package tests.encargados;

import encargados.*;
import encargados.evaluacion.EvaluacionNormal;
import encargados.evaluacion.EvaluacionProductiva;
import modelo.*;
import servicios.AdministradorProntuario;
import servicios.EmailSenderFake;
import servicios.IEmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CadenaResponsabilidadTest {

    private ManejadorDeExcusa manejador;
    private Empleado empleadoTrivial;
    private Empleado empleadoModerado;
    private Empleado empleadoInverosimil;
    private Empleado empleadoComplejo;
    private Empleado empleadoNoValido;

    @BeforeEach
    void setUp() {
        IEmailSender emailSender = new EmailSenderFake();

        // Encargados
        Encargado recepcionista = new Recepcionista(emailSender);
        Encargado supervisor = new Supervisor(emailSender);
        Encargado gerente = new GerenteRRHH(emailSender);
        Encargado ceo = new CEO(emailSender);
        Encargado rechazador = new RechazadorExcusas();

        // Estrategias
        recepcionista.setEstrategia(new EvaluacionNormal());
        supervisor.setEstrategia(new EvaluacionNormal());
        gerente.setEstrategia(new EvaluacionNormal());
        ceo.setEstrategia(new EvaluacionProductiva(emailSender));
        rechazador.setEstrategia(new EvaluacionNormal());

        manejador = new ManejadorDeExcusa(recepcionista, supervisor, gerente, ceo, rechazador);

        // Empleados
        empleadoTrivial = new Empleado("Ana", "ana@mail.com", 1);
        empleadoModerado = new Empleado("Luis", "luis@mail.com", 2);
        empleadoInverosimil = new Empleado("Nora", "nora@mail.com", 3);
        empleadoComplejo = new Empleado("Raúl", "raul@mail.com", 4);
        empleadoNoValido = new Empleado("Extra", "extra@mail.com", 5);
    }

    @Test
    void testCadenaCompletaConDistintosTipos() {
        // Trivial → Recepcionista
        empleadoTrivial.excusarse("Me quedé dormida", new Trivial(), manejador);

        // Moderada → Supervisor
        empleadoModerado.excusarse("Cuidé a un familiar", new Moderada(), manejador);

        // Inverosímil → Gerente
        empleadoInverosimil.excusarse("Una paloma robó mi bici", new Inverosimil(), manejador);

        // Compleja → CEO
        empleadoComplejo.excusarse("Fui abducido por aliens", new Compleja(), manejador);

        // No aceptada por nadie → Rechazador
        ITipoExcusa tipoFalso = new ITipoExcusa() {
            @Override
            public boolean puedeSerAtendidaPor(Encargado encargado) {
                return false;
            }
        };
        empleadoNoValido.excusarse("No tenía ganas", tipoFalso, manejador);

        // Verificá la consola para asegurarte de que cada encargado respondió lo que corresponde.
    }
}
