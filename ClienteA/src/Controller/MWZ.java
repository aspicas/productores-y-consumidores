/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class MWZ extends Thread{
    private final int port= 7000;
    private final int port_mwz= 7300;
    private static  DatagramPacket packetaenviar;
    private DatagramSocket mwz;
    private byte[] buf = new byte[1024];
    private byte[] bufsend = new byte[124];
    private final static String IP_A= "127.0.0.1";
    private final static String IP_B= "127.0.0.1";
    private final static String IP_C= "127.0.0.1";

   

    public MWZ() {
        try {
            this.mwz= new DatagramSocket(port,InetAddress.getByName("localhost"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(MWZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(MWZ.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void listening() throws UnknownHostException, SocketException {        
            
            while(true){
                 
                  DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            mwz.receive(packet);
             System.out.print("Recibido dato de CLIENTE "
                        + packet.getAddress().getHostName() + " : ");
                
                // Conversion de los bytes a DatoUdp
                String recibe=new String(packet.getData());
                System.out.println(recibe);
                String[] aux = recibe.split(";");
                 
               if (aux[1].equals("A")){
                    String datoenviar = "Z;A;"+aux[2];
                    bufsend = datoenviar.getBytes();
                     DatagramPacket dato = new DatagramPacket(bufsend,
                    bufsend.length, InetAddress
                            .getByName(IP_A),
                    port_mwz);
                     mwz.send(dato);
                DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
                mwz.receive(packet2);
                String recibe2=new String(packet2.getData());
                System.out.println("Recibo de MWZ Servidor"+recibe2);
               }
                if (aux[1].equals("B")){
                    String datoenviar = "Z;B;"+aux[2];
                    bufsend = datoenviar.getBytes();
                     DatagramPacket dato = new DatagramPacket(bufsend,
                    bufsend.length, InetAddress
                            .getByName(IP_B),
                    port_mwz);
                     mwz.send(dato);
                DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
                mwz.receive(packet2);
                String recibe2=new String(packet2.getData());
                System.out.println("Recibo de MWZ Servidor"+recibe2);
               }
                if (aux[1].equals("C")){
                    String datoenviar = "Z;C;"+aux[2];
                    bufsend = datoenviar.getBytes();
                     DatagramPacket dato = new DatagramPacket(bufsend,
                    bufsend.length, InetAddress
                            .getByName(IP_C),
                    port_mwz);
                     mwz.send(dato);
                DatagramPacket packet2 = new DatagramPacket(buf, buf.length);
                mwz.receive(packet2);
                String recibe2=new String(packet2.getData());
                System.out.println("Recibo de MWZ Servidor"+recibe2);
               }
              
               mwz.close();
        } catch (IOException ex) {
            Logger.getLogger(MWZ.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
           
        

 }
  @Override
    public void run(){ try {
        listening();
        } catch (UnknownHostException ex) {
            Logger.getLogger(MWZ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(MWZ.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
