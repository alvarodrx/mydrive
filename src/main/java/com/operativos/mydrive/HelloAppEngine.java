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
    Directorio MiDirectorio = CreateRealDirectory.ReadDirectorio(usuario.getNombre());


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
