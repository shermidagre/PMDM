import java.util.Locale
import java.util.Locale.getDefault
/*


fun main() {
MaquinadelCafe.hacercafe()

    MaquinadelCafe.limpiarmaquina()
}



object MaquinadelCafe {
    var estadoactual: estadomaquinacafe = estadomaquinacafe.Idle

    fun hacercafe() {
        println("Depuracion : ${estadoactual.hashCode()}")

        when (estadoactual) {
            is estadomaquinacafe.Idle -> {
                println("Máquina encendida. ")
                print("quieres azucarina? (s|si / n|no|random): ")
                val response = readLine()?.lowercase()
                val azucarina = response == "s" || response == "si"
                if (azucarina) {
                    println("Agregando azucarina...")
                    Thread.sleep(1000)
                    estadoactual = estadomaquinacafe.echarazucar
                }

                print("Empezando a hacer café...")
                estadoactual = estadomaquinacafe.haciendocafe
                Thread.sleep(2000)
                // Simula un proceso de preparación
                estadoactual = estadomaquinacafe.sirviendocafe("Nescafé")
                println("\n¡Café listo! Estado: $estadoactual")
            }
            is estadomaquinacafe.haciendocafe -> {
                println("¡Espera! La máquina ya está haciendo café.")
            }
            is estadomaquinacafe.sirviendocafe -> {
                println("Ya hay café servido. Por favor, toma tu café.")
            }
            is estadomaquinacafe.Error -> {
                println("La máquina tiene un error: ${(estadoactual as estadomaquinacafe.Error).message}")
            }

            else -> {}
        }
    }
    sealed class estadomaquinacafe {
        object Idle : estadomaquinacafe()
        object echarazucar : estadomaquinacafe()
        object haciendocafe : estadomaquinacafe()
        data class sirviendocafe(val brand: String) : estadomaquinacafe()
        data class Error(val message: String) : estadomaquinacafe()
    }

    fun pedircafe(cafe: String) {
        println("Pedimos $cafe...")
        Thread.sleep(1000)
        println("Preparando el $cafe...")
        Thread.sleep(2000)
        println("$cafe listo!")
    }

    fun cogervaso() {

        println("El vaso está listo.")
        Thread.sleep(1000)
        println("Cogiendo el vaso...")
        Thread.sleep(2000)

    }

    fun limpiarmaquina() {
        cogervaso()
        println("Limpiando la máquina...")
        Thread.sleep(2000)
        estadoactual = estadomaquinacafe.Idle
        println("Máquina limpia. Depuracion: ${estadoactual.hashCode()}")
    }




} */
