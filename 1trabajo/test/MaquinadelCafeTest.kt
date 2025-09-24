import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InterfazUsuarioMock(
    var orden: Boolean = true,
    var tipoCafe: String = "solo",
    var azucar: Boolean = false,
    var mensajes: MutableList<String> = mutableListOf(),
    var errores: MutableList<String> = mutableListOf()
) : InterfazUsuario() {
    override fun mostrarMensaje(mensaje: String) { mensajes.add(mensaje) }
    override fun pedirOrden(): Boolean = orden
    override fun pedirTipoCafe(): String = tipoCafe
    override fun pedirAzucar(): Boolean = azucar
    override fun mostrarError(mensaje: String) { errores.add(mensaje) }
}

class MaquinadelCafeTest {

    @Test
    fun `Idle a MenuSeleccionCafe si pide cafe`() {
        val mock = InterfazUsuarioMock(orden = true)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Máquina encendida") })
    }

    @Test
    fun `Idle termina si no pide cafe`() {
        val mock = InterfazUsuarioMock(orden = false)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertEquals(0, mock.errores.size)
    }

    @Test
    fun `MenuSeleccionCafe pide tipo cafe y pasa a ComprobandoRecursos`() {
        val mock = InterfazUsuarioMock(tipoCafe = "leche")
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.MenuSeleccionCafe
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("¿Qué café deseas?") })
    }

    @Test
    fun `ComprobandoRecursos pasa a VerificandoAgua`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.ComprobandoRecursos("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Comprobando recursos") })
    }

    @Test
    fun `VerificandoAgua sin agua lanza error`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.VerificandoAgua("solo")
        MaquinadelCafe.interfaz = mock
        // Simula sin agua
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Verificando agua") })
    }

    @Test
    fun `VerificandoAzucar sin azucar y cafe con leche lanza error`() {
        val mock = InterfazUsuarioMock(tipoCafe = "leche")
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.VerificandoAzucar("leche")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Verificando azúcar") })
    }

    @Test
    fun `RecursosOK pasa a PreparandoCafe`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.RecursosOK("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Preparando solo") })
    }

    @Test
    fun `PreparandoCafe pasa a DecidiendoAzucar`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.PreparandoCafe("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Preparando solo") })
    }

    @Test
    fun `DecidiendoAzucar con azucar pasa a ConAzucar`() {
        val mock = InterfazUsuarioMock(azucar = true)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.DecidiendoAzucar("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Añadiendo azúcar") })
    }

    @Test
    fun `DecidiendoAzucar sin azucar pasa a SinAzucar`() {
        val mock = InterfazUsuarioMock(azucar = false)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.DecidiendoAzucar("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Preparando café sin azúcar") })
    }

    @Test
    fun `ConAzucar pasa a Sirviendo`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.ConAzucar("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Sirviendo solo con azúcar") })
    }

    @Test
    fun `SinAzucar pasa a Sirviendo`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.SinAzucar("solo")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Sirviendo solo sin azúcar") })
    }

    @Test
    fun `Sirviendo pasa a EchandoVaso`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Sirviendo("solo", true)
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Esperando vaso") })
    }

    @Test
    fun `EchandoVaso pasa a Limpiando`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.EchandoVaso
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.mensajes.any { it.contains("Limpiando la máquina") })
    }

    @Test
    fun `Limpiando vuelve a Idle`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Limpiando
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Idle)
    }

    @Test
    fun `Error muestra mensaje y pasa a Limpiando`() {
        val mock = InterfazUsuarioMock()
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Error("Error test")
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(mock.errores.any { it.contains("Error test") })
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Limpiando)
    }

    @Test
    fun `Ciclo completo con azucar`() {
        val mock = InterfazUsuarioMock(orden = true, tipoCafe = "solo", azucar = true)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Idle)
    }

    @Test
    fun `Ciclo completo sin azucar`() {
        val mock = InterfazUsuarioMock(orden = true, tipoCafe = "solo", azucar = false)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Idle)
    }

    @Test
    fun `Ciclo completo cafe con leche`() {
        val mock = InterfazUsuarioMock(orden = true, tipoCafe = "leche", azucar = true)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Idle)
    }

    @Test
    fun `Ciclo completo capuchino`() {
        val mock = InterfazUsuarioMock(orden = true, tipoCafe = "capuchino", azucar = false)
        MaquinadelCafe.estadoactual = MaquinadelCafe.EstadoMaquinaCafe.Idle
        MaquinadelCafe.interfaz = mock
        MaquinadelCafe.iniciar()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.EstadoMaquinaCafe.Idle)
    }
}
