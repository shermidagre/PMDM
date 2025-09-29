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
                    estadoactual = EstadoMaquinaCafe.ComprobandoImporte(tipoCafe, when (tipoCafe) {
                        "solo" -> 0.40
                        "leche" -> 0.50
                        "capuchino" -> 0.60
                        else -> 0.0
                    })
                }

                is EstadoMaquinaCafe.ComprobandoImporte -> {
                    val datos = estadoactual as EstadoMaquinaCafe.ComprobandoImporte
                    val tipoCafe = datos.tipoCafe
                    val importe = datos.importe
                    interfaz.mostrarMensaje("El precio del café $tipoCafe es $importe €. Por favor, inserta el importe.")
                    do {
                        var dineroInsertado = readLine()?.toDoubleOrNull() ?: 0.0
                        var cambio = dineroInsertado - importe
                        if (cambio > 0.0) {
                            interfaz.mostrarMensaje("Has insertado $dineroInsertado €. Tu cambio es $cambio €.")
                            estadoactual = EstadoMaquinaCafe.ComprobandoRecursos(tipoCafe)
                            break
                        } else if (cambio < 0.0) {
                            interfaz.mostrarMensaje("Has insertado $dineroInsertado €. Te faltan ${-cambio} €.")
                        } else {
                            interfaz.mostrarMensaje("Has insertado el importe exacto: $dineroInsertado €.")
                            estadoactual = EstadoMaquinaCafe.ComprobandoRecursos(tipoCafe)
                            break
                        }
                    } while (true)
                }


                is EstadoMaquinaCafe.ComprobandoRecursos -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.ComprobandoRecursos).tipoCafe
                    interfaz.mostrarMensaje("Comprobando recursos para $cafe...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.VerificandoAgua(cafe)
                }
                is EstadoMaquinaCafe.VerificandoAgua -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.VerificandoAgua).tipoCafe
                    interfaz.mostrarMensaje("Verificando agua...")
                    Thread.sleep(500)
                    val hayAgua = true
                    if (hayAgua) {
                        estadoactual = EstadoMaquinaCafe.VerificandoAzucar(cafe)
                    } else {
                        estadoactual = EstadoMaquinaCafe.Error("No queda agua")
                    }
                }
                is EstadoMaquinaCafe.VerificandoAzucar -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.VerificandoAzucar).tipoCafe
                    interfaz.mostrarMensaje("Verificando azúcar...")
                    Thread.sleep(500)
                    val hayAzucar = true // Simulación
                    if (hayAzucar || cafe == "solo") {
                        estadoactual = EstadoMaquinaCafe.RecursosOK(cafe)
                    } else {
                        estadoactual = EstadoMaquinaCafe.Error("Fallo al verificar azúcar")
                    }
                }
                is EstadoMaquinaCafe.RecursosOK -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.RecursosOK).tipoCafe
                    estadoactual = EstadoMaquinaCafe.PreparandoCafe(cafe)
                }
                is EstadoMaquinaCafe.PreparandoCafe -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.PreparandoCafe).tipoCafe
                    interfaz.mostrarMensaje("Preparando $cafe...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.DecidiendoAzucar(cafe)
                }
                is EstadoMaquinaCafe.DecidiendoAzucar -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.DecidiendoAzucar).tipoCafe
                    val azucar = interfaz.pedirAzucar()
                    estadoactual = if (azucar) EstadoMaquinaCafe.ConAzucar(cafe) else EstadoMaquinaCafe.SinAzucar(cafe)
                }
                is EstadoMaquinaCafe.ConAzucar -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.ConAzucar).tipoCafe
                    interfaz.mostrarMensaje("Añadiendo azúcar...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Sirviendo(cafe, true)
                }
                is EstadoMaquinaCafe.SinAzucar -> {
                    val cafe = (estadoactual as EstadoMaquinaCafe.SinAzucar).tipoCafe
                    interfaz.mostrarMensaje("Preparando café sin azúcar...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Sirviendo(cafe, false)
                }
                is EstadoMaquinaCafe.EchandoVaso -> {
                    interfaz.mostrarMensaje("Esperando vaso...")
                    Thread.sleep(500)

                }

                is EstadoMaquinaCafe.Sirviendo -> {
                    val datos = estadoactual as EstadoMaquinaCafe.Sirviendo
                    interfaz.mostrarMensaje("Sirviendo ${datos.tipoCafe} ${if (datos.azucar) "con azúcar" else "sin azúcar"}...")
                    Thread.sleep(1000)
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
        data class ComprobandoImporte(val tipoCafe: String, val importe : Double) : EstadoMaquinaCafe()
        data class ComprobandoRecursos(val tipoCafe: String) : EstadoMaquinaCafe()
        data class VerificandoAgua(val tipoCafe: String) : EstadoMaquinaCafe()
        data class VerificandoAzucar(val tipoCafe: String) : EstadoMaquinaCafe()
        data class RecursosOK(val tipoCafe: String) : EstadoMaquinaCafe()
        data class PreparandoCafe(val tipoCafe: String) : EstadoMaquinaCafe()
        data class DecidiendoAzucar(val tipoCafe: String) : EstadoMaquinaCafe()
        data class ConAzucar(val tipoCafe: String) : EstadoMaquinaCafe()
        data class SinAzucar(val A: String) : EstadoMaquinaCafe()
        data class Sirviendo(val tipoCafe: String, val azucar: Boolean) : EstadoMaquinaCafe()
        object EchandoVaso : EstadoMaquinaCafe()
        object Limpiando : EstadoMaquinaCafe()
        data class Error(val message: String) : EstadoMaquinaCafe()
    }

}