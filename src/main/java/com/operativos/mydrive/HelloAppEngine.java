package com.operativos.mydrive;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

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

   // Usuario usuario = new Usuario("Martin", "martin@algo.com", "123456");

   // CreateRealDirectory.MakeFolderforUsuario(usuario.getNombre());
   // CreateRealDirectory.SaveUsuario(usuario.getNombre(), usuario.getSerializableObject());

    Usuario usuario = CreateRealDirectory.ReadUsuario("Martin");
    response.getWriter().println(usuario.getNombre());

    String currentDirectory = "";

    //Creacion de archivo raiz
    Directorio MiDirectorio = new Directorio("MyDrive");


    currentDirectory = MiDirectorio.getPath();

    //Creacion de directorios por defecto
    MiDirectorio.CrearDirectorio("Mis Archivos");
    MiDirectorio.CrearDirectorio("Compartido Conmigo");


    currentDirectory += MiDirectorio.getIntoDirectorio("Mis Archivos").getPath();


    //Entrar a un directorio y crear directorios random
    MiDirectorio.getIntoDirectorio("Mis Archivos").CrearDirectorio("Mis apuntes");
    MiDirectorio.getIntoDirectorio("Mis Archivos").CrearDirectorio("Mis tareas");



    currentDirectory += MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes").getPath();



    //Creacion de un archivo
    Archivo archivo = new Archivo("Apuntes 1.txt");
    archivo.setVirtualPath(currentDirectory);

    //entrar a un directorio y agregar un archivo
    MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes").AddArchivo(archivo);
    MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes").CrearDirectorio("Recordatorios");


    //Entrar a un directorio y eliminar un directorio hijo
    boolean result = MiDirectorio.getIntoDirectorio("Mis Archivos").EliminarDirectorio("Mis tareas");
    response.getWriter().println(result);


    //desplazarse a una direccion y agregar un archivo
    MiDirectorio.gotoPath("/Compartido Conmigo").AddArchivo(archivo);


    CreateRealDirectory.SaveDirectorio(usuario.getNombre(), MiDirectorio.getSerializableObject());

    //seralizar el objeto directorio y pasarlo a json
    String CompleteDirectoryJson = MiDirectorio.getSerializableObject();




    //mostrar el producto de todo lo anterior 
    response.getWriter().println(currentDirectory);

    response.getWriter().println(CompleteDirectoryJson);


  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
