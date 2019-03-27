package com.naveentp.preferencehelper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * @author Naveen T P
 * @since 26/03/19
 */
object PreferenceHelper {
    /**
     * Default SharedPreference
     * @param context   Context for getting default sharedPreference.
     * */
    fun defaultPref(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Custom SharedPreference
     * @param context   Context for getting default sharedPreference.
     * @param name      Desired preferences file. If a preferences file by this name
     * does not exist, it will be created when you retrieve an
     * editor (SharedPreferences.edit()) and then commit changes (Editor.commit()).
     * */
    fun customPref(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
}