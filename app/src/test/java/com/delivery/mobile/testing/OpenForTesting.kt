package com.delivery.mobile.testing

/**
 * This annotations will allows us to open some classes for mocking when they are final in
 * release .
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

/**
 * This [OpenForTesting] if we want it to be extendable in debug builds.
 */
@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting