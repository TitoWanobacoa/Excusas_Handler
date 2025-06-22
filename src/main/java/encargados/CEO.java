package encargados;


import modelo.Excusa;

import servicios.IAdministradorProntuario;
import servicios.IEmailSender;
import servicios.IObservador;

public class CEO extends Encargado implements IObservador {
    private final IEmailSender emailSender;
    private final IAdministradorProntuario admin;

    public CEO(IEmailSender emailSender, IAdministradorProntuario admin) {
        this.emailSender = emailSender;
        this.admin = admin;
    }



    @Override
    public boolean aceptaCompleja() {
        return true;
    }

    @Override
    public void procesar(Excusa excusa) {
        admin.guardarProntuario(excusa);
        emailSender.enviarEmail(
                excusa.getEmpleado().getEmail(),
                "ceo@excusas.sa",
                "Aprobado",
                "Tu excusa ha sido aceptada por creatividad."
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
