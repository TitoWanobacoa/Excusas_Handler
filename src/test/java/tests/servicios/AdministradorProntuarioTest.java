package tests.servicios;

import encargados.Encargado;
import modelo.Empleado;
import modelo.Excusa;
import modelo.ITipoExcusa;
import modelo.Trivial;
import servicios.AdministradorProntuario;
import servicios.IObservador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorProntuarioTest {

    private AdministradorProntuario admin;
    private TestObservador observador;
    private Empleado empleado;
    private ITipoExcusa tipo;

    private static class TestObservador implements IObservador {
        String mensajeRecibido = null;
        Excusa excusaRecibida = null;

        @Override
        public void actualizar(String mensaje, Object contexto) {
            this.mensajeRecibido = mensaje;
            if (contexto instanceof Excusa excusa) {
                this.excusaRecibida = excusa;
            }
        }
    }

    @BeforeEach
    void setUp() {
        admin = AdministradorProntuario.getInstancia();
        observador = new TestObservador();
        admin.agregarObservador(observador);

        empleado = new Empleado("Pablo", "pablo@test.com", 707);
        tipo = new Trivial();
    }

    @Test
    void testNotificaObservadoresAlGuardarExcusa() {
        Excusa excusa = new Excusa(empleado, tipo);
        admin.guardarProntuario(excusa);

        assertNotNull(observador.mensajeRecibido);
        assertEquals("Se guardó una excusa", observador.mensajeRecibido);
        assertNotNull(observador.excusaRecibida);
        assertEquals(excusa, observador.excusaRecibida);
    }
}
