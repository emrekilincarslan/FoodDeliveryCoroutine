# OnlineFood

This app is current Android Architecture state using Android development best practices.
 
Introduction
The application uses Clean Architecture based on MVVM and Repository patterns. Implemented Architecture principles follow Google
recommended Guide to app architecture.

The application is written entirely in Kotlin.

Android Jetpack is used as an Architecture glue including but not limited to ViewModel, LiveData, Lifecycles, Navigation, 
Room and Data Binding. See a complete list in "Libraries used" section.

The application does network HTTP requests via Retrofit, OkHttp and GSON. Loaded data is saved to SQL based database Room, 
which serves as single source of truth and support offline mode.

Kotlin Coroutines manage background threads with simplified code and reducing needs for callbacks. 
Combination of Coroutines and Kotlin build in functions (transformation, collections) are preferred over RxJava 2.

Work manager does synchronisation job being compatible with Doze Mode and using battery efficiently. Navigation component 
manages in-app navigation.

Dagger 2 is used for dependency injection.

Glide is used for image loading

Description
In the assets you can find a JSON file (sample.json), this file contains all the
necessary data to complete this assignment. Project parses the JSON file and use it for the
visualization and sorting of the list. Using the following priority of the sorting (from the
highest to the lowest priority):

1. Favourites​: Favourite restaurants are at the top of the list, your current favourite
restaurants are stored locally on the phone.
2. Openings state​: Restaurant is either open (top), you can order ahead (middle) or
a restaurant is currently closed (bottom). (Values available in sample.json)
3. Sort options​: Always one sort option is chosen and this can be best match,
newest, rating average, distance, popularity, average product price, delivery costs
or the minimum cost. (Values available in sample.json)
Regarding the filtering,  it is possible for us to search by
restaurant name


 Notes
●visualizing the name of the restaurants, the current opening state, the
selected sort, the sort value for a restaurant and if it’s a favourite or not.
● if we have multiple favourite restaurants, they are also sorted based
on their current openings state and current selected sort.

Sorting is in the UI thread becouse of the list. You can launch coroutine for applying sort in work threat.Coroutine is ready for that implemented in MainActivity.
