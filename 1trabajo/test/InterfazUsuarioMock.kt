class InterfazUsuarioMock : InterfazUsuario {
    val mensajes = mutableListOf<String>()
    var azucar: Boolean = false

    constructor(azucar: Boolean = false) {
        this.azucar = azucar
    }

    override fun mostrarMensaje(mensaje: String) {
        mensajes.add(mensaje)
    }

}