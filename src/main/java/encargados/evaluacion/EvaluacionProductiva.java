package encargados.evaluacion;

import encargados.Encargado;
import modelo.Excusa;
import servicios.AdministradorProntuario;
import servicios.IEmailSender;

public class EvaluacionProductiva implements IEvaluacionExcusa {
    private final IEmailSender emailSender;

    public EvaluacionProductiva(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void evaluar(Encargado encargado, Excusa excusa) {
        // Se evalúa normalmente
        if (excusa.getTipo().puedeSerAtendidaPor(encargado)) {
            AdministradorProntuario.getInstancia().guardarProntuario(excusa);
            encargado.procesar(excusa);
        } else {
            // Notifica al CTO antes de pasar
            emailSender.enviarEmail(
                    "cto@excusas.sa",
                    "notificador-productivo@excusas.sa",
                    "Encargado productivo derivando excusa",
                    "Un encargado derivó una excusa no apta para él."
            );
            encargado.pasarAlSiguiente(excusa);
        }
    }
}
