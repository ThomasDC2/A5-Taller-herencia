package Model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {

    @Test
    void salarioBrutoVentas() {
        EmpleadoVentas e = new EmpleadoVentas(
                "Luis", "101", 28, 2000f,
                CategoriaEmpleado.SENIOR, 4f, 4f,
                5000f, 10f
        );

        float esperado = 2000f + (2000f * 0.15f) + (5000f * 0.10f);

        assertEquals(esperado, e.calcularSalarioBruto(), 0.001f);
    }

    @Test
    void salarioTemporalNoEsCero() {
        EmpleadoTemporal e = new EmpleadoTemporal(
                "Ana", "102", 25, 1500f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                20, 80f
        );

        assertNotEquals(0f, e.calcularSalarioNeto(), 0.001f);
    }

    @Test
    void empleadosGuardados() {
        Empresa empresa = new Empresa("Mi Empresa");

        Empleado e1 = new EmpleadoPlanta(
                "Juan", "123", 30, 2000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                "Auxiliar", 2, 50f, 100f
        );

        Empleado e2 = new EmpleadoTemporal(
                "Ana", "456", 25, 1500f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                20, 80f
        );

        empresa.agregarEmpleado(e1);
        empresa.agregarEmpleado(e2);

        assertIterableEquals(List.of(e1, e2), empresa.getListaEmpleados());
    }

    @Test
    void bonificacionJunior() {
        EmpleadoPlanta e = new EmpleadoPlanta(
                "Juan", "111", 30, 2000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                "Auxiliar", 2, 50f, 100f
        );

        assertTrue(e.calcularBonificacionCategoria() > 0);
    }

    @Test
    void salarioNoNegativo() {
        EmpleadoTemporal e = new EmpleadoTemporal(
                "Ana", "222", 25, 1500f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                20, 80f
        );

        assertTrue(e.calcularSalarioNeto() >= 0);
    }

    @Test
    void buscarInexistente() {
        Empresa empresa = new Empresa("Mi Empresa");

        assertNull(empresa.buscarEmpleadoPorDocumento("999999"));
    }

    @Test
    void salarioBaseNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmpleadoPlanta(
                    "Juan", "333", 30, -2000f,
                    CategoriaEmpleado.JUNIOR, 4f, 4f,
                    "Auxiliar", 2, 50f, 100f
            );
        });
    }

    @Test
    void salarioMayorA() {
        Empresa empresa = new Empresa("Mi Empresa");

        Empleado e1 = new EmpleadoPlanta(
                "Juan", "123", 30, 1000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                "Auxiliar", 0, 50f, 0f
        );

        Empleado e2 = new EmpleadoVentas(
                "Luis", "456", 28, 3000f,
                CategoriaEmpleado.SENIOR, 4f, 4f,
                5000f, 10f
        );

        Empleado e3 = new EmpleadoTemporal(
                "Ana", "789", 25, 1500f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                25, 120f
        );

        empresa.agregarEmpleado(e1);
        empresa.agregarEmpleado(e2);
        empresa.agregarEmpleado(e3);

        assertIterableEquals(
                List.of(e2, e3),
                empresa.empleadosConSalarioMayorA(2000f)
        );
    }

    @Test
    void buscarPorDocumento() {
        Empresa empresa = new Empresa("Mi Empresa");

        assertNull(empresa.buscarPorDocumento("000000"));
    }

    @Test
    void noDuplicados() {
        Empresa empresa = new Empresa("Mi Empresa");

        Empleado e1 = new EmpleadoPlanta(
                "Juan", "123", 30, 2000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                "Auxiliar", 2, 50f, 100f
        );

        Empleado e2 = new EmpleadoTemporal(
                "Pedro", "123", 26, 1500f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                20, 80f
        );

        empresa.agregarEmpleado(e1);

        assertThrows(IllegalArgumentException.class, () -> empresa.agregarEmpleado(e2));
    }

    @Test
    void mayorSalario() {
        Empresa empresa = new Empresa("Mi Empresa");

        Empleado e1 = new EmpleadoPlanta(
                "Juan", "123", 30, 2000f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                "Auxiliar", 2, 50f, 100f
        );

        Empleado e2 = new EmpleadoVentas(
                "Luis", "456", 28, 3500f,
                CategoriaEmpleado.SENIOR, 4f, 4f,
                8000f, 10f
        );

        Empleado e3 = new EmpleadoTemporal(
                "Ana", "789", 25, 1500f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                20, 80f
        );

        empresa.agregarEmpleado(e1);
        empresa.agregarEmpleado(e2);
        empresa.agregarEmpleado(e3);

        assertEquals(e2, empresa.obtenerEmpleadoConMayorSalarioNeto());
    }

    @Test
    void temporalesMasDe100Horas() {
        Empresa empresa = new Empresa("Mi Empresa");

        EmpleadoTemporal e1 = new EmpleadoTemporal(
                "Ana", "101", 25, 1500f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                10, 80f
        );

        EmpleadoTemporal e2 = new EmpleadoTemporal(
                "Luis", "102", 27, 1600f,
                CategoriaEmpleado.SEMI_SENIOR, 4f, 4f,
                15, 90f
        );

        empresa.agregarEmpleado(e1);
        empresa.agregarEmpleado(e2);

        assertIterableEquals(
                List.of(e2),
                empresa.empleadosTemporalesConMasDe100Horas()
        );
    }

    @Test
    void salarioPlantaMayorABase() {
        EmpleadoPlanta e = new EmpleadoPlanta(
                "Juan", "555", 30, 2000f,
                CategoriaEmpleado.SENIOR, 4f, 4f,
                "Supervisor", 5, 100f, 150f
        );

        assertTrue(e.calcularSalarioNeto() > e.getSalarioBase());
    }

    @Test
    void salarioTemporalMayorACero() {
        EmpleadoTemporal e = new EmpleadoTemporal(
                "Ana", "666", 25, 1500f,
                CategoriaEmpleado.JUNIOR, 4f, 4f,
                20, 80f
        );

        assertTrue(e.calcularSalarioNeto() > 0);
    }
}
