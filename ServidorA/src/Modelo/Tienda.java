/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Tienda extends Thread{
    private final int port = 2000;
    private ServerSocket tienda;
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    
    public Tienda(){
        try {
            this.tienda = new ServerSocket(port);
            this.client = tienda.accept();
            this.input = new DataInputStream(client.getInputStream());
            this.output = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void listening(){        
        try{
            while(true){
                this.client = tienda.accept();
                ((ProcessTienda) new ProcessTienda(client)).start();
            }
        }
        catch (IOException ex){
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    @Override
    public void run(){ listening(); }
}