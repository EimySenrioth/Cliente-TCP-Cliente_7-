/*
public class InetAddress extends Object implements Serializable
1.- Obtiene Ip local y nombre local del host (nombre máquina)
2.- Obtiene Ip WAN y nombre del Dominio del host
*/
package TCP;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class InetAddress_6 {
    public static void main(String[] args) throws IOException {
        try (Scanner teclado = new Scanner(System.in)) {
            // Método para obtener la Ip del hostlocal
            System.out.println("\nObteniendo la IP y nombre local de esta máquina...");
            InetAddress ipAddress = InetAddress.getLocalHost();
            System.out.println("Ip local: " + ipAddress.getHostAddress()); // Ip
            System.out.println("Host name: " + ipAddress.getHostName()); // Name Ip


            // Método para obtener la Ip de cualquier HostName
            System.out.println("\nObtendremos la IP y el nombre de una máquina remota...");
            System.out.print("Ingresa el nombre de un dominio: ");
            if (teclado.hasNextLine()) {
                String ipNombre = teclado.nextLine();
                InetAddress ipAddress2 = InetAddress.getByName(ipNombre);

                System.out.println("\nIp WAN: " + ipAddress2.getHostAddress());
                System.out.println("HostName WAN: " + ipAddress2.getHostName());

                // Enviar como parámetro a un socket, se puede hacer de esta forma
                // marcara error si no se puede conectar (rechaza la conexión)
                try (Socket socket1 = new Socket(ipAddress, 9090)) {
                    System.out.println("Conexión establecida con éxito.");
                } catch (ConnectException ex2) {
                    System.out.println("El Host Rechazó la conexión: " + ex2.getMessage());
                }
            }

        } catch (UnknownHostException ex) {
            System.out.println("Error de Host Desconocido: " + ex);
        }
    }
}
