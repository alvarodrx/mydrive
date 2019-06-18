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

    public String getRoot(){
        return this.root;
    }
    public void CrearDirectorio(String nombreDirectorio){
        Directorio directorio = new Directorio(nombreDirectorio);
        this.childs.add(directorio);
    }

    public void AddArchivo(Archivo archivo){
        this.archivos.add(archivo);
    }

    public void AddDirectorio(Directorio dir){
        this.childs.add(dir);
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

    private int getIndexDirectorio(String nombre){
        int index = 0;
        for(Directorio directorio : this.childs){
            if(directorio.root.equals(nombre)){return index;}
            index++;
        }return -1;
    }

    private int getIndexArchivo(String nombre){
        int index = 0;
        for(Archivo archivo : this.archivos){
            if(archivo.getName().equals(nombre)){return index;}
            index++;
        }return -1;
    }

    public boolean EliminarDirectorio(String nombre){
        int index = getIndexDirectorio(nombre);
        if(index == -1){return false;}
        this.childs.remove(index);
        return true;
    }

    public boolean EliminarArchivo(String nombre){
        int index = getIndexArchivo(nombre);
        if(index == -1){return false;}
        this.archivos.remove(index);
        return true;
    }

    public ArrayList<String> getNombresDirectorios(){
        ArrayList<String> result = new ArrayList<>();
        for(Directorio dir : this.childs){
            result.add(dir.getRoot());
        }
        return result;
    }

    public ArrayList<String> getNombresArchivos(){
        ArrayList<String> result = new ArrayList<>();
        for(Archivo archivo : this.archivos){
            result.add(archivo.getName());
        }
        return result;
    }

    public boolean CopiarArchivoVirtualVirtual(Directorio dir, String path, String nombre){
        for(Archivo archivo : this.archivos){
            if(archivo.getName().equals(nombre)){dir.gotoPath(path).AddArchivo(archivo); return true;}
        }
        return false;
    }

    public void MoverArchivo(){}

    public void MoverDirectorio(){}


}
