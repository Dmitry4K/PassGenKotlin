import java.lang.NumberFormatException
import java.util.Random

class PassGenUtil(
    parser: CommandLineParser = ApacheCliCmdParserImpl(),
    cliArgs: Array<String> = arrayOf()) {
    private val settings = parser.parse(cliArgs)
    fun generate(): String{
        try {
            return PassGenerator { Random().nextInt() }.generate(settings)
        } catch (e: PresetParseException){
            TODO()
        } catch (e: NumberFormatException){
            TODO()
        }
    }
}