/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *  Clase que serializa los mensajes que procesara el servidor
 * @author Familia
 */
public class DatoUDP implements Serializable {
    public String cadenaTexto;
    
     public DatoUDP (String cadena)
    {
        this.cadenaTexto=cadena;
    }
    public byte [] toByteArray()
    {
        try
        {
             // Se hace la conversi�n usando un ByteArrayOutputStream y un
             // ObjetOutputStream.
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream (bytes);
            os.writeObject(this);
            os.close();
            return bytes.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public static DatoUDP fromByteArray (byte [] bytes)
    {
        try
        {
            // Se realiza la conversi�n usando un ByteArrayInputStream y un
            // ObjectInputStream
            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(byteArray);
            DatoUDP aux = (DatoUDP)is.readObject();
            is.close();
            return aux;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

