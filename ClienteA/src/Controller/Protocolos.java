/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.DatoUDP;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Familia
 */
public class Protocolos {
 private final int port= 6200;
 
 
    public Protocolos() {
        try {
         DatagramSocket socket = new DatagramSocket(
                    port, InetAddress.getByName("localhost"));

            // Se instancia un DatoUdp y se convierte a bytes[]
            DatoUDP elDato = new DatoUDP("hola");
            byte[] elDatoEnBytes = elDato.toByteArray();

            // Se meten los bytes en el DatagramPacket, que es lo que se
            // va a enviar por el socket.
            // El destinatario es el servidor.
            // El puerto es por el que est� escuchando el servidor.
            DatagramPacket dato = new DatagramPacket(elDatoEnBytes,
                    elDatoEnBytes.length, InetAddress
                            .getByName("localhost"),
                    6000);
            
            // Se env�a el DatagramPacket 10 veces, esperando 1 segundo entre
            // env�o y env�o.
            for (int i = 0; i < 10; i++)
            {
                System.out.println("Envio dato " + i);
                socket.send(dato);
                Thread.sleep(1000);
            }
     } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
