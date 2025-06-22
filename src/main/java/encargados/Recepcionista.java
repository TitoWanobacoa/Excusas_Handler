package encargados;

import encargados.evaluacion.EvaluacionNormal;
import modelo.Excusa;
import servicios.IEmailSender;

public class Recepcionista extends Encargado {
    private final IEmailSender emailSender;

    public Recepcionista(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean aceptaTrivial() {
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        String mensaje = "La licencia fue aceptada.";
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                "recepcionista@excusas.sa",
                "Motivo demora",
                mensaje
        );
    }
}
