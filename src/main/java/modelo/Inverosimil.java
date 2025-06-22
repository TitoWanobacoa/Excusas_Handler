package modelo;

import encargados.Encargado;

public class Inverosimil implements ITipoExcusa {
    @Override
    public boolean puedeSerAtendidaPor(Encargado encargado) {
        return encargado.aceptaInverosimil();
    }
}
