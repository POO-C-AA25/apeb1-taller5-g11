
import java.util.ArrayList;
import java.util.List;


public class Problema3_EjecutorDepaEmpresa {
    public static void main(String[] args) {
        Empresa miEmpresa = new Empresa("TechSolutions S.A.", "20123456789", "Av. Industrial 1234");

        Departamento ventas = new Departamento("Ventas", 25, 1_200_000);
        Departamento produccion = new Departamento("Produccion", 18, 800_000);
        Departamento marketing = new Departamento("Marketing", 12, 600_000);
        Departamento logistica = new Departamento("Logistica", 8, 300_000);

        miEmpresa.agregarDepartamento(ventas);
        miEmpresa.agregarDepartamento(produccion);
        miEmpresa.agregarDepartamento(marketing);
        miEmpresa.agregarDepartamento(logistica);

        miEmpresa.mostrarDepartamentos();

        System.out.println("\nEjemplo de categorizacion:");
        System.out.println(ventas);
        System.out.println("Categoria esperada: A");
        System.out.println("\n" + produccion);
        System.out.println("Categoria esperada: C");
    }
}
class Departamento {
    public String nombre;
    public int numeroEmpleados;
    public double produccionAnual;
    public char categoria;

    public Departamento(String nombre, int numeroEmpleados, double produccionAnual) {
        this.nombre = nombre;
        this.numeroEmpleados = numeroEmpleados;
        this.produccionAnual = produccionAnual;
        determinarCategoria();
    }

    private void determinarCategoria() {
        if (numeroEmpleados > 20 && produccionAnual > 1_000_000) {
            categoria = 'A';
        } else if (numeroEmpleados >= 20 && produccionAnual >= 1_000_000) {
            categoria = 'B';
        } else if (numeroEmpleados >= 10 && produccionAnual >= 500_000) {
            categoria = 'C';
        } else {
            categoria = 'D';
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public double getProduccionAnual() {
        return produccionAnual;
    }

    public char getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return String.format("Departamento: %s\nEmpleados: %d\nProduccion anual: $%,.2f\nCategoria: %s\n",
                nombre, numeroEmpleados, produccionAnual, categoria);
    }
}

class Empresa {
    public String nombre;
    public String ruc;
    public String direccion;
    public List<Departamento> departamentos;

    public Empresa(String nombre, String ruc, String direccion) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = new ArrayList<>();
    }

    public void agregarDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public void mostrarDepartamentos() {
        System.out.println("==================================");
        System.out.println("Empresa: " + nombre);
        System.out.println("RUC: " + ruc);
        System.out.println("Direccion: " + direccion);
        System.out.println("Departamentos:");
        System.out.println("==================================");
        
        for (Departamento depto : departamentos) {
            System.out.println(depto);
            System.out.println("----------------------------------");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getDireccion() {
        return direccion;
    }
}