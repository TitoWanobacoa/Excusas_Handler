package encargados;

import modelo.Empleado;
import modelo.Excusa;
import modelo.ITipoExcusa;

public class ManejadorDeExcusa {
    private final Encargado encargadoInicial;

    public ManejadorDeExcusa(Encargado... encargados) {
        for (int i = 0; i < encargados.length - 1; i++) {
            encargados[i].setSiguiente(encargados[i + 1]);
        }
        this.encargadoInicial = encargados[0];
    }

    public void recibirExcusa(String motivo, Empleado empleado, ITipoExcusa tipo) {
        Excusa excusa = new Excusa(empleado, tipo);
        encargadoInicial.manejarExcusa(excusa);
    }
}
