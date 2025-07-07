package tests.servicios;


import modelo.empleados.Empleado;
import modelo.excusas.Excusa;
import modelo.excusas.ITipoExcusa;
import modelo.excusas.Trivial;
import servicios.AdministradorProntuario;
import servicios.IObservador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import servicios.NotificacionExcusa;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorProntuarioTest {

    private AdministradorProntuario admin;
    private TestObservador observador;
    private Empleado empleado;
    private ITipoExcusa tipo;

    private static class TestObservador implements IObservador {
        Excusa excusaRecibida = null;

        @Override
        public void actualizar(NotificacionExcusa notificacion) {
            this.excusaRecibida = notificacion.getExcusa();
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
        Excusa excusa = new Excusa(empleado, tipo, "Excusa usada para guardar en prontuario");
        admin.guardarProntuario(excusa);

        assertNotNull(observador.excusaRecibida);
        assertEquals("Excusa usada para guardar en prontuario", observador.excusaRecibida.getDescripcion());
        assertEquals(excusa, observador.excusaRecibida);
    }

}
