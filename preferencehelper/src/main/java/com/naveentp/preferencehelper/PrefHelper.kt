package com.naveentp.preferencehelper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import java.lang.UnsupportedOperationException

/**
 * @author Naveen T P
 * @since 26/03/19
 */
object PrefHelper {

    fun defaultPref(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPref(context: Context, name: String): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(task: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        task(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("No implementation")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("No implementation")
        }
    }
}