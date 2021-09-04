import java.util.*

fun main(args: Array<String>){
    val passGen = PassGenUtil(cliArgs = arrayOf("-pl", "15"))
    val input = Scanner(System.`in`)
    val count = input.nextInt()
    generateSequence { passGen.generate() }.take(count).forEach(::println)
}
