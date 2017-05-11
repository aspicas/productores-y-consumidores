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
 * Clase que define el proceso de la tienda
 * @author david
 * @version 1.0
 */
public class ProcessTienda extends Thread{
    
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private Tienda tienda;
    
    
    /**
     * Constructor de la clase con un parametro socket
     * @param cliente Socket que atiende al cliente
     * @exception IOException por si falla la entrada o la salida de la conexion
     */
    public ProcessTienda(Socket client, Tienda tienda){        
        try {
            this.tienda = tienda;
            this.socket = socket;
            this.input = new DataInputStream(socket.getInputStream());
            this.output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Cierra la conexion del hilo
     * @exception IOException por si falla la cancelacion de la conexion
     */
    public void desconectar(){
        try {
            socket.close();
        }
        catch (IOException ex) {
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        } catch (Exception ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @exception IOException por si falla la cancelacion de la conexion
     */
    @Override
    public void run(){
        try {
            String peticion = input.readUTF();
            
        }
        catch (IOException ex){
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        } catch (Exception ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
