package App;

public class EmpleadoPlanta extends Empleado {
    private String cargo;
    private int horasExtra;
    private float valorHoraExtra;
    private float auxilioTransporte;

    public EmpleadoPlanta(String nombre, String documento, int edad, float salarioBase,
                          CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension,
                          String cargo, int horasExtra, float valorHoraExtra, float auxilioTransporte) {
        super(nombre, documento, edad, salarioBase, categoria, descuentoSalud, descuentoPension);
        setCargo(cargo);
        setHorasExtra(horasExtra);
        setValorHoraExtra(valorHoraExtra);
        setAuxilioTransporte(auxilioTransporte);
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("El cargo no puede estar vacio.");
        }
        this.cargo = cargo;
    }

    public int getHorasExtra() { return horasExtra; }
    public void setHorasExtra(int horasExtra) {
        if (horasExtra < 0) throw new IllegalArgumentException("Las horas extra no pueden ser negativas.");
        this.horasExtra = horasExtra;
    }

    public float getValorHoraExtra() { return valorHoraExtra; }
    public void setValorHoraExtra(float valorHoraExtra) {
        if (valorHoraExtra < 0) throw new IllegalArgumentException("El valor de la hora extra no puede ser negativo.");
        this.valorHoraExtra = valorHoraExtra;
    }

    public float getAuxilioTransporte() { return auxilioTransporte; }
    public void setAuxilioTransporte(float auxilioTransporte) {
        if (auxilioTransporte < 0) throw new IllegalArgumentException("El auxilio de transporte no puede ser negativo.");
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    public float calcularSalarioBruto() {
        return getSalarioBase() + calcularBonificacionCategoria() +
                (horasExtra * valorHoraExtra) + auxilioTransporte;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo: Empleado de Planta");
        System.out.println("Cargo: " + cargo);
        System.out.println("Horas Extra: " + horasExtra);
        System.out.println("Valor Hora Extra: " + valorHoraExtra);
        System.out.println("Auxilio Transporte: " + auxilioTransporte);
        System.out.println("Salario Bruto: " + calcularSalarioBruto());
        System.out.println("Salario Neto: " + calcularSalarioNeto());
        System.out.println("-----------------------------");
    }
}
