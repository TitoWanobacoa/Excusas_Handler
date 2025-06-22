package tests.encargados;

import encargados.*;
import encargados.evaluacion.EvaluacionNormal;
import modelo.Empleado;
import modelo.ITipoExcusa;
import servicios.AdministradorProntuario;
import servicios.EmailSenderFake;
import servicios.IAdministradorProntuario;
import servicios.IEmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Tipo que no acepta ningún encargado (falso para todos)
class TipoInvalido implements ITipoExcusa {
    @Override
    public boolean puedeSerAtendidaPor(Encargado encargado) {
        return false;
    }
}

class RechazoTest {

    private ManejadorDeExcusa manejador;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        IEmailSender emailSender = new EmailSenderFake();
        IAdministradorProntuario admin = AdministradorProntuario.getInstancia();


        Encargado recepcionista = new Recepcionista(emailSender);
        Encargado supervisor = new Supervisor(emailSender);
        Encargado gerente = new GerenteRRHH(emailSender);
        Encargado ceo = new CEO(emailSender, admin);
        Encargado rechazador = new RechazadorExcusas();

        recepcionista.setEstrategia(new EvaluacionNormal());
        supervisor.setEstrategia(new EvaluacionNormal());
        gerente.setEstrategia(new EvaluacionNormal());
        ceo.setEstrategia(new EvaluacionNormal());
        rechazador.setEstrategia(new EvaluacionNormal());

        manejador = new ManejadorDeExcusa(recepcionista, supervisor, gerente, ceo, rechazador);

        empleado = new Empleado("Lucía", "lucia@test.com", 456);
    }

    @Test
    void testExcusaRechazadaCuandoNadieLaAcepta() {
        ITipoExcusa excusaNoValida = new TipoInvalido();
        empleado.excusarse("Excusa no aceptada por nadie", excusaNoValida, manejador);

        // Ver consola: "Excusa rechazada: necesitamos pruebas contundentes."
    }
}
