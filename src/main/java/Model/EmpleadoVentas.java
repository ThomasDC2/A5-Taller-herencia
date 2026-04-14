package Model;

public class EmpleadoVentas extends Empleado {
    private float totalVentas;
    private float porcentajeComision;

    public EmpleadoVentas(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          float totalVentas, float porcentajeComision) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);

        if (totalVentas < 0) throw new IllegalArgumentException("El total de ventas no puede ser negativo.");
        if (porcentajeComision < 0 || porcentajeComision > 100)
            throw new IllegalArgumentException("El porcentaje de comisión debe estar entre 0 y 100.");

        setTotalVentas(totalVentas);
        setPorcentajeComision(porcentajeComision);
    }



    public float getTotalVentas() { return totalVentas; }
    public void setTotalVentas(float totalVentas) {
        if (totalVentas < 0) throw new IllegalArgumentException("El total de ventas no puede ser negativo.");
        this.totalVentas = totalVentas;
    }

    public float getPorcentajeComision() { return porcentajeComision; }
    public void setPorcentajeComision(float porcentajeComision) {
        validarPorcentaje(porcentajeComision, "comision");
        this.porcentajeComision = porcentajeComision;
    }

    @Override
    public float calcularSalarioBruto() {
        return getSalarioBase() + calcularBonificacionCategoria() +
                (totalVentas * porcentajeComision / 100.0f);
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Empleado de Ventas");
        System.out.println("Total Ventas: " + totalVentas);
        System.out.println("Porcentaje Comisión: " + porcentajeComision + "%");
        System.out.println("Salario Bruto: " + calcularSalarioBruto());
        System.out.println("Salario Neto: " + calcularSalarioNeto());
        System.out.println("-----------------------------");
    }
}
