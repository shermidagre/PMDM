package example.myapp

class Aquarium(var length: Int = 100, var width: Int = 20, var height: Int = 40) {
init {
    println("Inicializando aquarium")
}
    fun printSize() {
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")

        println("Volume: $volume l")

    }

    constructor(numberOfFish: Int) : this() {
        val tanque = numberOfFish * 2000 * 1.1
        height = (tanque / (length * width)).toInt()

    }

    var volume: Int
        get() = width * height * length / 1000  // 1000 cm^3 = 1 l
        set(value) {
            height = (value * 1000) / (width * length)
        }

}
fun buildAquarium() {
    val myAquarium = Aquarium()
    myAquarium.printSize();
    myAquarium.height = 50
    myAquarium.printSize();
    val aquarium1 = Aquarium()
    aquarium1.printSize()
    val aquarium2 = Aquarium(length = 50, width = 30)
    aquarium2.printSize()
    val aquarium3 = Aquarium(29)
    aquarium3.printSize()
}

fun main() {
    buildAquarium()
}


