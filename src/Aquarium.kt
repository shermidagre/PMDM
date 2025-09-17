package example.myapp

class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {

    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")
    }
}
fun buildAquarium() {
    val myAquarium = Aquarium()
    myAquarium.printSize();
    myAquarium.height = 50
    myAquarium.printSize();
}

fun main() {
    buildAquarium()
}


