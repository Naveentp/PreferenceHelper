package com.naveentp.preferencehelper

/**
 * @author Naveen T P
 * @since 27/03/19
 */

typealias PreferenceObserver<T> = (key: String, value: T) -> Unit