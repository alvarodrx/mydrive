package com.operativos.mydrive;

import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class Usuario {

    private static final String fs_path = "/home/gerardo/Documents/TEC/Git Repos/Operativos-P3/mydrive/";

    private String nombre;
    private String correo;
    private String password;
    private Directorio root;

    public Usuario(String nombre, String correo, String password, boolean nuevo) throws Exception{

        if(nuevo && initializeUser()){
            throw new Exception("Un usuario con el nombre ya existe.");
        }else{
            this.nombre = nombre;
            this.correo = correo;
            this.password = password;
            this.root = initializeRoot();
        }


    }

    public String getNombre(){
        return this.nombre;
    }

    public String getSerializableObject(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Directorio getRoot(){
        return this.root;
    }

    public String getPassword(){
        return password;
    }

    public static ArrayList<String> getUsuarios(){
        File users_root = new File(fs_path + "Users/");
        ArrayList<String> nombres_usuarios = new ArrayList<>();

        for(File user_folder : users_root.listFiles()){
            nombres_usuarios.add(user_folder.getName());
        }

        return nombres_usuarios;
    }

    public static Usuario getUser(String email){

        for(String userName : getUsuarios()){
            File userData = new File(fs_path + "Users/" + userName + "/data.txt");

            try{
                InputStreamReader is = new InputStreamReader(new FileInputStream(userData));
                BufferedReader reader = new BufferedReader(is);

                String data = "";
                String linea = reader.readLine();

                while(linea != null){
                    data += linea;
                    linea = reader.readLine();
                }

                Gson gson = new Gson();
                Usuario user = gson.fromJson(data, Usuario.class);

                return user;

            }catch (Exception e){
                return null;
            }

        }

        return null;
    }

    private Directorio initializeRoot(){
        File root = new File(fs_path + "FS/" + this.nombre + "/MyDrive");

        // Si la raiz no existe la crea
        if(!root.exists())
            root.mkdirs(); //Crea el directorio actual y los parents


        Directorio MiDirectorio = new Directorio(root, "MyDrive/");

        return MiDirectorio;
    }

    private boolean initializeUser(){
        boolean encontrado = false;

        for(String user : getUsuarios()){
            if(user.equals(this.getNombre()))
                encontrado = true;
        }

        if(!encontrado){
            File dir = new File(fs_path + "Users/" + this.getNombre());
            dir.mkdir();
            updateData();
        }

        return encontrado;

    }

    public void updateData(){
        String data = this.getSerializableObject();
        File file = new File(fs_path + "Users/" + this.getNombre() + "/data.txt");

        if(!file.exists())
            try {
                file.createNewFile();
            }catch (Exception e){
                System.out.println("No pude crear el archivo de datos");
            }

        try{

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            writer.write(data);

        }catch (Exception e){
            System.out.println("No pude imprimir los datos");
        }

    }
}