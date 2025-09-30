import kotlin.compareTo
import kotlin.unaryMinus

fun main() {
    MaquinadelCafe.iniciar()
}
object MaquinadelCafe {
    var estadoactual: EstadoMaquinaCafe = EstadoMaquinaCafe.Idle
    var interfaz = InterfazUsuario()

    fun iniciar() {
        while (true) {
            when (estadoactual) {
                is EstadoMaquinaCafe.Idle -> {
                    interfaz.mostrarMensaje("\nSelecciona tu profucto\n")
                    estadoactual = EstadoMaquinaCafe.MenuSeleccionCafe
                }
                is EstadoMaquinaCafe.MenuSeleccionCafe -> {
                    val tipoCafe = interfaz.pedirTipoCafe()
                    estadoactual = EstadoMaquinaCafe.PreparandoCafe(tipoCafe)
                }

                is EstadoMaquinaCafe.PreparandoCafe -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.PreparandoCafe).tipoCafe
                    interfaz.mostrarMensaje("Preparando $cafe...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Sirviendo(cafe)
                }

                is EstadoMaquinaCafe.Sirviendo -> {
                    interfaz.mostrarMensaje("Vaso servido. ¡Disfruta tu café!")
                    Thread.sleep(2000)
                    estadoactual = EstadoMaquinaCafe.Limpiando
                }

                is EstadoMaquinaCafe.Limpiando -> {
                    interfaz.mostrarMensaje("Limpiando la máquina...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Idle
                }
                is EstadoMaquinaCafe.Error -> {
                    val msg = (estadoactual as EstadoMaquinaCafe.Error).message
                    interfaz.mostrarError(msg)
                    Thread.sleep(500)
                }
                else -> break
            }
        }
    }

    sealed class EstadoMaquinaCafe {
        object Idle : EstadoMaquinaCafe()
        object MenuSeleccionCafe : EstadoMaquinaCafe()
        data class PreparandoCafe(val tipoCafe: String) : EstadoMaquinaCafe()
        data class Sirviendo(val tipoCafe: String) : EstadoMaquinaCafe()
        object Limpiando : EstadoMaquinaCafe()
        data class Error(val message: String) : EstadoMaquinaCafe()
    }

}