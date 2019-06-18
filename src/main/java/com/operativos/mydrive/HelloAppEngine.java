package com.operativos.mydrive;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Hello App Engine - Standard using "
            + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));

    String currentDirectory = "";

    Directorio MiDirectorio = new Directorio("MyDrive");


    currentDirectory = MiDirectorio.getPath();


    MiDirectorio.CrearDirectorio("Mis Archivos");
    MiDirectorio.CrearDirectorio("Compartido Conmigo");


    currentDirectory += MiDirectorio.getIntoDirectorio("Mis Archivos").getPath();



    MiDirectorio.getIntoDirectorio("Mis Archivos").CrearDirectorio("Mis apuntes");
    MiDirectorio.getIntoDirectorio("Mis Archivos").CrearDirectorio("Mis tareas");



    currentDirectory += MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes").getPath();



    //Creacion de un archivo
    Archivo archivo = new Archivo("Apuntes 1.txt");
    archivo.setVirtualPath(currentDirectory);

    MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes").AddArchivo(archivo);



    String CompleteDirectoryJson = MiDirectorio.getSerializableObject();


    response.getWriter().println(currentDirectory);

    response.getWriter().println(CompleteDirectoryJson);


  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
