package tests.encargados;

import modelo.empleados.encargados.*;
import modelo.empleados.encargados.evaluacion.EvaluacionNormal;
import modelo.empleados.encargados.evaluacion.EvaluacionVaga;
import modelo.empleados.Empleado;
import modelo.excusas.ITipoExcusa;
import modelo.excusas.Moderada;
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

        Encargado supervisor = new Supervisor("Supervisor", "supervision@excusas.sa", 202, emailSender);
        Encargado ceo = new CEO("CEO", "ceo@excusas.sa", 999, emailSender, admin);
        Encargado rechazador = new RechazadorExcusas("Rechazador", "rechazo@excusas.sa", 203, new EmailSenderFake());



        supervisor.setEstrategia(new EvaluacionVaga());


        ceo.setEstrategia(new EvaluacionNormal());
        rechazador.setEstrategia(new EvaluacionNormal());

        manejador = new ManejadorDeExcusa(supervisor, ceo, rechazador);

        empleado = new Empleado("Ana", "ana@mail.com", 789);
    }

    @Test
    void testSupervisorVagoPasaExcusaYCeoLaProcesa() {
        ITipoExcusa excusaModerada = new Moderada();
        empleado.excusarse("Corte de luz", excusaModerada, manejador);


    }
}
