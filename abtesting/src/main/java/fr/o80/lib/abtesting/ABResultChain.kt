package fr.o80.lib.abtesting

/**
 * Used to chain calls for each case.
 *
 * @author Olivier Perez
 */
class ABResultChain(private val resultValue: String) {
    fun on(value: String, action: () -> Unit): ABResultChain? =
            if (value == resultValue) {
                action()
                null
            } else {
                this
            }

    fun otherwise(action: () -> Unit) {
        action()
    }
}