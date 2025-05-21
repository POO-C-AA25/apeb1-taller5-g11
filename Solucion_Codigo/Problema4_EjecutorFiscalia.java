import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class EjecutorFiscalia {
    public static void main(String[] args) {
        Scanner tcl = new Scanner(System.in);
        char opcion = 'S';
        
        while (opcion == 'S') {
            ArrayList<Persona> personas = new ArrayList<>(
                Arrays.asList(
                    new Persona("Juan Pérez", 45, "Funcionario público", "Acusado", true, 50000),
                    new Persona("María Gómez", 38, "Abogada", "Testigo", false, 0),
                    new Persona("Carlos Ruiz", 52, "Empresario", "Acusado", false, 100000)
                ));
            ArrayList<CasoCorrupcion> casos = new ArrayList<>(
                Arrays.asList(
                    new CasoCorrupcion("Caso Sobornos 2024", LocalDate.now().minusDays(10), 
                                      "Involucra a funcionarios públicos", personas),
                    new CasoCorrupcion("Caso Cohecho Internacional", LocalDate.now().minusDays(3), 
                                      "Pagos irregulares a autoridades", personas)
                ));
            for (CasoCorrupcion caso : casos) {
                caso.actualizarEstado();
                caso.procesarAcusados();
                System.out.println(caso);
            }
            
            System.out.print("¿Desea procesar más casos? (S/N): ");
            opcion = tcl.next().toUpperCase().charAt(0);
        }
    }
}

class CasoCorrupcion {
    public String nombre;
    public LocalDate fechaInicio;
    public String descripcion;
    public String estado;
    public ArrayList<Persona> personasImplicadas;
    public double danioEconomico;

    public CasoCorrupcion(String nombre, LocalDate fechaInicio, String descripcion, 
                         ArrayList<Persona> personasImplicadas) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.descripcion = descripcion;
        this.personasImplicadas = personasImplicadas;
        this.estado = "Iniciado";
        this.danioEconomico = calcularDanioEconomico();
    }

    public void actualizarEstado() {
        LocalDate hoy = LocalDate.now();
        long diasTranscurridos = hoy.toEpochDay() - fechaInicio.toEpochDay();
        
        if (diasTranscurridos > 14) {
            this.estado = "Urgente";
        } else if (diasTranscurridos > 7) {
            this.estado = "Alerta";
        }
    }

    public void procesarAcusados() {
        for (Persona persona : personasImplicadas) {
            if (persona.nivelImplicacion.equals("Acusado")) {
                if (persona.colabora) {
                    persona.reduccionPena = true;
                    
                    if (persona.sentencia < 12) {
                        double fianzaMaxima = danioEconomico * 0.5;
                        persona.fianza = Math.min(persona.fianza, fianzaMaxima);
                    }
                }
            }
        }
    }

    private double calcularDanioEconomico() {
        double total = 0;
        for (Persona persona : personasImplicadas) {
            total += persona.danioEconomicoCausado;
        }
        return total;
    }

    @Override
    public String toString() {
        return """
               CasoCorrupcion{ nombre='""" + nombre + '\'' + "\n  fechaInicio=" + fechaInicio + "\n  descripcion='" + descripcion + '\'' + "\n  estado='" + estado + '\'' + "\n  dañoEconomico=" + danioEconomico + "\n  personasImplicadas=" + personasImplicadas + "\n}";
    }
}

class Persona {
    public String nombre;
    public int edad;
    public String ocupacion;
    public String nivelImplicacion;
    public boolean colabora;
    public boolean reduccionPena;
    public int sentencia;
    public double fianza;
    public double danioEconomicoCausado;

    public Persona(String nombre, int edad, String ocupacion, String nivelImplicacion, 
                  boolean colabora, double danioEconomico) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.colabora = colabora;
        this.danioEconomicoCausado = danioEconomico;
        this.sentencia = new Random().nextInt(24); // Sentencia aleatoria de 0-24 meses
        this.fianza = danioEconomico * 0.3; // Fianza inicial del 30%
    }

    @Override
    public String toString() {
        return """             
                   Persona{nombre='""" + nombre + '\'' + "\n      edad=" + edad + "\n      ocupacion='" + ocupacion + '\'' + "\n      nivelImplicacion='" + nivelImplicacion + '\'' + "\n      colabora=" + colabora + "\n      reduccionPena=" + reduccionPena + "\n      sentencia=" + sentencia + " meses" + "\n      fianza=" + fianza + "\n      dañoEconomicoCausado=" + danioEconomicoCausado + "\n    }";
    }
}