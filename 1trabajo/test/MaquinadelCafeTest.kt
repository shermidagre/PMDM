import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MaquinadelCafeTest {

    @Test
    fun `test limpiar maquina pone estado Idle`() {
        MaquinadelCafe.estadoactual = MaquinadelCafe.estadomaquinacafe.haciendocafe
        MaquinadelCafe.limpiarmaquina()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.estadomaquinacafe.Idle)
    }

    @Test
    fun `test hacer cafe cambia estado a sirviendocafe`() {
        MaquinadelCafe.estadoactual = MaquinadelCafe.estadomaquinacafe.Idle
        MaquinadelCafe.hacercafe()
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.estadomaquinacafe.sirviendocafe)
    }

    @Test
    fun `test estado error`() {
        MaquinadelCafe.estadoactual = MaquinadelCafe.estadomaquinacafe.Error("Sin agua")
        assertTrue(MaquinadelCafe.estadoactual is MaquinadelCafe.estadomaquinacafe.Error)
        val error = MaquinadelCafe.estadoactual as MaquinadelCafe.estadomaquinacafe.Error
        assertEquals("Sin agua", error.message)
    }

    @Test
    fun `test pedircafe no cambia estadoactual`() {
        val estadoInicial = MaquinadelCafe.estadoactual
        MaquinadelCafe.pedircafe("Café solo")
        assertEquals(estadoInicial, MaquinadelCafe.estadoactual)
    }

    @Test
    fun `test cogervaso no cambia estadoactual`() {
        val estadoInicial = MaquinadelCafe.estadoactual
        MaquinadelCafe.cogervaso()
        assertEquals(estadoInicial, MaquinadelCafe.estadoactual)
    }
}
