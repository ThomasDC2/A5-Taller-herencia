package App;

import Model.*;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Empresa Demo");

        Empleado empleadoPlanta = new EmpleadoPlanta(
                "Laura Gomez", "1010", 30, 2500000f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                "Analista", 10, 20000f, 162000f
        );

        Empleado empleadoTemporal = new EmpleadoTemporal(
                "Carlos Perez", "2020", 24, 1400000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                22, 65000f
        );

        Empleado empleadoVentas = new EmpleadoVentas(
                "Maria Rodriguez", "3030", 28, 1800000f,
                CategoriaEmpleado.SENIOR, 4f, 4f,
                12000000f, 3.5f
        );

        empresa.agregarEmpleado(empleadoPlanta);
        empresa.agregarEmpleado(empleadoTemporal);
        empresa.agregarEmpleado(empleadoVentas);

        System.out.println("\n=== LISTA DE EMPLEADOS ===");
        empresa.mostrarTodosLosEmpleados();

        System.out.println("\n=== BUSQUEDA POR DOCUMENTO ===");
        Empleado encontrado = empresa.buscarEmpleadoPorDocumento("2020");
        if (encontrado != null) {
            encontrado.mostrarInformacion();
        } else {
            System.out.println("Empleado no encontrado.");
        }

        System.out.println("\n=== EMPLEADO CON MAYOR SALARIO NETO ===");
        Empleado mayorSalario = empresa.obtenerEmpleadoConMayorSalarioNeto();
        if (mayorSalario != null) {
            System.out.println("Nombre: " + mayorSalario.getNombre());
            System.out.println("Documento: " + mayorSalario.getDocumento());
            System.out.println("Salario neto: " + mayorSalario.calcularSalarioNeto());
        }

        System.out.println("\n=== NOMINA TOTAL ===");
        System.out.println("Total a pagar: " + empresa.calcularNominaTotal());

        System.out.println("\n=== RESUMENES DE PAGO ===");
        empresa.mostrarResumenesDePago();
    }
}
