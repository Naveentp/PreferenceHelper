package com.naveentp.preferencehelper

import android.content.Context
import android.content.SharedPreferences
import java.lang.UnsupportedOperationException
import kotlin.reflect.KProperty

/**
 * @author Naveen T P
 * @since 27/03/19
 */

class PreferenceDelegate<T> {

    private var pref: SharedPreferences
    private var key: String
    private var defaultValue: T
    private var listener: PreferenceObserver? = null

    constructor(pref: SharedPreferences, key: String, defaultValue: T, listener: PreferenceObserver? = null) {
        this.pref = pref
        this.key = key
        this.defaultValue = defaultValue
        this.listener = listener
    }

    constructor(context: Context,
                name: String? = null, key: String, defaultValue: T, listener: PreferenceObserver? = null) {
        this.pref =
            if (name == null) PreferenceHelper.defaultPref(context) else PreferenceHelper.customPref(context, name)
        this.key = key
        this.defaultValue = defaultValue
        this.listener = listener
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return pref[key, defaultValue]
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        pref[key] = value
        listener?.onChangePreferenceValue(key, value)
    }
}

/**
 * Internal function to save the value to SharedPreferences
 * @param task  Takes higher order function to save the value to preference
 * */
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

@Suppress("UNCHECKED_CAST")
inline operator fun <T> SharedPreferences.get(key: String, defaultValue: T? = null): T {
    return when (defaultValue) {
        is String -> getString(key, defaultValue as? String) as T
        is Int -> getInt(key, defaultValue as? Int ?: -1) as T
        is Boolean -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        is Float -> getFloat(key, defaultValue as? Float ?: -1f) as T
        is Long -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("No implementation")
    }
}
