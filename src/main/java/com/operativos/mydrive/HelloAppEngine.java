package com.operativos.mydrive;

import com.google.appengine.api.utils.SystemProperty;

import java.io.IOException;
import java.io.File;
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

    String fs_path = "/home/gerardo/Documents/TEC/Git Repos/Operativos-P3/mydrive/";

    Properties properties = System.getProperties();

    response.setContentType("text/plain");
    response.getWriter().println("Hello App Engine - Standard using "
            + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));

    //Creacion de archivo raiz
    File root = new File(fs_path + "FS/Gerardo/MyDrive");

    // Si la raiz no existe la crea
    if(!root.exists())
      root.mkdirs(); //Crea el directorio actual y los parents


    Directorio MiDirectorio = new Directorio(root, "MyDrive/");
    String currentDirectory = MiDirectorio.getPath();

    //Creacion de directorios por defecto
    MiDirectorio.createDirectorio("Mis Archivos");
    MiDirectorio.createDirectorio("Compartido Conmigo");

    // Obtiene el path de mis archivos
    currentDirectory += MiDirectorio.getIntoDirectorio("Mis Archivos").getName() + "/";

    //Entrar a un directorio y crear directorios random
    MiDirectorio.getIntoDirectorio("Mis Archivos").createDirectorio("Mis apuntes");
    MiDirectorio.getIntoDirectorio("Mis Archivos").createDirectorio("Mis tareas");


    Directorio currentDirectoryFile = MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis apuntes");
    currentDirectory += currentDirectoryFile.getName() + "/";


    //Creacion de un archivo
    File file = new File(currentDirectoryFile.getRealPath() + "/Apuntes.txt");

    if(!file.exists())
      if(file.createNewFile())
        System.out.println("Se ha creado con exito el archivo: " + currentDirectoryFile.getRealPath() + "/Apuntes.txt");
      else
        System.out.println("No se ha creado con exito el archivo: " + currentDirectoryFile.getRealPath() + "/Apuntes.txt");

    Archivo archivo = new Archivo(file, currentDirectory + "Apuntes.txt");
    currentDirectoryFile.addChild(archivo);

    Directorio tareas = MiDirectorio.getIntoDirectorio("Mis Archivos").getIntoDirectorio("Mis tareas");
    archivo.copyVirtualToVirtual(tareas);

    /*
    //Entrar a un directorio y eliminar un directorio hijo
    boolean result = MiDirectorio.getIntoDirectorio("Mis Archivos").EliminarDirectorio("Mis tareas");
    response.getWriter().println(result);


    //desplazarse a una direccion y agregar un archivo
    MiDirectorio.gotoPath("/Compartido Conmigo").addChild(archivo);


    //desplazarse a una direccion y eliminar un archivo
    result = MiDirectorio.gotoPath("/Compartido Conmigo").EliminarArchivo("Apuntes 1.txt");
    response.getWriter().println(result);



    //desplazarse a una direccion y capiar un directorio
    result = MiDirectorio.gotoPath("/Mis Archivos/Mis apuntes/").CopiarArchivoVirtualVirtual(MiDirectorio,"/Compartido Conmigo", "Apuntes 1.txt");
    response.getWriter().println(result);

    //desplazarse a una direccion y copiar un archivo
    result = MiDirectorio.gotoPath("/Mis Archivos/").CopiarDirectorioVirtualVirtual(MiDirectorio,"/Compartido Conmigo", "Mis apuntes");
    response.getWriter().println(result);

    */

    //seralizar el objeto directorio y pasarlo a json
    String CompleteDirectoryJson = MiDirectorio.getSerializableObject();


    //mostrar el producto de todo lo anterior 
    response.getWriter().println(currentDirectory);

    response.getWriter().println(currentDirectoryFile.getPath());

    response.getWriter().println(CompleteDirectoryJson);

  }

  public static String getInfo() {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }

}
