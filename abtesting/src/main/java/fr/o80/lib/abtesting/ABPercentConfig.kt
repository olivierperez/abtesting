package fr.o80.lib.abtesting

import java.security.SecureRandom

/**
 * @author Olivier Perez
 */
@AbTestingDsl
class ABPercentConfig internal constructor(private val name: String) {

    private val cases: MutableList<ABPercentCase> = mutableListOf()

    private lateinit var defaultValue: String
    object DefaultCase

    @AbTestingDsl
    infix fun String.isIn(range: IntRange) {
        cases.add(ABPercentCase(range.first, range.last, this))
    }

    @AbTestingDsl
    infix fun String.isFor(default: DefaultCase) {
        defaultValue = this
    }

    @AbTestingDsl

    internal fun result(store: ABStore): String {
        var value = store.getInt(name, DEFAULT_VALUE)
        value = if (value >= 0) value else init(store)

        for (aCase in cases) {
            if (aCase.matches(value)) {
                return aCase.value()
            }
        }
        return defaultValue
    }

    private fun init(store: ABStore): Int {
        val value = store.getInt(name, DEFAULT_VALUE)
        return when (value) {
            DEFAULT_VALUE -> {
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

    fun reset(store: ABStore) {
        store[name] = DEFAULT_VALUE
    }

    companion object {
        private const val DEFAULT_VALUE = -1
    }

}