package example.myapp
import java.lang.Math.PI
open class Aquarium(open var length: Int = 100,open var width: Int = 20, open var height: Int = 40) {
init {
    println("Inicializando aquarium")
}
    fun printSize() {

        println(shape)
        println("Width: $width cm " +
                "Length: $length cm " +
                "Height: $height cm ")

        println("Volume: $volume l")
        // 1 l = 1000 cm^3
        println("Volume: $volume l Water: $water l (${water/volume*100.0}% full)")
    }

    constructor(numberOfFish: Int) : this() {
        val tanque = numberOfFish * 2000 * 1.1
        height = (tanque / (length * width)).toInt()

    }

   open var volume: Int
        get() = width * height * length / 1000  // 1000 cm^3 = 1 l
         set(value) {
            height = (value * 1000) / (width * length)
        }

    open var shape ="recangle"
    open var water: Double = 0.0
        get() = volume * 0.9


    class TowerTank (override var height: Int, var diameter: Int): Aquarium(height = height, width = diameter, length = diameter) {
        override var volume: Int
            // ellipse area = π * r1 * r2
            get() = (width/2 * length/2 * height / 1000 * PI).toInt()
            set(value) {
                height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
            }

        override var water: Double = 0.0
            get() = volume * 0.8
        override var shape = "cylinder"
    }

}
fun buildAquarium() {
    val aquarium1 = Aquarium()
    aquarium1.printSize()
    val aquarium2 = Aquarium(length = 50, width = 30)
    aquarium2.printSize()
    val aquarium3 = Aquarium(29)
    aquarium3.printSize()
    aquarium3.volume = 70
    aquarium3.printSize()
    val aquarium4 = Aquarium(length = 25, width = 25, height = 40)
    aquarium4.printSize()

    val myAquarium = Aquarium(width = 25, length = 25, height = 40)
    myAquarium.printSize()
    val myTower = Aquarium.TowerTank(diameter = 25, height = 40)
    myTower.printSize()


}

fun main() {
    buildAquarium()
}

