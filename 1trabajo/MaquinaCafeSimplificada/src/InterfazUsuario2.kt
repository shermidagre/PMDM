/**
 * Clase base para gestionar la interacción de la máquina con el usuario (Consola/GUI).
 *
 * Está marcada como [open] para permitir que otras clases hereden y sustituyan
 * (override) su comportamiento, por ejemplo, para una interfaz gráfica.
 */
open class InterfazUsuario {

    /**
     * Muestra un mensaje estándar al usuario.
     * @param mensaje La cadena de texto a imprimir en la salida estándar.
     */
    open fun mostrarMensaje(mensaje: String) {
        println(mensaje)
    }

    /**
     * Solicita y valida el tipo de café deseado por el usuario.
     *
     * Utiliza un bucle [do-while] para asegurar que la entrada del usuario
     * coincida con la lista de cafés válidos.
     * @return El tipo de café seleccionado por el usuario, siempre en minúsculas.
     */
    open fun pedirTipoCafe(): String {
        val cafesValidos = listOf("solo", "leche", "capuchino")
        var tipo: String?
        do {
            mostrarMensaje("¿Qué café deseas? (solo, leche, capuchino): ")
            // readLine() devuelve String?, se usa el operador Elvis para manejar null
            tipo = readLine()?.lowercase()
            if (tipo !in cafesValidos) {
                mostrarError("Tipo de café no válido.")
            }
        } while (tipo !in cafesValidos)
        // Se usa !! porque el do-while garantiza que 'tipo' no será null al salir.
        return tipo!!
    }

    /**
     * Pregunta al usuario si desea añadir azúcar.
     * @return true si el usuario responde 's' o 'si', false en caso contrario.
     */
    open fun pedirAzucar(): Boolean {
        mostrarMensaje("¿Quieres azúcar? (s|si / n|no): ")
        val respuesta = readLine()?.lowercase()
        return respuesta == "s" || respuesta == "si"
    }

    /**
     * Muestra un mensaje de error formateado al usuario.
     * @param mensaje El cuerpo del mensaje de error.
     */
    open fun mostrarError(mensaje: String) {
        mostrarMensaje("ERROR: $mensaje")
    }

    /**
     * Función pendiente de implementación (placeholder).
     * @throws NotImplementedError Siempre lanza un error indicando que la función no está lista.
     */
    open fun pedirOrden(): Boolean {
        // En Kotlin, TODO es una función que lanza un error. ¡Mucho mejor que dejar un método vacío en Java!
        TODO("Not yet implemented")
    }
}