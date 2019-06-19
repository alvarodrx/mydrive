package com.operativos.mydrive;

import com.google.appengine.repackaged.com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Directorio extends Archivo{

    private ArrayList<Archivo> children;

    public Directorio(File file, String virtual_path){
        super(file, virtual_path);
        this.children = new ArrayList<>();
    }

    public void createDirectorio(String nombreDirectorio){

        // Crea el directorio fisicamente y obtiene el File
        File dir = new File(this.getRealPath() + "/" + nombreDirectorio);
        if(dir.mkdir())
            System.out.println("Se ha creado con exito la ruta: " + this.getRealPath() + "/" + nombreDirectorio);
        else
            System.out.println("No se ha creado con exito la ruta: " + this.getRealPath() + "/" + nombreDirectorio);

        // Crea el objeto directorio
        Directorio directorio = new Directorio(dir,this.virtual_path + nombreDirectorio + "/");

        this.children.add(directorio);
    }

    public void addChild(Archivo archivo){
        this.children.add(archivo);
    }

    public Directorio getIntoDirectorio(String nombre){
        for(Archivo archivo : this.children){
            if(archivo.getName().equals(nombre)){return (Directorio)archivo;}
        }
        return null;
    }

    public String getSerializableObject(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public ArrayList<Archivo> getChildren(){
        return this.children;
    }

    public Directorio gotoPath(String path){

        Directorio result = this;
        StringTokenizer tokens = new StringTokenizer(path, "/");
        String nombre;

        while(tokens.hasMoreTokens()){

            nombre = tokens.nextToken();

            if(result != null && result.getName().equals(nombre)){
                nombre = tokens.nextToken();
            }

            result = result.getIntoDirectorio(nombre);
        }

        return result;
    }

    private int getIndexChild(String nombre){
        int index = 0;
        for(Archivo archivo: this.children){
            if(archivo.getName().equals(nombre)){return index;}
            index++;
        }return -1;
    }

    public boolean deleteChild(String nombre){
        int index = getIndexChild(nombre);
        if(index == -1){ return false; }
        this.children.remove(index);
        return true;
    }

    public ArrayList<String> getChildrenNames(){
        ArrayList<String> result = new ArrayList<>();
        for(Archivo file: this.children){
            result.add(file.getName());
        }
        return result;
    }

    public boolean copyVirtualToVirtual(Directorio dir){

        // "Copia" la carpeta
        dir.createDirectorio(this.getName());
        dir = dir.getIntoDirectorio(this.getName());

        // Copia cada child (archivos y carpetas)
        for(Archivo archivo : this.children){

            if(!archivo.copyVirtualToVirtual(dir))
                return false;
        }

        return true;
    }

    public boolean delete(Directorio parent){

        for(Archivo archivo : this.getChildren()){
            archivo.delete(this);
        }

        return super.delete(parent);
    }


}
