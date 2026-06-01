package estructura32;
import java.util.*;

public class Estructura32 {

    static class Trabajo {
        int numero;
        String estudiante;
        String proyecto;
        int duracion;
        String material;
        String estado;
    }
    
    static Queue<Trabajo> cola = new LinkedList<>();
    static LinkedList<Trabajo> procesados = new LinkedList<>();
    static int contador = 1;
    
    public static void main(String[] args) {
        Scanner sc =new Scanner (System.in);
        int op;
        
        do {
            System.out.println("\n=== Laboratorio de Impresoras 3D ===");
            System.out.println("1. Registrar trabajo");
            System.out.println("2. Consultar el siguiente trabajo");
            System.out.println("3. Procesar el siguiente trabajo");
            System.out.println("4. Ver trabajos pendientes");
            System.out.println("5. Ver trabajos procesados");
            System.out.println("6. Tiempo total estimado pendientes");
            System.out.println("7. Cuantos trabajos quedan pendientes");
            System.out.println("0. Salir");
            System.out.print("\nOpcion: ");
            
            try{
                op= sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Opcion Invalida, trata ingresando un numero");
                sc.nextLine();
                op=-1;
            }
            
            switch (op){
                case 1: registrarTrabajo(sc); break;
                case 2: consultarSiguiente(); break;
                case 3: procesarTrabajo(); break;
                case 4: mostrarPendientes(); break;
                case 5: mostrarProcesados(); break;
                case 6: tiempoTotal(); break;
                case 7: System.out.println("Trabajos pendientes: " + cola.size()); break;
                case 0: System.out.println("Fin del programa"); break;
                default:
                    if (op != -1 && op != 0) System.out.println("Opcion invalida");
                    break;
            }
        }while (op !=0);
    }
    
    static void registrarTrabajo (Scanner sc){
        Trabajo t = new Trabajo();
        t.numero = contador++;
        System.out.print("Nombre del estudiante: ");
        t.estudiante = sc.nextLine();
        System.out.print("Nombre del proyecto: ");
        t.proyecto = sc.nextLine();
        System.out.print("Duracion estimada (minutos): ");
        t.duracion = sc.nextInt();
        while(t.duracion <= 0){
            System.out.print("Error. Ingrese una duracion valida (mayor a 0): ");
            t.duracion = sc.nextInt();
        }
        sc.nextLine();
        System.out.print("Material solicitado: ");
        t.material = sc.nextLine();
        t.estado = "pendiente";
        cola.offer(t);
        System.out.println("\nTrabajo #" + t.numero + " registrado");
    }
    
    static void consultarSiguiente(){
        if (cola.isEmpty()){
            System.out.println("No hay trabajos pendientes");
            return;
        }
        System.out.println("Siguiente trabajo a procesar: ");
        mostrarDatos(cola.peek());
    }
    
    static void procesarTrabajo(){
        if (cola.isEmpty()){
            System.out.println("No hay trabajos pendientes");
            return;
        }
        Trabajo t= cola.poll();
        t.estado="procesado";
        procesados.add(t);
        System.out.println("Trabajo #" + t.numero + ", '" + t.proyecto +"' de " + t.estudiante + " procesado");
    }
    
    static void mostrarPendientes(){
        if (cola.isEmpty()) {
            System.out.println("No hay trabajos pendientes.");
            return;
        }
        System.out.println("=== Trabajos Pendientes ===");
        Queue<Trabajo> temp = new LinkedList<>();
        while (!cola.isEmpty()) {
            Trabajo t = cola.poll();
            mostrarDatos(t);
            temp.offer(t);
        }
        while (!temp.isEmpty()) {
            cola.offer(temp.poll());
        }
    }
    
    static void mostrarProcesados() {
        if (procesados.isEmpty()) {
            System.out.println("No se ha procesado ningun trabajo");
            return;
        }
        System.out.println("=== Trabajos Procesados ===");
        for (int i = 0; i < procesados.size(); i++) {
            mostrarDatos(procesados.get(i));
        }
    }

    static void tiempoTotal() {
        if (cola.isEmpty()) {
            System.out.println("No hay trabajos pendientes");
            return;
        }
        int total = 0;
        Queue<Trabajo> temp = new LinkedList<>();
        while (!cola.isEmpty()) {
            Trabajo t = cola.poll();
            total += t.duracion;
            temp.offer(t);
        }
        while (!temp.isEmpty()) {
            cola.offer(temp.poll());
        }
        System.out.println("Tiempo total estimado: " + total + " minutos faltantes");
    }

    static void mostrarDatos(Trabajo t) {
        System.out.println("Numero: " + t.numero);
        System.out.println("Estudiante: " + t.estudiante);
        System.out.println("Proyecto: " + t.proyecto);
        System.out.println("Duracion: " + t.duracion + " minutos");
        System.out.println("Material: " + t.material);
        System.out.println("Estado: " + t.estado);
        System.out.println("-------------------");
    }
}