package com.operativos.mydrive;

import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author XPC
 */
public class CreateRealDirectory {

    private static CreateRealDirectory instance = null;

    private static String AbsPath = "C:/Users/XPC/Desktop/mydrive/";

    public static CreateRealDirectory getInstance()
    {
        if (instance == null)
            instance = new CreateRealDirectory();

        return instance;
    }

    public static void MakeFolderforUsuario(String username){
        File dir = new File(AbsPath+"Usuarios/" + username);
        dir.mkdir();

    }

    public static void MakeFolderforData(String username){
        File dir = new File(AbsPath+"BD/" + username);
        dir.mkdir();

    }

    public static void SaveUsuario(String username, String data) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(AbsPath+"Usuarios/" + username + "/data.txt", "UTF-8");
        writer.print(data);
        writer.close();
    }

    public static Usuario ReadUsuario(String username) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(AbsPath+"Usuarios/" + username+"/data.txt")), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(text, Usuario.class);
        return usuario;
    }

    public static void SaveDirectorio(String username, String data) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(AbsPath+"Usuarios/" + username + "/FS.txt", "UTF-8");
        writer.print(data);
        writer.close();
    }

    public static Directorio ReadDirectorio(String username) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(AbsPath+"Usuarios/" + username+"/FS.txt")), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Directorio dir = gson.fromJson(text, Directorio.class);
        return dir;
    }

    public static void SaveArchivo(String username, Archivo archivo)throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(AbsPath+"BD/" + username + "/" + archivo.getID() +".txt", "UTF-8");
        writer.print(archivo.getContenido());
        writer.close();
    }
}