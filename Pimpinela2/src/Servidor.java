import java.io.*;
import java.net.*;

/**
 * Servidor que responde a mensajes del cliente.
 */
public class Servidor {

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado. Esperando conexión...");
            // Espera a que un cliente se conecte
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado.");

            // Configura los flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            boolean finalizar = false;

            // Procesa los mensajes del cliente
            while (!finalizar && (mensaje = entrada.readLine()) != null) {
                switch (mensaje) {
                    case "¿Quién es?":
                        salida.println("Soy yo");
                        break;
                    case "¿Qué vienes a buscar?":
                        salida.println("A ti");
                        break;
                    case "Ya es tarde":
                        salida.println("¿Por qué?");
                        break;
                    case "Porque ahora soy yo la que quiere estar sin ti":
                        salida.println("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta");
                        finalizar = true;
                        break;
                    default:
                        salida.println("Error");
                        break;
                }
            }
            System.out.println("Conexión cerrada con el cliente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
