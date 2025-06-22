package servicios;

import modelo.Excusa;

public class AdministradorProntuario extends Observable {
    private static AdministradorProntuario instancia;

    private AdministradorProntuario() {}

    public static AdministradorProntuario getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorProntuario();
        }
        return instancia;
    }

    public void guardarProntuario(Excusa excusa) {
        System.out.println("📝 Prontuario guardado para " + excusa.getEmpleado().getNombre());
        this.notificar("Se guardó una excusa", excusa);
    }
}
