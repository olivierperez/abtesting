package fr.o80.lib.abtesting

import android.content.Context

/**
 * @author Olivier Perez
 */
@DslMarker
annotation class AbTestingDsl

@AbTestingDsl
fun abConfiguration(context: Context, block: ABTesting.() -> Unit): ABTesting {
    val sharedPref = context.getSharedPreferences("ABTestingSharedPref", Context.MODE_PRIVATE)
    val store = ABStore(sharedPref)
    return abConfiguration(store, block)
}

@AbTestingDsl
fun abConfiguration(store: ABStore, block: ABTesting.() -> Unit): ABTesting =
        ABTesting(store).apply(block)
