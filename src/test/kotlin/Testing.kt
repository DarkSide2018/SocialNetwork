class Testing {


}

fun main(){
    var liost:List<String> = mutableListOf("1","1","1")

    var processedList = liost.map {
        if(it == "1"){
            println("process 1")
            return@map "processed 1"
        }else{
            println("process other")
            return@map "processed other"
        }
    }
    println(processedList)
}