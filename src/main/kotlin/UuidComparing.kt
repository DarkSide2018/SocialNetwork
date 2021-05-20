import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.util.stream.Collectors
import java.io.FileWriter

import java.io.BufferedWriter


class UuidComparing {

}

fun main() {
    val from1CUuids = BufferedReader((FileReader("src/main/resources/from1C.csv"))).lines().collect(Collectors.toSet())
    val fromOboz2Uuids =
        BufferedReader((FileReader("src/main/resources/fromOboz2.csv"))).lines().collect(Collectors.toSet())
    val bufferedWriter = BufferedWriter(FileWriter("src/main/resources/result2.csv"))
    val matchingSet = mutableSetOf<String>()
    from1CUuids.forEach { el ->
        if (fromOboz2Uuids.contains(el)) {
            matchingSet.add(el)
        }
    }
    println(matchingSet.size)
    matchingSet.forEachIndexed { index, value ->
        run {
            bufferedWriter.write(value)
            bufferedWriter.newLine()
        }
    }
   bufferedWriter.close()
}