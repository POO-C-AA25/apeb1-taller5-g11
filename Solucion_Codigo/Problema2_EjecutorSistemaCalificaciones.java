import java.util.Scanner;
public class EjecutorSistemaCalificaciones {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar datos del estudiante
        System.out.println("Ingrese los datos del estudiante:");
        System.out.print("Nombre: ");
        String nombreEstudiante = scanner.nextLine();
        System.out.print("Edad: ");
        int edadEstudiante = scanner.nextInt();

        Estudiante estudiante = new Estudiante(nombreEstudiante, edadEstudiante);

        System.out.println("\nIngrese los datos de la materia:");
        System.out.print("Nombre de la materia: ");
        String nombreMateria = scanner.nextLine();
        System.out.print("Nota ACD (sobre 3.5): ");
        double acd = scanner.nextDouble();
        System.out.print("Nota APE (sobre 3.5): ");
        double ape = scanner.nextDouble();
        System.out.print("Nota AA (sobre 3.0): ");
        double aa = scanner.nextDouble();

        Materia materia = new Materia(nombreMateria, acd, ape, aa);
        estudiante.agregarMateria(materia);

        estudiante.verificarAprobacion();

        scanner.close();
    }
}
class Materia {
    private String nombre;
    private double acd;
    private double ape;
    private double aa;

    public Materia(String nombre, double acd, double ape, double aa) {
        this.nombre = nombre;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public double calcularNotaFinal() {
        double porcentajeACD = (acd / 3.5) * 35;
        double porcentajeAPE = (ape / 3.5) * 35;
        double porcentajeAA = (aa / 3.0) * 30;
        
        return porcentajeACD + porcentajeAPE + porcentajeAA;
    }

    public boolean necesitaRecuperacion() {
        return calcularNotaFinal() < 70;
    }

    public String getNombre() {
        return nombre;
    }

    public double getAcd() {
        return acd;
    }

    public double getApe() {
        return ape;
    }

    public double getAa() {
        return aa;
    }
}

class Estudiante {
    private String nombre;
    private int edad;
    private Materia materia;

    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void agregarMateria(Materia m) {
        this.materia = m;
    }

    public void verificarAprobacion() {
        if (materia == null) {
            System.out.println("El estudiante no tiene materias asignadas.");
            return;
        }

        System.out.println("\n--- Resultados para " + nombre + " en " + materia.getNombre() + " ---");
        System.out.println("Notas ingresadas:");
        System.out.println("ACD: " + materia.getAcd() + "/3.5");
        System.out.println("APE: " + materia.getApe() + "/3.5");
        System.out.println("AA: " + materia.getAa() + "/3.0");

        double notaFinal = materia.calcularNotaFinal();
        System.out.printf("Nota final: %.2f%%\n", notaFinal);

        if (notaFinal >= 70) {
            System.out.println("¡Felicidades! Has aprobado la materia.");
        } else {
            System.out.println("No has alcanzado el 70% necesario para aprobar.");
            System.out.println("Deberás rendir un examen de recuperación sobre 3.5 puntos.");

            double porcentajeACD = (materia.getAcd() / 3.5) * 35;
            double porcentajeAPE = (materia.getApe() / 3.5) * 35;
            double porcentajeAA = (materia.getAa() / 3.0) * 30;
            double acumulado60 = (porcentajeACD + porcentajeAPE + porcentajeAA) * 0.6;
            
            System.out.printf("Con el 60%% del acumulado (%.2f%%) + 3.5 puntos del examen de recuperación, podrías aprobar.\n", acumulado60);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Materia getMateria() {
        return materia;
    }
}
