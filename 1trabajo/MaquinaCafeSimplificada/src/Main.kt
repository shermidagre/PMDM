
fun main() {
    MaquinadelCafe.iniciar()
}

/**
 * Este objeto Singleton gestiona el flujo de trabajo, las transiciones de estado y la interacción con el usuario a través de la interfaz.
 */
object MaquinadelCafe {
    /**
     * Almacena el estado actual de la máquina.
     * Inicialmente, la máquina se encuentra en [EstadoMaquinaCafe.Idle], esperando una orden.
     */

    var estadoactual: EstadoMaquinaCafe = EstadoMaquinaCafe.Idle

    /**
     * Instancia de la interfaz para interactuar con el usuario (mostrar mensajes, pedir input).
     */

    var interfaz = InterfazUsuario()

    /**
     * Mantiene un bucle infinito (`while (true)`) que gestiona las transiciones de estado basadas en el valor de [estadoactual].
     * La exhaustividad del 'when' está garantizada por la clase sellada [EstadoMaquinaCafe].
     */
    fun iniciar() {
        while (true) {
            when (estadoactual) {
                is EstadoMaquinaCafe.Idle -> {
                    interfaz.mostrarMensaje("\nSelecciona tu producto\n")
                    estadoactual = EstadoMaquinaCafe.MenuSeleccionCafe
                }
                is EstadoMaquinaCafe.MenuSeleccionCafe -> {
                    // El smart cast de Kotlin permite acceder al tipo
                    val tipoCafe = interfaz.pedirTipoCafe()
                    estadoactual = EstadoMaquinaCafe.PreparandoCafe(tipoCafe)
                }

                is EstadoMaquinaCafe.PreparandoCafe -> {
                    // Mejor práctica: asignar a una variable en el when para evitar el cast feo.
                    val estadoPrep = estadoactual as EstadoMaquinaCafe.PreparandoCafe
                    interfaz.mostrarMensaje("Preparando ${estadoPrep.tipoCafe}...")
                    Thread.sleep(500)
                    estadoactual = EstadoMaquinaCafe.Sirviendo(estadoPrep.tipoCafe)
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
                    val estadoError = estadoactual as EstadoMaquinaCafe.Error
                    interfaz.mostrarError(estadoError.message)
                    // La máquina permanece en estado Error hasta que se rompa el bucle.
                    Thread.sleep(500)
                }
            }
        }
    }

    /**
     * Clase sellada (sealed class) que define todos los estados posibles de la máquina.
     */
    sealed class EstadoMaquinaCafe {
        /** La máquina está inactiva y lista para recibir una nueva orden. */
        object Idle : EstadoMaquinaCafe()
        /** El usuario está en el proceso de elegir el tipo de bebida. */
        object MenuSeleccionCafe : EstadoMaquinaCafe()

        /** * La máquina está ocupada procesando la bebida.
         * @param tipoCafe El nombre del café que se está preparando (ej: "solo").
         */
        data class PreparandoCafe(val tipoCafe: String) : EstadoMaquinaCafe()

        /** * La bebida ha sido preparada y se está dispensando.
         * @param tipoCafe El nombre del café que se está sirviendo.
         */
        data class Sirviendo(val tipoCafe: String) : EstadoMaquinaCafe()

        /** La máquina está ejecutando un ciclo de limpieza para mantenimiento. */
        object Limpiando : EstadoMaquinaCafe()

        /** * Un estado de error irrecuperable ha ocurrido y la máquina requiere atención.
         * @param message El mensaje detallado del error a mostrar al usuario.
         */
        data class Error(val message: String) : EstadoMaquinaCafe()
    }

}
