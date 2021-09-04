import org.apache.commons.cli.*
import java.lang.NumberFormatException

class ApacheCliCmdParserImpl: CommandLineParser {
    private val options = Options()
    init {
        options.addOption(
            CmdKeys.WORD_PRESET.optionName.short,
            CmdKeys.WORD_PRESET.optionName.long,
            true,
            "Word preset which using while pass generating"
        )
        options.addOption(
            CmdKeys.PASS_LENGTH.optionName.short,
            CmdKeys.PASS_LENGTH.optionName.long,
            true,
            "Password length"
        )
    }
    override fun parse(cmdArgs: Array<String>): PassGenSettings {
        val cmdParser = DefaultParser()
        val cmd = cmdParser.parse(options, cmdArgs)
        val settings = PassGenSettings()
        if (cmd.hasOption(CmdKeys.WORD_PRESET.optionName.short)) {
            settings.symbolsAndCoefficients =
                PassGenSettings.genArrayOfSymbolsAndCoefficients(cmd.getOptionValue(CmdKeys.WORD_PRESET.optionName.short))
        }
        if (cmd.hasOption(CmdKeys.PASS_LENGTH.optionName.short)) {
            settings.passLength = cmd.getOptionValue(CmdKeys.PASS_LENGTH.optionName.short).toInt()
        }
        return settings
    }
}
