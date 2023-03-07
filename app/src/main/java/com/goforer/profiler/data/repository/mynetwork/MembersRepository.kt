package com.goforer.profiler.data.repository.mynetwork

import com.goforer.profiler.data.repository.Repository
import com.goforer.profiler.data.source.local.mynetwork.MembersLocalDataSource
import com.goforer.profiler.data.source.mediator.LocalDataMediator
import com.goforer.profiler.data.source.model.entity.source.response.mynetwork.Person
import com.goforer.profiler.di.module.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MembersRepository
@Inject constructor(
    @ApplicationScope private val externalScope: CoroutineScope,
    private val membersLocalDataSource: MembersLocalDataSource
) : Repository<List<Person>>() {
    val profiles = object : LocalDataMediator<List<Person>>(externalScope, replyCount) {
        override fun load() = membersLocalDataSource.members
    }.asSharedFlow
}