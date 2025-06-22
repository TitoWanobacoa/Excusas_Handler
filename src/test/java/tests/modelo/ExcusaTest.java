package tests.modelo;

import modelo.Empleado;
import modelo.Excusa;
import modelo.Trivial;
import modelo.ITipoExcusa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExcusaTest {

    @Test
    void testCrearExcusa() {
        Empleado empleado = new Empleado("Laura", "laura@mail.com", 1001);
        ITipoExcusa tipo = new Trivial();

        Excusa excusa = new Excusa(empleado, tipo);

        assertEquals("Laura", excusa.getEmpleado().getNombre());
        assertEquals("laura@mail.com", excusa.getEmpleado().getEmail());
        assertEquals(1001, excusa.getEmpleado().getNroLegajo());
        assertEquals(tipo, excusa.getTipo());
    }
}
