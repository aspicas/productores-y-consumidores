/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Genera los xml de la tienda
 * @author Familia
 */
public class ServidorXMLFIle {
    
     public static boolean saveUserInServerDataBase (String Hora, String Nombre, String Cantidad,String Protocolo)
    {
        Document    doc;
        Element     root, newChild;

        SAXBuilder  builder = new SAXBuilder();

        try
        {
            doc = builder.build(Util.USERS_XML_PATH);

            root = doc.getRootElement();


            newChild = new Element(Util.SERVER_TAG);

  
            newChild.setAttribute(Util.HORA_TAG, Hora);
            newChild.setAttribute(Util.NOMBRE_TAG, Nombre);
            newChild.setAttribute(Util.CANTIDAD_TAG,Cantidad);
            newChild.setAttribute(Util.PRIMITIVA_TAG,Protocolo);
           

            root.addContent(newChild);

            try
            {
                Format format = Format.getPrettyFormat();

              
                XMLOutputter out = new XMLOutputter(format);

         
                FileOutputStream file = new FileOutputStream(Util.USERS_XML_PATH);

     
                out.output(doc,file);

               
                file.flush();
                file.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(JDOMParseException e)
        {
            System.out.println(Util.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(Util.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }

        return true;
    }
}
