package encargados;

import encargados.evaluacion.EvaluacionNormal;
import modelo.Excusa;

public class RechazadorExcusas extends Encargado {
    public RechazadorExcusas() {
        this.estrategia = new EvaluacionNormal();
    }

    @Override
    public void procesar(Excusa excusa) {
        System.out.println("Excusa rechazada: necesitamos pruebas contundentes.");
    }
}
