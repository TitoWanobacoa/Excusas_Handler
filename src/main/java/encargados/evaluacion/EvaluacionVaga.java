package encargados.evaluacion;

import encargados.Encargado;
import modelo.Excusa;

public class EvaluacionVaga implements IEvaluacionExcusa {
    @Override
    public void evaluar(Encargado encargado, Excusa excusa) {
        System.out.println("Evaluación vaga. El encargado no se toma el trabajo.");
        encargado.pasarAlSiguiente(excusa);
    }
}
