import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    static List<Tarea> tareas = obtener_tareas();
    static int contador = tareas.size();
    static Boolean currentStatus;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int option;
        do {
            System.out.println("Selecciona que deseas hacer: ");
            System.out.println("1. Agregar una nueva tarea.");
            System.out.println("2. Mostrar tareas.");
            System.out.println("3. Completar una tarea.");
            System.out.println("4. Contar tareas pendientes.");

            System.out.println("0. Salir.");
            option = sc.nextInt();

            if (option == 1) {
                sc.nextLine();
                System.out.println("Ingrese la nueva tarea. ");
                String nombreTarea = sc.nextLine();
                List<Tarea> nuevasTareas = agregar_tareas(tareas, nombreTarea);
                System.out.println("Se ha registrado: " + nuevasTareas.get(nuevasTareas.size() - 1));

            }
            if (tareas.size() >= 1) {
                if (option == 2) {
                    obtener_tareas();
                    mostrar_tareas(tareas);
                } else if (option == 3) {
                    System.out.println("Ingresa el numero de la tarea que deseas actualizar");
                    int tareaSeleccionada = sc.nextInt() - 1;
                    if (tareaSeleccionada > tareas.size()) {
                        System.err.println("La tarea " + tareaSeleccionada
                                + " no existe, intenta de nuevo con un numero valido en la siguiente lista:  ");
                        mostrar_tareas(tareas);
                        tareaSeleccionada = sc.nextInt() - 1;
                    }
                    marcar_completada(tareas, tareaSeleccionada);
                } else if (option == 4) {
                    int pendientes = contar_tareas_pendientes(tareas, 0);
                    System.out.println("Hay " + pendientes + " tareas pendientes por completar.");
                }
            }

        } while (option != 0);
        sc.close();
    }

    /*
     * Metodo encargado de evaluar cuantas tareas estan pendientes por medio de la
     * lista e indice que se pasan por
     * medio de parametros. El metodo devuelve 0 en caso el indice sea igual al
     * largo de la lista.De no ser así
     * por medio de recursividad se ejecuta la funcion haciendo una suma interta por
     * medio de iteraciones sobre cada
     * elemento.
     */
    public static int contar_tareas_pendientes(List<Tarea> tareas, int indiceInicial) {
        if (indiceInicial == tareas.size()) {
            return 0;
        }

        Tarea tareaActual = tareas.get(indiceInicial);

        if (!tareaActual.isComplete) {
            return 1 + contar_tareas_pendientes(tareas, indiceInicial + 1);
        } else {
            return contar_tareas_pendientes(tareas, indiceInicial + 1);
        }
    }

    /*
     * Metodo que actualiza el estado de la tarea
     */
    public static void marcar_completada(List<Tarea> listaTareas, int indice) {
        tareas.get(indice).isComplete = !tareas.get(indice).isComplete;
        System.out.println(tareas.get(indice));
    }

    /*
     * Este metodo se encarga de mostrar las tareas con [] si estan pendientes
     * y [X] si estan completadas.
     */
    public static void mostrar_tareas(List<Tarea> listaTareas) {

        for (Tarea tarea : listaTareas) {
            String checkbox = tarea.isComplete ? "[X]" : "[]";
            System.out.println((tareas.indexOf(tarea) + 1) + ". " + checkbox + " " + tarea.task);
        }
    }

    /*
     * Metodo que se encarga de mostrar todas las tareas, con la tarea y el estado
     * de esta misma.
     */
    public static List<Tarea> obtener_tareas() {
        List<Tarea> listaInicial = new ArrayList<>();

        listaInicial.add(new Tarea("T1", "Tarea 1", false));
        listaInicial.add(new Tarea("T2", "Tarea 2", false));
        listaInicial.add(new Tarea("T3", "Tarea 3", false));

        return listaInicial;
    }

    /*
     * Metodo se encarga de agregar tareas nuevas al arreglo a manera de objetos.
     * se agrega con descripcion de tarea, el status que por defecto sera falso y se
     * crea un ID
     */

    // -------------------Pendiente por corregir, debe recibir lista y tarea y
    // regresar lista modificada
    public static List<Tarea> agregar_tareas(List<Tarea> listaTareas, String nuevaTarea) {

        String id = "T" + contador + 1;

        listaTareas.add(new Tarea(id, nuevaTarea, false));
        return listaTareas;
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