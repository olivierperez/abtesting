package fr.o80.lib.abtesting

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ABTestingUnitTest {

    @MockK
    private lateinit var store: ABStore

    private lateinit var abTesting: ABTesting

    private val name = "test"

    init {
        MockKAnnotations.init(this)
    }

    @Before
    fun setup() {
        abTesting = abConfiguration(store) {
            percentage(name) {
                "A" isIn 1..33
                "B" isIn 34..66
                default = "default"
            }
        }
    }

    @Test
    fun should_match_one_case() {
        // Given
        every { store.getInt(name, any()) } returns 5

        // When
        val result = abTesting.result(name)

        // Then
        assertEquals("A", result)
    }

    @Test
    fun should_match_default_case() {
        // Given
        every { store.getInt(name, any()) } returns 99

        // When
        val result = abTesting.result(name)

        // Then
        assertEquals("default", result)
    }
}