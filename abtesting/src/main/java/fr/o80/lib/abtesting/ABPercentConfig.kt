package fr.o80.lib.abtesting

import java.security.SecureRandom

/**
 * @author Olivier Perez
 */
@AbTestingDsl
class ABPercentConfig internal constructor(private val name: String) {

    private val cases: MutableList<ABPercentCase> = mutableListOf()

    lateinit var default: String

    @AbTestingDsl
    infix fun String.isIn(range: IntRange) {
        cases.add(ABPercentCase(range.first, range.last, this))
    }

    internal fun result(store: ABStore): String {
        val value = store.getInt(name, -1)
        for (aCase in cases) {
            if (aCase.matches(value)) {
                return aCase.value()
            }
        }
        return default
    }

    internal fun init(store: ABStore) {
        if (store.getInt(name, -1) == -1) {
            val random = SecureRandom()
            val value = random.nextInt(99)

            store.set(name, value)
        }
    }

}