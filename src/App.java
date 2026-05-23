
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static List<Tarea> tareas = new ArrayList<>();
    static int contador = 0;
    static Boolean currentStatus;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int option;
        do {
            System.out.println("Selecciona que deseas hacer: ");
            System.out.println("1. Agregar una nueva tarea.");
            if (contador >= 1) {
                System.out.println("2. Obtener tareas.");
                System.out.println("3. Mostrar tareas.");
                System.out.println("4. Completar una tarea.");
                System.out.println("5. Contar tareas pendientes.");

            }
            System.out.println("0. Salir.");
            option = sc.nextInt();    
            
            if(option == 1){
                agregar_tareas();
            }
            if (tareas.size() >=1){
                if (option == 2) {
                    obtener_tareas();
                }else if (option == 3){
                    mostrar_tareas(tareas);
                }
            }

        } while (option != 0);
        sc.close();
    }

    /*
    Este metodo se encarga de mostrar las tareas con [] si estan pendientes
    y [X] si estan completadas.
    */

    public static void mostrar_tareas(List<Tarea> listaTareas){

        for (Tarea tarea : listaTareas) {
            String checkbox = tarea.isComplete ? "[X]" : "[]" ;
            System.out.println( (tareas.indexOf(tarea) + 1)+". " + checkbox + " " + tarea.task);
        }
    }

    /*
    Metodo que se encarga de mostrar todas las tareas, con la tarea y el estado de esta misma.
    */
    public static void obtener_tareas(){
        System.out.println("Hay " + contador + " tareas agregadas");
        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }
    }
    /*
    Metodo se encarga de agregar tareas nuevas al arreglo a manera de objetos.
    se agrega con descripcion de tarea, el status que por defecto sera falso y se crea un ID
    */
    public static void agregar_tareas() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese la nueva tarea. ");
        String nombreTarea = sc.nextLine();

        String id = "T" + contador + 1;

        Tarea nueva = new Tarea(id, nombreTarea, false);
        tareas.add(nueva);
        System.out.println("Se ha registrado: " + tareas.get(tareas.size() -1));
        contador++;

    };
}

/*
 * Clase estudiante guardara la estructura que tendra cada tarea y
 * la manera en que se mostrara la información
 */
class Tarea {
    public String ID;
    public String task;
    public boolean isComplete;

    Tarea(String id, String task, Boolean status) {
        this.ID = id;
        this.task = task;
        this.isComplete = status;
    }

    @Override
    public String toString() {
        String estado = isComplete ? "Completada" : "Pendiente";
        return task + ", " + " status: " + estado;
    }
}