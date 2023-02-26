package com.goforer.profiler.di.module

import android.app.Application
import android.content.Context
import android.os.Build
import com.orhanobut.logger.Logger
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.goforer.base.extension.isNull
import com.goforer.profiler.BuildConfig
import com.goforer.profiler.data.source.network.NetworkError
import com.goforer.profiler.data.source.network.NetworkErrorHandler
import com.goforer.profiler.data.source.network.adapter.factory.FlowCallAdapterFactory
import com.goforer.profiler.data.source.network.adapter.factory.NullOnEmptyConverterFactory
import com.goforer.profiler.data.source.network.api.Params
import com.goforer.profiler.data.source.network.api.RestAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestAPIModule {
    private const val timeout_read = 60L
    private const val timeout_connect = 60L
    private const val timeout_write = 60L

    @Singleton
    @Provides
    fun appContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideGSon(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideNetworkErrorHandler(context: Context) = NetworkErrorHandler(context)


    @Singleton
    @Provides
    fun providePersistentCookieJar(context: Context) =
        PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        cookieJar: PersistentCookieJar
    ): OkHttpClient {
        val ok = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .connectTimeout(timeout_connect, TimeUnit.SECONDS)
            .readTimeout(timeout_read, TimeUnit.SECONDS)
            .writeTimeout(timeout_write, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            if ("robolectric" != Build.FINGERPRINT)
                ok.addNetworkInterceptor(StethoInterceptor())

            val httpLoggingInterceptor =
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        if (isJSONValid(message))
                            Logger.json(message)
                        else
                            Timber.d("%s", message)
                    }

                    fun isJSONValid(jsonInString: String): Boolean {
                        try {
                            JSONObject(jsonInString)
                        } catch (ex: JSONException) {
                            try {
                                JSONArray(jsonInString)
                            } catch (ex1: JSONException) {
                                return false
                            }

                        }

                        return true
                    }
                })

            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            ok.addInterceptor(httpLoggingInterceptor)
        }

        ok.addInterceptor(interceptor)

        return ok.build()
    }

    @Provides
    @Singleton
    fun getRequestInterceptor(
        application: Application,
        context: Context
    ): Interceptor {
        return Interceptor {
            Timber.tag("PRETTY_LOGGER")

            val original = it.request()

            Timber.tag("pretty").e("Interceptor.url.host: %s", original.url.host)
            Timber.tag("pretty").e("Interceptor.url.path: %s", original.url)
            val requested = with(original) {
                val builder = newBuilder()

                builder.header("Accept", "application/json")
                builder.header("Accept-Version", "v1")
                builder.header("mobileplatform", "android")
                Timber.d("mobileplatform: android")

                builder.header("versioncode", "${BuildConfig.VERSION_CODE}")
                Timber.d("versioncode: ${BuildConfig.VERSION_CODE}")

                builder.build()
            }

            val response = it.proceed(requested)
            val body = response.body
            var bodyStr = body.string()
            Timber.d("**http-num: ${response.code}")
            Timber.d("**http-body: $body")

            if (!response.isSuccessful) {
                try {
                    when (response.code) {
                        NetworkError.ERROR_SERVICE_BAD_GATEWAY, NetworkError.ERROR_SERVICE_UNAVAILABLE -> {
                            // Implemented UI
                        }

                        NetworkError.ERROR_SERVICE_UNPROCESSABLE_ENTITY -> {
                            val networkError =
                                Gson().fromJson(bodyStr, NetworkError::class.java)

                            networkError.isNull({

                            }, {
                                networkError.detail[0].msg =
                                    original.url.encodedPath + "\n" + networkError.detail[0].msg
                                bodyStr = Gson().toJson(networkError)
                            })
                        }

                        else -> {
                            val networkError =
                                Gson().fromJson(bodyStr, NetworkError::class.java)

                            networkError.isNull({

                            }, {
                                networkError.detail[0].msg =
                                    original.url.encodedPath + "\n" + networkError.detail[0].msg
                                bodyStr = Gson().toJson(networkError)
                            })
                        }
                    }
                } catch (e: Exception) {
                    e.stackTrace
                    Timber.e(Throwable(e.toString()))
                }
            }

            val builder = response.newBuilder()

            builder.body(bodyStr.toByteArray().toResponseBody(body.contentType())).build()
        }
    }

    @Singleton
    @Provides
    fun provideRestAPI(gson: Gson, okHttpClient: OkHttpClient): RestAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.apiServer)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .client(okHttpClient)
            .build()

        return retrofit.create(RestAPI::class.java)
    }
}