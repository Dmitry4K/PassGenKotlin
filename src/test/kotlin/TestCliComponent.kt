import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestCliComponent {
    @Test
    fun `Testing cli without args`(){
        assertEquals(PassGenUtil().generate().length, 10)
    }

    @Test
    fun `Testing cli with pass length attribute`(){
        assertEquals(PassGenUtil(cliArgs = arrayOf("-pl", "20")).generate().length, 20)
    }

    @Test
    fun `Testing cli with long pass length attribute`(){
        assertEquals(PassGenUtil(cliArgs = arrayOf("--pass-length", "20")).generate().length, 20)
    }

    @Test
    fun `Testing cli with preset attribute`(){
        assertTrue(PassGenUtil(cliArgs = arrayOf("--word-preset", "A")).generate().filter{it == 'A' }.toList().size == 10)
    }
}