package com.naveentp.preferencehelper

/**
 * @author Naveen T P
 * @since 27/03/19
 */

interface PreferenceObserver {

    fun <T> onChangePreferenceValue(key: String, value: T)
}