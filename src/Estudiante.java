import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    // atributos con nombres en minúscula por convención
    private String nombre;
    private String apellido;
    private int edad;
    private List <Carrera> carreras;
    private double promedio;
    private List<Materia> materias;
    // constructor vacio
    public Estudiante() {
    }

    // constructor con parametros
    public Estudiante(String nombre, String apellido, int edad, String carreras, double promedio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.carreras = new ArrayList<>();
        this.promedio = promedio;
        
    }

    public void calcularPromedio() {
        if (materias.isEmpty())
            ;
        {
            System.out.println("El estudiante no tiene materias registradas");
        }
        double suma = 0; 
        for (Materia m : materias){
            suma +=m.getCalif();
        }
        promedio = suma / materias.size();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    // Getter y Setter para edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad > 16) {
            this.edad = edad;
        } else {
            System.out.println("ERROR: La edad debe ser mayor a 16.");
        }
    }


    // Getter y Setter para promedio
    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        if (promedio >= 0 && promedio <= 10) {
            this.promedio = promedio;
        } else {
            System.out.println("ERROR: El promedio tiene que estar entre 0 y 10.");
        }
    }

    public List<Carrera> getCarrera() {
        return carreras;
    }
}
