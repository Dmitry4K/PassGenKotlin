import java.util.function.Supplier

class PassGenerator(private val randomSupplier: Supplier<Int>) {

    fun generate(settings: PassGenSettings): String{
        val coefficientSum = settings.symbolsAndCoefficients.sumOf{it.second}
        val genSequence = generateSequence(Pair(settings.symbolsAndCoefficients.first().second, 0)){
            Pair(it.first+settings.symbolsAndCoefficients[it.second + 1].second, it.second+1)
        }
            .take(settings.symbolsAndCoefficients.size)
            .map{it.first}

        return generateSequence {
            val nextValue = kotlin.math.abs(randomSupplier.get()) % coefficientSum+1
            genSequence
                .filter{it<=nextValue}
                .mapIndexed{index, _ -> settings.symbolsAndCoefficients[index].first }
                .last()
        }
            .take(settings.passLength)
            .joinToString(separator = "")
    }
}