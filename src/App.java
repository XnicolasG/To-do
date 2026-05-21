public class App {

    static Tarea[] tareas = new Tarea[5];
    static int contador = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }

    public static void obtener_tareas(String tarea) {
        

    };
}

/*
 * Clase estudiante guardara la estructura que tendra cada tarea y
 * la manera en que se mostrara la información
 */
class Tarea {
    String ID;
    String task;
    Boolean isComplete;

    Tarea(String id, String task, Boolean status) {
        this.ID = id;
        this.task = task;
        this.isComplete = status;
    }

    @Override
    public String toString() {
        return ID + ": " + task + " status: " + isComplete;
    }
}