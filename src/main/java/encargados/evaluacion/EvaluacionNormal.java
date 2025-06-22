package encargados.evaluacion;

import encargados.Encargado;
import modelo.Excusa;

public class EvaluacionNormal implements IEvaluacionExcusa {
    @Override
    public void evaluar(Encargado encargado, Excusa excusa) {
        if (excusa.getTipo().puedeSerAtendidaPor(encargado)) {
            encargado.procesar(excusa);
        } else {
            encargado.pasarAlSiguiente(excusa);
        }
    }
}
