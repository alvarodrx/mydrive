package com.operativos.mydrive;

import java.util.ArrayList;
import java.util.Date;

public class Archivo {

    private String name;
    private String StorageID;
    private String virtual_path;
    private String real_path;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private int size;
    private String extension;

    public Archivo(String name){
        this.name = name.substring(0, name.lastIndexOf('.'));
        this.StorageID = generarStorageID();
        this.extension = name.substring(name.length() - 4);
        this.fechaCreacion = new Date();


    }

    public String getName(){
        return this.name;
    }

    public void setVirtualPath(String path){
        this.virtual_path = path;
    }

    public void setRealPath(String path){
        this.real_path = path;
    }


    private String generarStorageID(){
        //Aqui se puede modelar la logica de subir el archvo al storage y devolver su id para acceder luego
        return "prueba";
    }

    public ArrayList<String> verPropiedades(){
        ArrayList<String> propiedades = new ArrayList<>();
        propiedades.add(this.name);
        propiedades.add(this.extension);
        propiedades.add(this.fechaCreacion.toString());
        propiedades.add(this.fechaModificacion.toString());
        propiedades.add(Integer.toString(this.size));
        return  propiedades;
    }

}
