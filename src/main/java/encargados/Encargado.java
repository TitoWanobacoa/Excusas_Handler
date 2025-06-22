package encargados;

import encargados.evaluacion.IEvaluacionExcusa;
import modelo.Excusa;

public abstract class Encargado {
    private Encargado siguiente;
    protected IEvaluacionExcusa estrategia;

    public void setSiguiente(Encargado siguiente) {
        this.siguiente = siguiente;
    }

    public Encargado getSiguiente() {
        return siguiente;
    }

    // Template Method
    public final void manejarExcusa(Excusa excusa) {
        estrategia.evaluar(this, excusa);
    }

    public void pasarAlSiguiente(Excusa excusa) {
        if (siguiente != null) {
            siguiente.manejarExcusa(excusa);
        } else {
            System.out.println("Excusa rechazada: necesitamos pruebas contundentes.");
        }
    }

    // Hooks para definir qué tipo de excusas puede atender
    public boolean aceptaTrivial()     { return false; }
    public boolean aceptaModerada()    { return false; }
    public boolean aceptaCompleja()    { return false; }
    public boolean aceptaInverosimil() { return false; }

    // Metodo que define el comportamiento específico al procesar
    public abstract void procesar(Excusa excusa);

    public void setEstrategia(IEvaluacionExcusa estrategia) {
        this.estrategia = estrategia;
    }

}
