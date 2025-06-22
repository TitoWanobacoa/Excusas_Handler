package modelo;

public class Excusa {
    private final Empleado empleado;
    private final ITipoExcusa tipo;

    public Excusa(Empleado empleado, ITipoExcusa tipo) {
        this.empleado = empleado;
        this.tipo = tipo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public ITipoExcusa getTipo() {
        return tipo;
    }
}
