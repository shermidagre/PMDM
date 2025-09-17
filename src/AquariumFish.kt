
object GoldColor : FishColor {
    override val color = "gold"
}


class Shark: FishAction, FishColor {
    override val color = "gray"
    override fun eat() {
        println("hunt and eat fish")
    }
}

/*
    class Plecostomus: FishAction, FishColor by GoldColor {
        override fun eat() {
            println("eat algae")
        }
    }


 */
class Plecostomus (fishColor: FishColor = GoldColor):
    FishAction by PrintingFishAction("eat algae"),
    FishColor by fishColor



class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}

    fun makeFish() {
        val shark = Shark()
        val pleco = Plecostomus()

        println("Shark: ${shark.color}")
        shark.eat()
        println("Plecostomus: ${pleco.color}")
        pleco.eat()
    }


    fun main() {
        makeFish()
    }
   /* abstract class AquariumFish  : FishAction{
        abstract val color: String
        override fun eat() = println("yum")
    */




