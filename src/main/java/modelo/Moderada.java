package modelo;

import encargados.Encargado;

public class Moderada implements ITipoExcusa {
    @Override
    public boolean puedeSerAtendidaPor(Encargado encargado) {
        return encargado.aceptaModerada();
    }
}
