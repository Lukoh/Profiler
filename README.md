<p align="left">
  <a href="#"><img alt="Android OS" src="https://img.shields.io/badge/OS-Android-3DDC84?style=flat-square&logo=android"></a>
  <a href="#"><img alt="Languages-Kotlin" src="https://flat.badgen.net/badge/Language/Kotlin?icon=https://raw.githubusercontent.com/binaryshrey/Awesome-Android-Open-Source-Projects/master/assets/Kotlin_Logo_icon_white.svg&color=f18e33"/></a>
  <a href="#"><img alt="PRs" src="https://img.shields.io/badge/PRs-Welcome-3DDC84?style=flat-square"></a>
</p>

<p align="left">
:eyeglasses: Profiler by open-source contributor, Lukoh.
</p><br>

![header](https://1.bp.blogspot.com/-9MiK78CFMLM/YQFurOq9AII/AAAAAAAAQ1A/lKj5GiDnO_MkPLb72XqgnvD5uxOsHO-eACLcBGAsYHQ/s0/Android-Compose-1.0-header-v2.png)

# Profiler 
## Better Android Apps Using  Android Architecture Guidelines + Dependency injection with Hilt + Jetpack Compose + Navigation(Navigating with Compose)

Profiler is based on Android latest architectural components, Jetpack library, Clean Architecture, Coroutine & Flow and follows MVVM design pattern. Also Profiler App Architecture consist of Presentation layer, DI(Dependency Injection) and Repository layer. And All new latest technologies were applied into Profiler App as Advanced Android App Architecture. The many advanced functions already were applied into Profiler App. These stuff make Android Apps to be extended being more competitive power and help them to maintain consistency. Profiler open source was developed to help you building real apps with all the latest Android technologies mentioned below.

Now let’s dive into my open-source project, Profiler, which is based on the Android MVVM with Clean Architecture and the latest libraries like Jetpack & Hilt libraries. And the latest Jetpack Compose(Navigating with Compose + Adding a Hilt ViewModel to the Navigation graph) will help you develop your code very easily.

Please get in touch with me via email if you're interested in my technical experience and all techs which are applied into Profiler. Also visit my LinkedIn profile if you want to know more about me. Here is my email address below:

lukoh.nam@gmail.com

<p align="left">
<img src="https://github.com/Lukoh/Profiler/blob/main/Screenshot_1.jpg" width="24%" height="24%">
  
<img src="https://github.com/Lukoh/Profiler/blob/main/Screenshot_2.jpg" width="24%" height="24%">
  
<img src="https://github.com/Lukoh/Profiler/blob/main/Screenshot_3.jpg" width="24%" height="24%">
  
<img src="https://github.com/Lukoh/Profiler/blob/main/Screenshot_4.jpg" width="24%" height="24%">
</p>                                                                                            


An app for keeping up to date with the latest techs and developments in Android.
 * Jetpack Compose app.
 * Implements the recommended [Android Architecture Guidelines](https://developer.android.com/jetpack/compose/architecture)
 * Implements the recommended [Navigation(Navigating with Compose)](https://developer.android.com/jetpack/compose/navigation) Guidelines
 * Implements the recommended [Android Dependency injection with Hilt Guidelines](https://developer.android.com/training/dependency-injection/hilt-android)
 * Integrates Jetpack Libraries holistically in the context of a real world app

## Recommended App Architecture
Considering the common architectural principles mentioned in the previous section, each application should have at least two layers:

 * The UI layer that displays application data on the screen.
 * The data layer that contains the business logic of your app and exposes application data.

You can add an additional layer called the domain layer to simplify and reuse the interactions between the UI and data layers.

![mad-arch-overview-ui](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)
Figure 1. Diagram of a typical app architecture.


This Modern App Architecture encourages using the following techniques, among others:

 * A reactive and layered architecture.
 * Unidirectional Data Flow (UDF) in all layers of the app.
 * A UI layer with state holders to manage the complexity of the UI.
 * Coroutines and flows.
 * Dependency injection best practices.

For more information, see the following sections, the other Architecture pages in the table of contents, and the recommendations page that contains a summary of the most important best practices.

### UI layer
The role of the UI layer (or presentation layer) is to display the application data on the screen. Whenever the data changes, either due to user interaction (such as pressing a button) or external input (such as a network response), the UI should update to reflect the changes.

The UI layer is made up of two things:

 * UI elements that render the data on the screen. You build these elements using Views or Jetpack Compose functions.
 * State holders (such as [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) classes) that hold data, expose it to the UI, and handle logic.

![alt mad-arch-overview-ui](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-ui.png)
Figure 2. The UI layer's role in app architecture.

To learn more about this layer, see the UI layer page.

### Data layer
The data layer of an app contains the business logic. The business logic is what gives value to your app—it's made of rules that determine how your app creates, stores, and changes data.

The data layer is made of repositories that each can contain zero to many data sources. You should create a repository class for each different type of data you handle in your app. For example, you might create a MoviesRepository class for data related to movies, or a PaymentsRepository class for data related to payments.

![alt mad-arch-overview-data](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-data.png)
Figure 3. The data layer's role in app architecture.

Repository classes are responsible for the following tasks:

 * Exposing data to the rest of the app.
 * Centralizing changes to the data.
 * Resolving conflicts between multiple data sources.
 * Abstracting sources of data from the rest of the app.
 * Containing business logic.
Each data source class should have the responsibility of working with only one source of data, which can be a file, a network source, or a local database. Data source classes are the bridge between the application and the system for data operations.

To learn more about this layer, see the data layer page.

Domain layer
The domain layer is an optional layer that sits between the UI and data layers.

The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels. This layer is optional because not all apps will have these requirements. You should use it only when needed—for example, to handle complexity or favor reusability.

![alt mad-arch-overview-domain](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-domain.png)
Figure 4. The domain layer's role in app architecture.

Classes in this layer are commonly called use cases or interactors. Each use case should have responsibility over a single functionality. For example, your app could have a GetTimeZoneUseCase class if multiple ViewModels rely on time zones to display the proper message on the screen.

To learn more about this layer, see the [domain layer](https://developer.android.com/topic/architecture/domain-layer) page.

### Manage dependencies between components
Classes in your app depend on other classes in order to function properly. You can use either of the following design patterns to gather the dependencies of a particular class:

 * Dependency injection (DI): Dependency injection allows classes to define their dependencies without constructing them. At runtime, another class is responsible for providing these dependencies.

We recommend following dependency injection patterns and using the Hilt library in Android apps. Hilt automatically constructs objects by walking the dependency tree, provides compile-time guarantees on dependencies, and creates dependency containers for Android framework classes.

### General best practices
Programming is a creative field, and building Android apps isn't an exception. There are many ways to solve a problem; you might communicate data between multiple activities or fragments, retrieve remote data and persist it locally for offline mode, or handle any number of other common scenarios that nontrivial apps encounter.

Although the following recommendations aren't mandatory, in most cases following them makes your code base more robust, testable, and maintainable in the long run:

#### Don't store data in app components.

Avoid designating your app's entry points—such as activities, services, and broadcast receivers—as sources of data. Instead, they should only coordinate with other components to retrieve the subset of data that is relevant to that entry point. Each app component is rather short-lived, depending on the user's interaction with their device and the overall current health of the system.

#### Reduce dependencies on Android classes.

Your app components should be the only classes that rely on Android framework SDK APIs such as Context, or Toast. Abstracting other classes in your app away from them helps with testability and reduces coupling within your app.

#### Create well-defined boundaries of responsibility between various modules in your app.

For example, don't spread the code that loads data from the network across multiple classes or packages in your code base. Similarly, don't define multiple unrelated responsibilities—such as data caching and data binding—in the same class. Following the recommended app architecture will help you with this.

### Expose as little as possible from each module.

For example, don't be tempted to create a shortcut that exposes an internal implementation detail from a module. You might gain a bit of time in the short term, but you are then likely to incur technical debt many times over as your codebase evolves.

#### Focus on the unique core of your app so it stands out from other apps.

Don't reinvent the wheel by writing the same boilerplate code again and again. Instead, focus your time and energy on what makes your app unique, and let the Jetpack libraries and other recommended libraries handle the repetitive boilerplate.

#### Consider how to make each part of your app testable in isolation.

For example, having a well-defined API for fetching data from the network makes it easier to test the module that persists that data in a local database. If instead, you mix the logic from these two modules in one place, or distribute your networking code across your entire code base, it becomes much more difficult—if not impossible—to test effectively.

#### Types are responsible for their concurrency policy.

If a type is performing long-running blocking work, it should be responsible for moving that computation to the right thread. That particular type knows the type of computation that it is doing and in which thread it should be executed. Types should be main-safe, meaning they're safe to call from the main thread without blocking it.

#### Persist as much relevant and fresh data as possible.

That way, users can enjoy your app's functionality even when their device is in offline mode. Remember that not all of your users enjoy constant, high-speed connectivity—and even if they do, they can get bad reception in crowded places.

### Benefits of Architecture
Having a good Architecture implemented in your app brings a lot of benefits to the project and engineering teams:

 * It improves the maintainability, quality and robustness of the overall app.
 * It allows the app to scale. More people and more teams can contribute to the same codebase with minimal code conflicts.
 * It helps with onboarding. As Architecture brings consistency to your project, new members of the team can quickly get up to speed and be more efficient in less amount of time.
 * It is easier to test. A good Architecture encourages simpler types which are generally easier to test.
 * Bugs can be investigated methodically with well defined processes.

Investing in Architecture also has a direct impact in your users. They benefit from a more stable application, and more features due to a more productive engineering team. However, Architecture also requires an up-front time investment. To help you justify this time to the rest of you company, take a look at these case studies where other companies share their success stories when having a good architecture in their app.

# Navigating with Compose
If you’re working on an Android app, chances are you’ll need some form of navigation. Back stack handling, lifecycle, state saving and restoring, and deep linking are just some of them. You can take a closer look at the navigation component’s support for Jetpack Compose in the Profiler source.

The Navigation component provides support for Jetpack Compose applications. You can navigate between composables while taking advantage of the Navigation component’s infrastructure and features.

Jump to [Navigating with Compose](https://developer.android.com/jetpack/compose/navigation) if you’d like to learn it.

If you go through the above process, you can understand the source code more easily.

## MVVM with Clean Architecture: A Solid Combination
My purpose with this open-source project was to understand MVVM with Clean Architecture and latest Jetpack libraries, so I skipped over a few things that you can try to improve it further:

Use Kotlin Coroutine and Flow to remove callbacks and make it a little neater. Use states to represent your UI. (For that, check out this amazing talk by Jake Wharton.) Use Dagger2 to inject dependencies.

This is one of the best and most scalable architectures for Android apps. I hope you enjoyed this article, and I look forward to hearing how you’ve used this approach in your own apps!
