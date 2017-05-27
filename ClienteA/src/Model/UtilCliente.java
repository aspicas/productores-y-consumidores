/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Mensajes de error que pueden generarse
 * @author Familia
 */
public class UtilCliente {
    public static final String SERVER_TAG = "Consumidor";
    public static final String HORA_TAG = "Hora";
    public static final String NOMBRE_TAG = "Nombre";
    public static final String CANTIDAD_TAG = "Cantidad";
    public static final String PRIMITIVA_TAG ="Destino";
   
   public static final String ERROR_SERVER_TAG = "Error loading User from XML - Error in the attribute " + SERVER_TAG + " of the XML tag"; 
    public static final String ERROR_HORA_TAG = "Error loading User from XML - Error in the attribute " + HORA_TAG + " of the XML tag";
    public static final String ERROR_NOMBRE_TAG = "Error loading User from XML - Error in the attribute " + NOMBRE_TAG + " of the XML tag";
    public static final String ERROR_CANTIDAD_TAG = "Error loading User from XML - Error in the attribute " + CANTIDAD_TAG + " of the XML tag";
    public static final String ERROR_PRIMITIVA_TAG = "Error loading User from XML - Error in the attribute " + PRIMITIVA_TAG + " of the XML tag";
   
   

    public static final String ERROR_XML_EMPTY_FILE = "Error loading XML file - The file is empty";
    public static final String ERROR_XML_PROCESSING_FILE = "Error loading XML file - It's not possible processing the file";
    public static final String ERROR_XML_PROFESSOR_ID_NOT_EXIST = "Error loading XML file - The professor don't exist";
    
    
    public static final String USERS_XML_PATH = "src/model/cliente.xml";
}
