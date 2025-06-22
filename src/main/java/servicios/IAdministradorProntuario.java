package servicios;

import modelo.Excusa;
import servicios.IObservador;

public interface IAdministradorProntuario {
    void guardarProntuario(Excusa excusa);
    void agregarObservador(IObservador o);
}
