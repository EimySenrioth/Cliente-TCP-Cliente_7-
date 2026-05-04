/*
TCP_Client, funciona con MultiThreadServer_5.java (como SERVER ejecutarlo)
utiliza el InetAddress Nombre Maquina y retorna la Ip de la máquina
*/
package TCP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente_7 {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            // 1er paso (Crear el Socket) - Nombre de la Máquina Remota->
            InetAddress addressServidor = InetAddress.getLocalHost(); 
            
            System.out.println("Ip Address Cliente: " + addressServidor.getHostAddress());
            
            try (Socket socketCliente = new Socket(addressServidor, 9090)) {
                // 2do paso (Crear el Streaming in y out)
                PrintWriter streamOut = new PrintWriter(socketCliente.getOutputStream(), true);
                BufferedReader streamIn = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                
                // 3er Paso: Mandar y recibir información desde un protocolo TCP
                System.out.println(streamIn.readLine()); // Lee lo que llegó e imprime
                System.out.print("Ingresa Mensaje: ");
                if (teclado.hasNextLine()) {
                    String mensaje = teclado.nextLine();
                    streamOut.println("Francisco: " + mensaje); // Manda mensaje al servidor
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
