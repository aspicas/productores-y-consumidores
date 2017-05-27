/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hilo que que maneja las I/O 
 * @author Familia
 */
public class ProcessTiendaUDP extends Thread {
    private DatagramSocket socket;
    private byte[] buf = new byte[10000];
    private Integer numProductos = 0;
    private TiendaUDP tienda;
    
        public Integer getNumProductos() {
        return numProductos;
    }

   
    public void setNumProductos(Integer numProductos) {
        this.numProductos = numProductos;
    }
    public ProcessTiendaUDP(DatagramSocket client, TiendaUDP tienda){        
           this.socket= client;
           this.tienda = tienda;
        
}
    public void desconectar(){
            socket.close();
    }
     @Override
    public void run(){
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
             System.out.print("Recibido dato de "
                        + packet.getAddress().getHostName() + " : ");
                
                // Conversion de los bytes a DatoUdp
                DatoUDP datoRecibido = DatoUDP.fromByteArray(packet.getData());
                System.out.println(datoRecibido.cadenaTexto);
        } catch (IOException ex) {
            Logger.getLogger(ProcessTiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
