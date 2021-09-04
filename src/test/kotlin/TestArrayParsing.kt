import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class TestArrayParsing {
    @Test
    fun `Sequence parsing with one symbol`(){
        val result = PassGenSettings.genArrayOfSymbolsAndCoefficients("A")
        assertEquals(listOf(Pair('A', 1)), result)
    }

    @Test
    fun `Sequence parsing with one symbol and coefficient`(){
        val result = PassGenSettings.genArrayOfSymbolsAndCoefficients("A:2")
        assertEquals(listOf(Pair('A', 2)), result)
    }

    @Test
    fun `Multi sequence parsing with coefficient`(){
        val result = PassGenSettings.genArrayOfSymbolsAndCoefficients("A-C:2")
        assertEquals(listOf(Pair('A', 2), Pair('B', 2), Pair('C', 2)), result)
    }

    @Test
    fun `Sequence parsing with spec symbol`(){
        val result = PassGenSettings.genArrayOfSymbolsAndCoefficients("\\\\")
        assertEquals(listOf(Pair('\\', 1)), result)
    }
}