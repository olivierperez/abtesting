package fr.o80.lib.abtesting

import android.content.Context

/**
 * @author Olivier Perez
 */
@DslMarker
annotation class AbTestingDsl

@AbTestingDsl
fun abConfiguration(context: Context, block: ABTesting.() -> Unit): ABTesting =
        ABTesting(context).apply {
            block()
            init()
        }
