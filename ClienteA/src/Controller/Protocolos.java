/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Preferences;
import Model.ServidorXMLFIle;
import Model.DatoUDP;
import Model.Redes;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Familia
 */
public class Protocolos {
 private final static int port= 6200;
  private final static int portmwz= 7000;
 private final static String IP= Redes.servidorA;
  private final static String IP_MWZ= Redes.servidorA;
 private final static byte[] buf = new byte[1024];
 
 
    
    public static void ProtocoloA (Preferences preferences, String Cantidad) {
        try {
         DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName(IP));
            String datoenviar ="A;"+Cantidad;
            byte[] bufSend = datoenviar.getBytes();
            DatagramPacket dato = new DatagramPacket(bufSend,
                    bufSend.length, InetAddress
                            .getByName(preferences.getIp()),
                    preferences.getPort());
             Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                String Hora = hora+":"+minutos;
                String Nombre = IP;
                String Destino= preferences.getIp();
             ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,Cantidad,Destino);
                System.out.println("Envio dato ProtocoloA " + datoenviar);
                socket.send(dato);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recibe=new String(packet.getData());
                System.out.println(recibe);
                Thread.sleep(1000);
            socket.close();
     } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
        public static void ProtocoloZ (Preferences preferences, String Cantidad,String Servidor) {
        try {
         DatagramSocket socket = new DatagramSocket(port, InetAddress.getByName(IP));
            String datoenviar ="A;"+Servidor+";"+Cantidad;
            byte[] bufSend = datoenviar.getBytes();
            DatagramPacket dato = new DatagramPacket(bufSend,
                    bufSend.length, InetAddress
                            .getByName(IP_MWZ),
                    portmwz);
             Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                String Hora = hora+":"+minutos;
                String Nombre = IP;
                String Destino= preferences.getIp();
             ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,Cantidad,Destino);
                System.out.println("Envio dato MWZ Cliente " + datoenviar);
                socket.send(dato);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recibe=new String(packet.getData());
                System.out.println(recibe);
                Thread.sleep(1000);
            socket.close();
     } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
