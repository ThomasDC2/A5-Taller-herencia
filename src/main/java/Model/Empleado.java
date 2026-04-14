package Model;

public abstract class Empleado {
    private String nombre;
    private String documento;
    private int edad;
    private float salarioBase;
    private CategoriaEmpleado categoria;
    private float descuentoSalud;
    private float descuentoPension;

    public Empleado(String nombre, String documento, int edad, float salarioBase,
                    CategoriaEmpleado categoria, float descuentoSalud, float descuentoPension) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
        if (descuentoSalud < 0 || descuentoSalud > 100 || descuentoPension < 0 || descuentoPension > 100) {
            throw new IllegalArgumentException("Los descuentos de salud y pensión deben estar entre 0 y 100.");
        }

        setNombre(nombre);
        setDocumento(documento);
        setEdad(edad);
        setSalarioBase(salarioBase);
        setCategoria(categoria);
        setDescuentoSalud(descuentoSalud);
        setDescuentoPension(descuentoPension);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre;
    }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) {
        if (documento == null || documento.isBlank()) {
            throw new IllegalArgumentException("El documento no puede estar vacio.");
        }
        this.documento = documento;
    }

    public int getEdad() { return edad; }
    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }

    public float getSalarioBase() { return salarioBase; }
    public void setSalarioBase(float salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo.");
        }
        this.salarioBase = salarioBase;
    }

    public CategoriaEmpleado getCategoria() { return categoria; }
    public void setCategoria(CategoriaEmpleado categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoria no puede ser nula.");
        }
        this.categoria = categoria;
    }

    public float getDescuentoSalud() { return descuentoSalud; }
    public void setDescuentoSalud(float descuentoSalud) {
        validarPorcentaje(descuentoSalud, "salud");
        this.descuentoSalud = descuentoSalud;
    }

    public float getDescuentoPension() { return descuentoPension; }
    public void setDescuentoPension(float descuentoPension) {
        validarPorcentaje(descuentoPension, "pension");
        this.descuentoPension = descuentoPension;
    }


    public abstract float calcularSalarioBruto();

    public float calcularBonificacionCategoria() {
        return switch (categoria) {
            case JUNIOR -> salarioBase * 0.05f;
            case SEMI_SENIOR -> salarioBase * 0.10f;
            case SENIOR -> salarioBase * 0.15f;
        };
    }

    public float calcularDescuentos() {
        float bruto = calcularSalarioBruto();
        return bruto * (descuentoSalud + descuentoPension) / 100.0f;
    }

    public float calcularSalarioNeto() {
        return calcularSalarioBruto() - calcularDescuentos();
    }

    public void mostrarInformacion() {
        System.out.println("=== Información del Empleado ===");
        System.out.println("Nombre: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Edad: " + edad);
        System.out.println("Categoría: " + categoria);
        System.out.println("Salario Base: " + salarioBase);
    }

    public ResumenPago generarResumenPago() {
        float bruto = calcularSalarioBruto();
        float desc = calcularDescuentos();
        float neto = calcularSalarioNeto();
        String tipo = this.getClass().getSimpleName();
        return new ResumenPago(documento, nombre, tipo, bruto, desc, neto);
    }

    protected void validarPorcentaje(float valor, String campo) {
        if (valor < 0 || valor > 100) {
            throw new IllegalArgumentException("El porcentaje de " + campo + " debe estar entre 0 y 100.");
        }
    }
}
