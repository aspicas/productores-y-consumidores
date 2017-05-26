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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Familia
 */
public class ProcesosTienda extends Thread {
    private static byte[] buf = new byte[1024];
    private static byte[] bufsend = new byte[124];
    private static  DatagramPacket packetaenviar;
    private static Integer numProductos = 0;
    private static Boolean lleno;
    private static Boolean pedir=false;
    private static final int port= 6200;
    private static DatagramSocket socket;
    private static final String ipproductor = Redes.clienteA;
    private static  String ipserver="";

    public static void setNumProductos(Integer numProductos) {
        ProcesosTienda.numProductos = numProductos;
    }
    
    public static void protocoloA(String[] aux, DatagramPacket packet, DatagramSocket tienda){
        try {
                Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                socket=tienda;
                String Hora = hora+":"+minutos;
                String Nombre = packet.getAddress().getHostName();
                String Cantidad = aux[1].trim();
                String Protocolo= aux[0];
                System.out.println(aux[1]);
                ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,Cantidad,Protocolo);
                verificarProductos(Integer.parseInt(aux[1].trim()));
                if (pedir){
                String Hola = "Completado";
                bufsend = Hola.getBytes();
                packetaenviar= new DatagramPacket(bufsend,bufsend.length, packet.getAddress(), packet.getPort());
                 System.out.println(Hola);
                }
                else{
                String Hola = "Error";
                bufsend = Hola.getBytes();
                packetaenviar= new DatagramPacket(bufsend,bufsend.length, packet.getAddress(), packet.getPort());
                 System.out.println(Hola);
                }
                
               
                tienda.send(packetaenviar);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public static void verificarProductos (int cantidad){
        
        if (numProductos == 0){
            System.out.println("Tienda no posee Productos");
            reponer();
            if (cantidad <= numProductos){
             numProductos= numProductos - cantidad;
             System.out.println("Pedir2: "+numProductos);
            pedir=true;}
             else if (cantidad >= numProductos){
            System.out.println(cantidad);
            pedir=pedirProductos(cantidad);
            System.out.println("Pedir: "+numProductos);
             pedir=true;}
        }
        else if (cantidad >= numProductos){
            System.out.println(cantidad);
            pedir=pedirProductos(cantidad);
            System.out.println("Pedir: "+numProductos);
                    pedir=true;;
        }
        
            
    }
    public static void verificarProductosZ (int cantidad){
       if (numProductos == 0){
            System.out.println("Tienda no posee Productos");
            reponer();
            if (cantidad <= numProductos){
             numProductos= numProductos - cantidad;
             System.out.println("Pedir2: "+numProductos);
            pedir=true;}
             else if (cantidad >= numProductos){
            System.out.println(cantidad);
            pedir=pedirProductos(cantidad);
            System.out.println("Pedir: "+numProductos);
             pedir=true;}
        }
        else if (cantidad >= numProductos){
            System.out.println(cantidad);
            pedir=pedirProductos(cantidad);
            System.out.println("Pedir: "+numProductos);
                    pedir=true;;
        }
        
            
    }

    private static Boolean pedirProductos(int cantidad) {
  try {
            
            String datoenviar ="5";
            byte[] bufSend = datoenviar.getBytes();
            DatagramPacket dato = new DatagramPacket(bufSend,
                    bufSend.length, InetAddress
                            .getByName(ipproductor),
                    6850);
             Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                String Hora = hora+":"+minutos;
                String Nombre = ipserver;
                String Destino= ipproductor;
             ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,datoenviar,Destino);
             int numProductos2=0;
             int cant=0;
           while(numProductos2<=cantidad)
            {
                System.out.println("Envio dato " + datoenviar);
                socket.send(dato);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recibe=new String(packet.getData());
               cant=Integer.parseInt(recibe.trim());
               numProductos2= numProductos2+cant;
               System.out.println("Envio dato2 " + numProductos2);
            }

            setNumProductos(numProductos2 - cantidad);
     } catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
        }

    private static void reponer() {
       try {
           
            String datoenviar ="5";
            byte[] bufSend = datoenviar.getBytes();
            DatagramPacket dato = new DatagramPacket(bufSend,
                    bufSend.length, InetAddress
                            .getByName(ipproductor),
                    6850);
             Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                String Hora = hora+":"+minutos;
                String Nombre = ipserver;
                String Destino= ipproductor;
             ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,datoenviar,Destino);
                System.out.println("Envio dato " + datoenviar);
                socket.send(dato);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String recibe=new String(packet.getData());
                System.out.println(recibe);
               numProductos=Integer.parseInt(recibe.trim());
              // numProductos=5;
     } catch (Exception e)
        {
            e.printStackTrace();
        }
        }  
    
    public static void protocoloZ(String[] aux, DatagramPacket packet, DatagramSocket tienda){
        try {
                Calendar calendario = new GregorianCalendar(); 
                int hora=calendario.get(Calendar.HOUR_OF_DAY);
                int minutos=calendario.get(Calendar.MINUTE);
                socket=tienda;
                String Hora = hora+":"+minutos;
                String Nombre = packet.getAddress().getHostName();
                String Cantidad = "3";
                String Protocolo= aux[0];
                System.out.println(aux[2]);
                ServidorXMLFIle.saveUserInServerDataBase(Hora,Nombre,Cantidad,Protocolo);
                verificarProductosZ(Integer.parseInt(Cantidad));
                if (pedir){
                String Hola = "Completado";
                bufsend = Hola.getBytes();
                packetaenviar= new DatagramPacket(bufsend,bufsend.length, packet.getAddress(), packet.getPort());
                
                }
                else{
                String Hola = "Error";
                bufsend = Hola.getBytes();
                packetaenviar= new DatagramPacket(bufsend,bufsend.length, packet.getAddress(), packet.getPort());
                }
                
               
                tienda.send(packetaenviar);
        } catch (IOException ex) {
            Logger.getLogger(ProcesosTienda.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
  
}
