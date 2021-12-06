# weather-forecast
## Ngoc. Vo Thi Thanh @ 2021



### Project Statement
Build and android app to retrieve weather information based on their searching criteria and render the search result on dashboard screen (Data fetched from OpenWeatherMap)

### Concerns
- API response only return data of one city even there are more than one city that match the search value. This problem may make user confused so I replace there search text with 
{@city, @name} string to inform user which city's weather they are seeing.
- API does not provide accent string search. I handled this issue by decent the string before calling API

### Principles, patterns & practice being applied
In this simple application, I used MVC, which I feel way more comfortable to use, to design the architecture.
Beside, I follow Single responsibility principle to make sure my classes/functions keep it own responsibility (which is the most important principle).
Some static classes are defined to provide commonly use method.

### Folder Structure
  - activities: activity files
  - core: configuration files
  - model: model files
  - network: network service files
  - utils: helper files
 
### Kotlin libraries/plugins
  - kotlin-android-extensions: to access UI element 
  - androidx.lifecycle:lifecycle-process: to check Rooted Device in onResume event
  - com.squareup.retrofit2:retrofit: to implement network service
  
### Installation
  - Make sure you've configured your SDK location in local.properties
  - This project is built with Android Studio 4.1.1 (consider update your Android Studio for a better experience)
  - Run project on real mobile device (Recommendation)

### Check List: Expected Output Completed - 1,2,3,4,6,7,8  
 
### Suggestion in future
Since OpenWeatherMap API provide us 3 params that could be customize by users (city, cnt, unit), it would be nice to have a Settings page, where users could change their
setting and save them to cache. The settings page could contain a seek bar to choose number of day displayed from 1 to 17, 3 radio button to allow user pick the temperature unit
(Kelvin, Celsius, Fahrenheit). If we plan to make this project multi-language, we could add a dropdown menu to provide the language that we supported to the settings page too.
