package App;

import Model.*;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Ingrese el nombre de la empresa: ");
        Empresa empresa = new Empresa(SCANNER.nextLine());

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    registrarEmpleado(empresa);
                    break;
                case 2:
                    empresa.mostrarTodosLosEmpleados();
                    break;
                case 3:
                    buscarEmpleado(empresa);
                    break;
                case 4:
                    mostrarMayorSalario(empresa);
                    break;
                case 5:
                    System.out.println("Nomina total: " + empresa.calcularNominaTotal());
                    break;
                case 6:
                    empresa.mostrarResumenesDePago();
                    break;
                case 7:
                    continuar = false;
                    System.out.println("Programa finalizado.");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Mostrar todos los empleados");
        System.out.println("3. Buscar empleado por documento");
        System.out.println("4. Mostrar empleado con mayor salario neto");
        System.out.println("5. Calcular nomina total");
        System.out.println("6. Mostrar resumenes de pago");
        System.out.println("7. Salir");
    }

    private static void registrarEmpleado(Empresa empresa) {
        System.out.println("\nTipos de empleado:");
        System.out.println("1. Empleado de planta");
        System.out.println("2. Empleado temporal");
        System.out.println("3. Empleado de ventas");

        int tipo = leerEntero("Seleccione el tipo de empleado: ");

        String nombre = leerTexto("Nombre: ");
        String documento = leerTexto("Documento: ");
        int edad = leerEntero("Edad: ");
        float salarioBase = leerFloat("Salario base: ");
        CategoriaEmpleado categoria = leerCategoria();
        float descuentoSalud = leerFloat("Descuento salud (%): ");
        float descuentoPension = leerFloat("Descuento pension (%): ");

        try {
            Empleado empleado;

            switch (tipo) {
                case 1:
                    String cargo = leerTexto("Cargo: ");
                    int horasExtra = leerEntero("Horas extra: ");
                    float valorHoraExtra = leerFloat("Valor hora extra: ");
                    float auxilioTransporte = leerFloat("Auxilio transporte: ");

                    empleado = new EmpleadoPlanta(
                            nombre, documento, edad, salarioBase,
                            categoria, descuentoSalud, descuentoPension,
                            cargo, horasExtra, valorHoraExtra, auxilioTransporte
                    );
                    break;

                case 2:
                    int diasTrabajados = leerEntero("Dias trabajados: ");
                    float valorDia = leerFloat("Valor por dia: ");

                    empleado = new EmpleadoTemporal(
                            nombre, documento, edad, salarioBase,
                            categoria, descuentoSalud, descuentoPension,
                            diasTrabajados, valorDia
                    );
                    break;

                case 3:
                    float totalVentas = leerFloat("Total ventas: ");
                    float porcentajeComision = leerFloat("Porcentaje comision: ");

                    empleado = new EmpleadoVentas(
                            nombre, documento, edad, salarioBase,
                            categoria, descuentoSalud, descuentoPension,
                            totalVentas, porcentajeComision
                    );
                    break;

                default:
                    System.out.println("Tipo de empleado invalido.");
                    return;
            }

            empresa.agregarEmpleado(empleado);

        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar empleado: " + e.getMessage());
        }
    }

    private static void buscarEmpleado(Empresa empresa) {
        String documento = leerTexto("Ingrese el documento a buscar: ");
        Empleado empleado = empresa.buscarEmpleadoPorDocumento(documento);

        if (empleado != null) {
            empleado.mostrarInformacion();
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void mostrarMayorSalario(Empresa empresa) {
        Empleado empleado = empresa.obtenerEmpleadoConMayorSalarioNeto();

        if (empleado != null) {
            System.out.println("\n=== EMPLEADO CON MAYOR SALARIO NETO ===");
            System.out.println("Nombre: " + empleado.getNombre());
            System.out.println("Documento: " + empleado.getDocumento());
            System.out.println("Salario neto: " + empleado.calcularSalarioNeto());
        } else {
            System.out.println("No hay empleados registrados.");
        }
    }


    private static CategoriaEmpleado leerCategoria() {
        System.out.println("\nCategorias disponibles:");
        System.out.println("1. JUNIOR");
        System.out.println("2. SEMI_SENIOR");
        System.out.println("3. SENIOR");


        while (true) {
            int opcion = leerEntero("Seleccione la categoria: ");

            switch (opcion) {
                case 1:
                    return CategoriaEmpleado.JUNIOR;
                case 2:
                    return CategoriaEmpleado.SEMI_SENIOR;
                case 3:
                    return CategoriaEmpleado.SENIOR;
                default:
                    System.out.println("Categoria invalida. Intente de nuevo.");
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static float leerFloat(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Float.parseFloat(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = SCANNER.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Este campo no puede estar vacio.");
        }
    }
}
