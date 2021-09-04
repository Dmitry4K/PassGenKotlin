import java.util.*

const val DASH = '-'
val SPEC_SYMBOLS = listOf('\\')
const val COEFFICIENT_DELIMITER = ':'

class PassGenSettings {
    var symbolsAndCoefficients = genArrayOfSymbolsAndCoefficients("A-Za-z0-9")
    var passLength = 10
    companion object{
        fun genArrayOfSymbolsAndCoefficients(presetString: String): List<Pair<Char, Int>>{
            val queueOfChars : Queue<Char> = LinkedList(presetString.toCharArray().asList())
            val result = mutableListOf<Pair<Char, Int>>()
            while(queueOfChars.isNotEmpty()){
                val triple = parseSequence(queueOfChars)
                var from = triple.first
                val to = triple.second
                val coefficient = triple.third
                if (from == to){
                    result.add(Pair(from, coefficient))
                }
                else{
                    result.addAll(
                        generateSequence { (from).takeIf { from++ <= to } }
                            .map{Pair(it, coefficient)}
                            .toList()
                    )
                }
            }
            return result.toList()
        }
        private fun parseSequence(queue: Queue<Char>): Triple<Char, Char, Int>{
            val seq = parseSymbolSequence(queue)
            var coefficient = 1
            if(isCoefficientNext(queue)){
                coefficient = parseCoefficient(queue)
            }
            return Triple(seq.first, seq.second, coefficient)
        }
        private fun isCoefficientNext(queue: Queue<Char>): Boolean = queue.isNotEmpty() && queue.peek() == COEFFICIENT_DELIMITER

        private fun parseCoefficient(queue: Queue<Char>): Int {
            /*val delimiter = */queue.poll()
            return queue.poll().toString().toInt()
        }
        private fun parseSymbolSequence(queue: Queue<Char>): Pair<Char, Char> {
            val from = parseSymbol(queue)
            var to = from
            if(getIfDash(queue)){
                to = parseSymbol(queue)
            }
            return Pair(from, to)
        }
        private fun getIfDash(queue: Queue<Char>): Boolean{
            val isDash = queue.isNotEmpty() && queue.peek() == DASH
            if(isDash){
                queue.poll()
            }
            return isDash
        }
        private fun parseSymbol(queue: Queue<Char>) : Char {
            val char = queue.poll()
            if(!getIfSlash(queue) && char in SPEC_SYMBOLS){
                TODO()
            }
            return char
        }
        private fun getIfSlash(queue: Queue<Char>): Boolean{
            val isSlash = queue.isNotEmpty() && queue.peek() == '\\'
            if(isSlash){
                queue.poll()
            }
            return isSlash
        }
    }
}

class PresetParseException(message: String): Exception(message)