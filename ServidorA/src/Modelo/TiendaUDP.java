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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class TiendaUDP extends Thread{
    private final int port= 6000;
    private ProcessTiendaUDP process;
    private DatagramSocket tienda;
    private byte[] buf = new byte[10000];
    private Integer numProductos = 0;

    public TiendaUDP() {
        try {
            this.tienda= new DatagramSocket(port,InetAddress.getByName("localhost"));
        } catch (UnknownHostException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(TiendaUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 public void listening() throws UnknownHostException, SocketException {        
            
            while(true){
                 
                  process = new ProcessTiendaUDP(tienda, this);
                  process.start();
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
    
    
    
     

