package tests.encargados;

import encargados.*;
import encargados.evaluacion.EvaluacionNormal;
import encargados.evaluacion.EvaluacionVaga;
import modelo.Empleado;
import modelo.ITipoExcusa;
import modelo.Moderada;
import servicios.AdministradorProntuario;
import servicios.EmailSenderFake;
import servicios.IAdministradorProntuario;
import servicios.IEmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupervisorVagoTest {

    private ManejadorDeExcusa manejador;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        IEmailSender emailSender = new EmailSenderFake();
        IAdministradorProntuario admin = AdministradorProntuario.getInstancia();

        Encargado supervisor = new Supervisor(emailSender);
        Encargado ceo = new CEO(emailSender, admin);
        Encargado rechazador = new RechazadorExcusas();

        // ⚠️ Supervisor está en modo vago
        supervisor.setEstrategia(new EvaluacionVaga());

        // El CEO sí procesa si es necesario (modo normal)
        ceo.setEstrategia(new EvaluacionNormal());
        rechazador.setEstrategia(new EvaluacionNormal());

        manejador = new ManejadorDeExcusa(supervisor, ceo, rechazador);

        empleado = new Empleado("Ana", "ana@mail.com", 789);
    }

    @Test
    void testSupervisorVagoPasaExcusaYCeoLaProcesa() {
        ITipoExcusa excusaModerada = new Moderada();
        empleado.excusarse("Corte de luz", excusaModerada, manejador);

        // Como el supervisor es vago, no la procesa.
        // El CEO no acepta excusas moderadas, así que pasará también.
        // Rechazador debe imprimir mensaje final.
    }
}
