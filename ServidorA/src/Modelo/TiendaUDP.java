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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manejo de UDP de la tienda
 * @author Familia
 */
public class TiendaUDP extends Thread{
    private final int port= 6000;
    private DatagramSocket tienda;
    private byte[] buf = new byte[1024];
    private byte[] bufsend = new byte[124];
   

    public TiendaUDP() {
        try {
            this.tienda= new DatagramSocket(port,InetAddress.getByName(Redes.clienteA));
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
            tienda.receive(packet);
             System.out.print("Recibido dato de "
                        + packet.getAddress().getHostName() + " : ");
                
                // Conversion de los bytes a DatoUdp
                String recibe=new String(packet.getData());
                System.out.println(recibe);
                String [] aux = recibe.split(";");
                
                if (aux[0].equals("A"))
                    ProcesosTienda.protocoloA(aux, packet,tienda);
                if (aux[0].equals("Z"))
                    ProcesosTienda.protocoloZ(aux, packet,tienda);
              
            
        } catch (IOException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
           
        

 }
        
    

    
    /**
     * @see Tienda#listening() 
     */
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
    
    
    
     

