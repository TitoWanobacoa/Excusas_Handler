package encargados;

import encargados.evaluacion.EvaluacionNormal;
import modelo.Excusa;
import servicios.IEmailSender;

public class GerenteRRHH extends Encargado {
    private final IEmailSender emailSender;

    public GerenteRRHH(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public boolean aceptaInverosimil() {
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                "gerente@excusas.sa",
                "Excusa aceptada",
                "La excusa fue considerada válida."
        );
    }
}
