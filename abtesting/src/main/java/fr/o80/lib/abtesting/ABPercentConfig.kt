package fr.o80.lib.abtesting

import java.security.SecureRandom

/**
 * @author Olivier Perez
 */
@AbTestingDsl
class ABPercentConfig internal constructor(private val name: String) {

    private val cases: MutableList<ABPercentCase> = mutableListOf()

    private lateinit var defaultValue: String

    @AbTestingDsl
    infix fun String.isIn(range: IntRange) {
        cases.add(ABPercentCase(range.first, range.last, this))
    }

    @AbTestingDsl
    infix fun String.isFor(default: DefaultCase) {
        defaultValue = this
    }

    @AbTestingDsl
    object DefaultCase

    internal fun result(store: ABStore): String {
        var value = store.getInt(name, -1)
        value = if (value >= 0) value else init(store)

        for (aCase in cases) {
            if (aCase.matches(value)) {
                return aCase.value()
            }
        }
        return defaultValue
    }

    private fun init(store: ABStore): Int {
        val value = store.getInt(name, -1)
        return when (value) {
            -1 -> {
                val random = SecureRandom()
                val newValue = random.nextInt(99)

                store[name] = newValue
                newValue
            }
            else -> {
                value
            }
        }
    }

}