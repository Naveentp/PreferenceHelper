# Preference Helper
> A SharedPreferences helper library to save and fetch the values easily.

## Use in your project
Add this to your module's `build.gradle`

```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'com.naveentp:preferencehelper:0.0.1'
}
```

## Usage Example 
- **Initialise the SharedPreferences**

    - Default SharedPreferences
        ```kotlin
        PreferenceHelper.defaultPref(this)
        ```
        
        or Lazy loading
        
        ```kotlin
        private val sharedPref: SharedPreferences by lazy {
            PreferenceHelper.customPref(this, "Sample")
        }
        ```
        
    - Custom SharedPreferences
        ```kotlin
        PreferenceHelper.customPref(this, "YOUR_SHARED_PREFERENCES_NAME")
        ```
    
- **Save / Fetch values from SharedPreferences**

    - Using operator overloading method
        - Save
            ```koltin
            // To save the value to SharedPreferences
            sharedPref["name"] = "Naveen" 
            ```
        
        - Fetch
            ```koltin
            // To fetch the value from SharedPreferences
            val userName = sharedPref["name", DEFAULT_VALUE] 
            ```
            
    - Using Delegation properties method
    
        - Initialisation 
            ```koltin
            // Create userName variable that corresponds to sharedPreference "name" key
            var name by PreferenceDelegate(sharedPref, "name", DEFAULT_VALUE)
            ```
        - Save
            ```kotlin
            //Saves value "Naveen" to sharedPreference with key "name"
            name = "Naveen"
            ```
    
        - Fetch
            ```koltin
            // To fetch the value "Naveen" from SharedPreferences
            val userName = name
            ```
            
    - Adding Listeners
        - Individual
            ```kotlin
            var userAge by PreferenceDelegate(sharedPref, "name", 0, object : PreferenceObserver {
                override fun <T> onChangePreferenceValue(key: String, value: T) {
                    print("$key: $value")
                }
            })
            ```
            
        - Global (Activity level)
            ```kotlin
            class MainActivity : AppCompatActivity(), PreferenceObserver {
                
                //Lazy initialisation of SharedPreferences
                private val sharedPref: SharedPreferences by lazy {
                    PreferenceHelper.customPref(this, "Sample")
                }
                
                override fun onCreate(savedInstanceState: Bundle?) {
                    super.onCreate(savedInstanceState)
                    setContentView(R.layout.activity_main)
                    
                    //Pass activity reference handle callbacks all at one place.
                    var userAge by PreferenceDelegate(sharedPref, "name", 0, this)
                }
                
                
                // Global listener which listens to all the PreferenceDelegates
                override fun <T> onChangePreferenceValue(key: String, value: T) {
                    print("$key: $value")
                }
            }
            ```
                  
   ** **Refer MainActivity in Sample project for more samples**

## License
Licensed under Apache Licence, Version 2.0. [Check the full licence here](https://github.com/Naveentp/PreferenceHelper/blob/master/LICENSE.txt)
