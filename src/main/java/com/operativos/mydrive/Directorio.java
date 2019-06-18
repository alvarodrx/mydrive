package com.operativos.mydrive;

import com.google.appengine.repackaged.com.google.gson.Gson;


import java.util.ArrayList;
import java.util.StringTokenizer;

public class Directorio {
    private String root;
    private ArrayList<Directorio> childs;
    private ArrayList<Archivo> archivos;

    public Directorio(String nombre){
        this.root = nombre;
        childs = new ArrayList<>();
        archivos = new ArrayList<>();
    }

    public void CrearDirectorio(String nombreDirectorio){
        Directorio directorio = new Directorio(nombreDirectorio);
        this.childs.add(directorio);
    }

    public void AddArchivo(Archivo archivo){
        this.archivos.add(archivo);
    }

    public Directorio getIntoDirectorio(String nombre){
        for(Directorio directorio : this.childs){
            if(directorio.root.equals(nombre)){return directorio;}
        }
        return null;
    }

    public String getPath(){
        return  this.root + "/";
    }

    public String getSerializableObject(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public ArrayList<Directorio> getDirectorios(){
        return this.childs;
    }

    public ArrayList<Archivo> getArchivos(){
        return this.archivos;
    }

    public Directorio gotoPath(String path){
        Directorio result = this;
        StringTokenizer tokens = new StringTokenizer(path, "/");
        String nombre;
        while(tokens.hasMoreTokens()){
            nombre = tokens.nextToken();
            if(this.root.equals(nombre)){nombre = tokens.nextToken();}
            result = result.getIntoDirectorio(nombre);
        }
        return result;
    }
}
