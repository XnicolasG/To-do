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
            if (option == 2) {
                mostrar_tareas(tareas);
            } else if (option == 3) {
                System.out.println("Ingresa el numero de la tarea que deseas actualizar");
                int tareaSeleccionada = sc.nextInt() - 1;
                if (tareaSeleccionada >= tareas.size() || tareaSeleccionada < 0) {
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

        } while (option != 0);
        sc.close();
    }

    /**
     * Cuenta cuántas tareas están pendientes utilizando recursividad.
     * Caso base: cuando el índice es igual al tamaño de la lista, retorna 0.
     * Caso recursivo: si la tarea actual está pendiente, suma 1 y avanza al
     * siguiente índice;
     * si está completada, solo avanza al siguiente índice.
     *
     * @param tareas        Lista de tareas a evaluar.
     * @param indiceInicial Posición actual desde donde inicia el conteo.
     * @return Número total de tareas pendientes.
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

    /**
     * Cambia el estado de una tarea según el índice recibido.
     * Alterna entre completada y pendiente. Incluye manejo de errores
     * para evitar fallos cuando el índice no existe en la lista.
     *
     * @param listaTareas Lista de tareas donde se realizará la actualización.
     * @param indice      Posición de la tarea a modificar.
     */

    public static void marcar_completada(List<Tarea> listaTareas, int indice) {
        try {
            tareas.get(indice).isComplete = !tareas.get(indice).isComplete;
            System.out.println(tareas.get(indice));
        } catch (IndexOutOfBoundsException e) {
            System.err.println("La Posición ingresada no es valida.");
        }
    }

    /**
     * Muestra todas las tareas en consola con su número correspondiente.
     * Usa "[ ]" para tareas pendientes y "[X]" para tareas completadas.
     *
     * @param listaTareas Lista de tareas a mostrar.
     */

    public static void mostrar_tareas(List<Tarea> listaTareas) {

        for (Tarea tarea : listaTareas) {
            String checkbox = tarea.isComplete ? "[X]" : "[]";
            System.out.println((tareas.indexOf(tarea) + 1) + ". " + checkbox + " " + tarea.task);
        }
    }

    /**
     * Agrega una nueva tarea a la lista con un ID generado automáticamente.
     * La tarea se crea con estado pendiente por defecto.
     *
     * @param listaTareas Lista actual de tareas.
     * @param nuevaTarea  Descripción de la tarea a agregar.
     * @return La lista de tareas actualizada.
     */

    public static List<Tarea> agregar_tareas(List<Tarea> listaTareas, String nuevaTarea) {

        String id = "T" + (contador + 1);

        listaTareas.add(new Tarea(id, nuevaTarea, false));
        contador++;
        return listaTareas;
    };

    /**
     * Inicializa la lista de tareas con tres tareas por defecto.
     * Cada tarea incluye un ID, una descripción y un estado inicial pendiente.
     *
     * @return Lista inicial de tareas.
     */

    public static List<Tarea> obtener_tareas() {
        List<Tarea> listaInicial = new ArrayList<>();

        listaInicial.add(new Tarea("T1", "Tarea 1", false));
        listaInicial.add(new Tarea("T2", "Tarea 2", false));
        listaInicial.add(new Tarea("T3", "Tarea 3", false));

        return listaInicial;
    }
}

/*
 * Clase Tarea que define la estructura de cada tarea del programa.
 * Contiene un ID, una descripción y un estado booleano que indica si está
 * completada.
 * Incluye un método toString para mostrar la información de forma legible.
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