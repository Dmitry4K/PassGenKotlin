interface CommandLineParser {
    fun parse(cmdArgs: Array<String>): PassGenSettings
}
class OptionName(val short: String, val long: String)

enum class CmdKeys(val optionName: OptionName){
    WORD_PRESET(OptionName("wp", "word-preset")),
    PASS_LENGTH(OptionName("pl", "pass-length"))
}