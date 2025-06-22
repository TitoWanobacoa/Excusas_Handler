package tests.encargados;

import encargados.*;
import encargados.evaluacion.EvaluacionProductiva;
import modelo.Compleja;
import modelo.Empleado;
import modelo.Excusa;
import modelo.ITipoExcusa;
import servicios.AdministradorProntuario;
import servicios.EmailSenderFake;
import servicios.IEmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CeoTest {

    private CEO ceo;
    private IEmailSender emailSender;
    private Empleado empleado;
    private ITipoExcusa tipoExcusa;

    @BeforeEach
    void setUp() {
        emailSender = new EmailSenderFake();
        ceo = new CEO(emailSender, AdministradorProntuario.getInstancia()); // ✅
        ceo.setEstrategia(new EvaluacionProductiva(emailSender));

        AdministradorProntuario.getInstancia().agregarObservador(ceo);

        empleado = new Empleado("Martín", "martin@test.com", 101);
        tipoExcusa = new Compleja();
    }

    @Test
    void testCeoProcesaExcusaCompleja() {
        Excusa excusa = new Excusa(empleado, tipoExcusa);
        ceo.manejarExcusa(excusa);

        // Ver consola: email + notificación + mensaje de prontuario
    }
}
