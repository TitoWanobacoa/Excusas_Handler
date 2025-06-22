package modelo;

import encargados.Encargado;

public interface ITipoExcusa {
    boolean puedeSerAtendidaPor(Encargado encargado);
}