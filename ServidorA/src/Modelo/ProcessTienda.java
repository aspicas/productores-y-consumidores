/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class ProcessTienda extends Thread{
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    public ProcessTienda(Socket client){                
        try {
            this.socket = socket;
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectar(){
        try {
            socket.close();
        }
        catch (IOException ex) {
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

        @Override
    public void run(){
        try {
            String peticion = input.readUTF();
            
        }
        catch (IOException ex){
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
