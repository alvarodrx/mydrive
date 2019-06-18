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

    private static String AbsPath = "C:/Users/casa/Desktop/mydrive/Usuarios/";

    public static CreateRealDirectory getInstance()
    {
        if (instance == null)
            instance = new CreateRealDirectory();

        return instance;
    }

    public static void MakeFolderforUsuario(String name){
        File dir = new File(AbsPath + name);
        dir.mkdir();

    }

    public static void SaveUsuario(String nombre, String data) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(AbsPath + nombre + "/data.txt", "UTF-8");
        writer.print(data);
        writer.close();
    }

    public static Usuario ReadUsuario(String nombre) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(AbsPath + nombre+"/data.txt")), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(text, Usuario.class);
        return usuario;
    }

    public static void SaveDirectorio(String nombre, String data) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(AbsPath + nombre + "/FS.txt", "UTF-8");
        writer.print(data);
        writer.close();
    }

    public static Directorio ReadDirectorio(String nombre) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(AbsPath + nombre+"/FS.txt")), StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Directorio dir = gson.fromJson(text, Directorio.class);
        return dir;
    }
}