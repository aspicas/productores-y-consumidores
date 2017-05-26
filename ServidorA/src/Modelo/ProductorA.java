/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

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
public class ProductorA extends Thread{
    private final int port= 6850;
    private static  DatagramPacket packetaenviar;
    private DatagramSocket productor;
    private byte[] buf = new byte[1024];
    private byte[] bufsend = new byte[124];    

    public ProductorA() {
        try {
            this.productor= new DatagramSocket(port,InetAddress.getByName(Redes.clienteA));
        } catch (UnknownHostException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void listening() throws UnknownHostException, SocketException {        
            
            while(true){
                 
                  DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            productor.receive(packet);
             System.out.print("Recibido dato de Servidor "
                        + packet.getAddress().getHostName() + " : ");
                
                // Conversion de los bytes a DatoUdp
                String recibe=new String(packet.getData());
                System.out.println(recibe);
                  Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                String Hora = hora+":"+minutos;
                String Nombre = packet.getAddress().getHostName();
                String CantidadIngredientes = "5";
                ProductorXMLFIle.saveUserInServerDataBase(Hora,Nombre,CantidadIngredientes);
                bufsend = CantidadIngredientes.getBytes();
                packetaenviar= new DatagramPacket(bufsend,bufsend.length, packet.getAddress(), packet.getPort());
                productor.send(packetaenviar);
              
            
        } catch (IOException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
           
        

 }
  @Override
    public void run(){ try {
        listening();
        } catch (UnknownHostException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
}
