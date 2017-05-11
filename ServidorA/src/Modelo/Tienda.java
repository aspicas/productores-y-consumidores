/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que define el comportamiento de la tienda
 * @author david
 * @version 1.0
 */
public class Tienda extends Thread{
    
    private final int port = 2000;
    private ServerSocket tienda;
    private Socket client;
    private DataInputStream input;
    private DataOutputStream output;
    private Integer numProductos = 0;
    
    
   /** 
    * Constructor de la clase
    * @exception IOException por si falla la entrada o la salida de la conexion
    */
    public Tienda(){
        
        try {           
            this.tienda = new ServerSocket(port);
            this.client = tienda.accept();
            this.input = new DataInputStream(client.getInputStream());
            this.output = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Permanece escuchando las peticiones entrantes del cliente
     * @exception IOException por si falla la entrada o la salida de la conexion
     */
    public void listening(){        
        
        try{
            while(true){
                this.client = tienda.accept();
                ((ProcessTienda) new ProcessTienda(client, this)).start();
            }
        }
        catch (IOException ex){
            Logger.getLogger(ProcessTienda.class.getName()).log(Level.SEVERE,null,ex);
        } catch (Exception ex) {
            Logger.getLogger(Tienda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Integer getNumProductos() {
        return numProductos;
    }

   
    public void setNumProductos(Integer numProductos) {
        this.numProductos = numProductos;
    }
    
    /**
     * @see Tienda#listening() 
     */
    @Override
    public void run(){ listening(); }
}