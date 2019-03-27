package com.naveentp.preferencehelper

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //Lazy initialisation of SharedPreferences
    private val sharedPref: SharedPreferences by lazy {
        PreferenceHelper.customPref(this, "Sample")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create userName variable that corresponds to sharedPreference "name" key
        var userName by PreferenceDelegate(sharedPref, "name", "")

        // Assigning userName a value, updates sharedPreference <key, value> pair of "name"
        userName = "Naveen"

        // while assigning the data userName now will fetch from sharedPreference key "name"
        val name = userName


        /**You can listen to preference changes for a particular key
         * In this case, whenever userAge updates, {@link onChangePreferenceValue} will be triggered
         */
        var userAge by PreferenceDelegate(sharedPref, "name", 0, object : PreferenceObserver {
            override fun <T> onChangePreferenceValue(key: String, value: T) {
                print("$key: $value")
            }
        })

        // Notice this assignment triggers onChangePreferenceValue callback
        userAge = 26
    }
}
