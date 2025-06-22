package modelo;

import encargados.Encargado;

public class Compleja implements ITipoExcusa {
    @Override
    public boolean puedeSerAtendidaPor(Encargado encargado) {
        return encargado.aceptaCompleja();
    }
}
