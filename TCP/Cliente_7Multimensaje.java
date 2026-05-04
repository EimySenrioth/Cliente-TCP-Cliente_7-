/*
Cliente TCP que permite enviar mensajes ilimitados hasta escribir "Exit".
Funciona con MultiThreadServer_5.java.
*/
package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente_7Multimensaje {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            // 1er paso: Obtener dirección del servidor
            InetAddress addressServidor = InetAddress.getLocalHost(); 
            System.out.println("Conectando a: " + addressServidor.getHostAddress());
            
            // Crear el Socket
            try (Socket socketCliente = new Socket(addressServidor, 9090)) {
                // 2do paso: Configurar flujos de entrada y salida
                PrintWriter streamOut = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader streamIn = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                
                // Leer mensaje de bienvenida del servidor si existe
                System.out.println("Servidor dice: " + streamIn.readLine());

                String mensaje = "";
                
                // Bucle para mensajes ilimitados
                while (!mensaje.equalsIgnoreCase("Exit")) {
                    System.out.print("Ingresa Mensaje (escribe 'Exit' para salir): ");
                    if (teclado.hasNextLine()) {
                        mensaje = teclado.nextLine();
                        // Mandar mensaje al servidor
                        streamOut.println("Francisco: " + mensaje);
                    } else {
                        break;
                    }
                }
                System.out.println("Cerrando conexión...");
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
