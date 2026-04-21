package Model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private final ArrayList<Empleado> listaEmpleados;

    public Empresa(String nombre) {
        setNombre(nombre);
        this.listaEmpleados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la empresa no puede estar vacio.");
        }
        this.nombre = nombre;
    }

    public ArrayList<Empleado> getListaEmpleados() {
        return new ArrayList<>(listaEmpleados);
    }

    public void agregarEmpleado(Empleado empleado) {
        if (empleado == null) {
            throw new IllegalArgumentException("El empleado no puede ser nulo.");
        }
        if (buscarEmpleadoPorDocumento(empleado.getDocumento()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese documento.");
        }
        listaEmpleados.add(empleado);
        System.out.println("✓ Empleado agregado exitosamente.");
    }

    public void mostrarTodosLosEmpleados() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        listaEmpleados.forEach(Empleado::mostrarInformacion);
    }

    public Empleado buscarEmpleadoPorDocumento(String documento) {
        for (Empleado e : listaEmpleados) {
            if (e.getDocumento().equals(documento)) return e;
        }
        return null;
    }

    public Empleado buscarPorDocumento(String documento) {
        return buscarEmpleadoPorDocumento(documento);
    }

    public Empleado obtenerEmpleadoConMayorSalarioNeto() {
        if (listaEmpleados.isEmpty()) return null;
        Empleado max = listaEmpleados.get(0);
        float maxNeto = max.calcularSalarioNeto();
        for (Empleado e : listaEmpleados) {
            float neto = e.calcularSalarioNeto();
            if (neto > maxNeto) {
                maxNeto = neto;
                max = e;
            }
        }
        return max;
    }

    public float calcularNominaTotal() {
        return listaEmpleados.stream()
                .map(Empleado::calcularSalarioNeto)
                .reduce(0.0f, Float::sum);
    }

    public List<Empleado> empleadosConSalarioMayorA(float valor) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado.calcularSalarioNeto() > valor) {
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    public List<EmpleadoTemporal> empleadosTemporalesConMasDe100Horas() {
        ArrayList<EmpleadoTemporal> empleados = new ArrayList<>();
        for (Empleado empleado : listaEmpleados) {
            if (empleado instanceof EmpleadoTemporal temporal &&
                    temporal.getDiasTrabajados() * 8 > 100) {
                empleados.add(temporal);
            }
        }
        return empleados;
    }

    public void mostrarResumenesDePago() {
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay resúmenes de pago.");
            return;
        }
        listaEmpleados.forEach(e -> {
            System.out.println(e.generarResumenPago());
            System.out.println("-----------------------------");
        });
    }
}
