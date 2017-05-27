/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Redes;
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
 * Middleware que maneja las peticiones del servidor externo
 * @author Familia
 */
public class MWZ extends Thread{
    private final int port= 7000;
    private final int port_mwz= 7300;
    private DatagramSocket mwz;
    private byte[] buf = new byte[1024];
    private byte[] bufsend = new byte[1024];
    private final static String IP_A= Redes.servidorA;
    private final static String IP_B= Redes.servidorB;
    private final static String IP_C= Redes.servidorC;
    
    public MWZ() {
        try {
            this.mwz= new DatagramSocket(port,InetAddress.getByName(Redes.servidorA));
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
                
               }
                if (aux[1].equals("B")){
                    String datoenviar = "Z;B;"+aux[2];
                    bufsend = datoenviar.getBytes();
                     DatagramPacket dato = new DatagramPacket(bufsend,
                    bufsend.length, InetAddress
                            .getByName(IP_B),
                    port_mwz);
                     mwz.send(dato);
                
               }
                if (aux[1].equals("C")){
                    String datoenviar = "Z;C;"+aux[2];
                    bufsend = datoenviar.getBytes();
                     DatagramPacket dato = new DatagramPacket(bufsend,
                    bufsend.length, InetAddress
                            .getByName(IP_C),
                    port_mwz);
                     mwz.send(dato);
              
               }
              
 
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
