import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Materias de Programador Universitario
        Materia diseñoWeb = new Materia("Diseño Web", 1, 3, 0f);
        Materia progUno = new Materia("Programacion I", 2, 3, 0f);
        Materia paradigmas = new Materia("Paradigmas de Programacion", 3, 2, 0f);
        // Materias de Matemáticas
        Materia matematica = new Materia("Matematica", 5, 4, 0f);
        Materia algebra = new Materia("Algebra", 6, 4, 0f);
        Materia fisica = new Materia("Fisica", 7, 4, 0f);
        // Materias Abogacía
        Materia derechoCivil = new Materia("Derecho Civil", 11, 3, 0f);
        Materia derechoPenal = new Materia("Derecho Penal", 12, 2, 0f);
        Materia teoriaEstado = new Materia("Teoria General Del Estado", 13, 3, 0f);

        List<Materia> materiasProg = new ArrayList<>();
        materiasProg.add(diseñoWeb);
        materiasProg.add(progUno);
        materiasProg.add(paradigmas);

        List<Materia> materiasMath = new ArrayList<>();
        materiasMath.add(matematica);
        materiasMath.add(algebra);
        materiasMath.add(fisica);

        List<Materia> materiasDer = new ArrayList<>();
        materiasDer.add(derechoCivil);
        materiasDer.add(derechoPenal);
        materiasDer.add(teoriaEstado);

        // carreras
        // Programacion
        Carrera carreraProg = new Carrera(1, "Programador", new ArrayList<>(), materiasProg);
        // Matematicas
        Carrera carreraMath = new Carrera(2, "Matematicas", new ArrayList<>(), materiasMath);
        // Abogacia
        Carrera carreraDer = new Carrera(3, "Abogacia", new ArrayList<>(), materiasMath);

        List<Carrera> carreras = new ArrayList<>();
        carreras.add(carreraProg);
        carreras.add(carreraMath);
        carreras.add(carreraDer);

        // menu
 List<Estudiante> estudiantes = new ArrayList<>();
        int opcion;
        do { 
            
            System.out.println("\n===== GESTOR DE ESTUDIANTES UNVIME 2025 =====");
            System.out.println("1. Crear estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Listar carreras");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch(opcion) {
                case 1: 
                System.out.println("Ingrese el nombre del estudiante");
                String nombre = sc.nextLine(); 
                System.out.println("Ingrese el apellido del estudiante");
                String apellido = sc.nextLine(); 
                System.out.println("Ingrese la edad del estudiante");
                String edad = sc.nextLine(); 

                System.out.println("Seleccione una carrera por ID (1 = Programacion, 2 = Matematias, 3 = Abogacia):");
                for (Carrera c : carreras) {
                        System.out.println(c.getId() + " - " + c.getNombre());
                    }
                    int idCarrera = sc.nextInt();
                    sc.nextLine();
                


                
                

            }

            
        }
    }
}