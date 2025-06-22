package encargados;

import encargados.evaluacion.EvaluacionNormal;
import modelo.Excusa;
import servicios.AdministradorProntuario;
import servicios.IEmailSender;
import servicios.IObservador;

public class CEO extends Encargado implements IObservador {
    private final IEmailSender emailSender;

    public CEO(IEmailSender emailSender) {
        this.emailSender = emailSender;
        AdministradorProntuario.getInstancia().agregarObservador(this);
    }

    @Override
    public boolean aceptaCompleja() {
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        AdministradorProntuario.getInstancia().guardarProntuario(excusa);

        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                "ceo@excusas.sa",
                "Aprobado por creatividad",
                "Tu excusa ha sido aceptada. Se inició un prontuario."
        );
    }

    @Override
    public void actualizar(String mensaje, Object contexto) {
        if (contexto instanceof Excusa excusa) {
            System.out.println("🔔 CEO notificado: " + mensaje);
            System.out.println("  - Empleado: " + excusa.getEmpleado().getNombre());
            System.out.println("  - Motivo: " + excusa.getTipo().getClass().getSimpleName());
        }
    }
}
