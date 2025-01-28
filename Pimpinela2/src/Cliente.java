import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente que envía mensajes al servidor y recibe respuestas.
 */
public class Cliente {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            // Configura los flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Conectado al servidor.");
            String mensaje, respuesta;

            // Enviar mensajes al servidor y mostrar las respuestas
            do {
                System.out.print("Escribe un mensaje: ");
                mensaje = scanner.nextLine(); // Leer mensaje del usuario
                salida.println(mensaje);     // Enviar mensaje al servidor

                respuesta = entrada.readLine(); // Leer respuesta del servidor
                System.out.println("Servidor: " + respuesta);

                // Termina si el servidor envía el mensaje final
                if (respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) {
                    System.out.println("El servidor ha terminado la comunicación.");
                    break;
                }
            } while (!mensaje.equalsIgnoreCase("salir"));

            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
