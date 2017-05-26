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
 *
 * @author Familia
 */
public class ProductorXMLFIle {
    
     public static boolean saveUserInServerDataBase (String Hora, String Nombre, String Cantidad)
    {
        Document    doc;
        Element     root, newChild;

        SAXBuilder  builder = new SAXBuilder();

        try
        {
            doc = builder.build(UtilProductor.USERS_XML_PATH);

            root = doc.getRootElement();


            newChild = new Element(UtilProductor.SERVER_TAG);

  
            newChild.setAttribute(UtilProductor.HORA_TAG, Hora);
            newChild.setAttribute(UtilProductor.NOMBRE_TAG, Nombre);
            newChild.setAttribute(UtilProductor.CANTIDAD_TAG,Cantidad);

           

            root.addContent(newChild);

            try
            {
                Format format = Format.getPrettyFormat();

              
                XMLOutputter out = new XMLOutputter(format);

         
                FileOutputStream file = new FileOutputStream(UtilProductor.USERS_XML_PATH);

     
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
            System.out.println(UtilProductor.ERROR_XML_EMPTY_FILE);
            e.printStackTrace();
        }
        catch(JDOMException e)
        {
            System.out.println(UtilProductor.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }
        catch(IOException e)
        {
            System.out.println(UtilProductor.ERROR_XML_PROCESSING_FILE);
            e.printStackTrace();
        }

        return true;
    }
}
