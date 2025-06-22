package encargados;

import encargados.evaluacion.EvaluacionNormal;
import modelo.Excusa;
import servicios.IEmailSender;

public class Supervisor extends Encargado {
    private final IEmailSender emailSender;

    public Supervisor(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean aceptaModerada() {
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        // Aquí podrías distinguir si es por corte de luz o cuidado familiar
        String cuerpo = "La excusa fue aceptada.";
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                "supervisor@excusas.sa",
                "Consulta por excusa moderada",
                cuerpo
        );
    }
}
