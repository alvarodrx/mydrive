package com.operativos.mydrive;

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
        this.name = name;
        this.StorageID = generarStorageID();
        this.extension = name.substring(name.length() - 4);


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

    public boolean CopiarRealaVirtual(){
        //agregar la logica
        return true;
    }
    public boolean CopiarVirtualaReal(){
        //agergar la logica
        return true;
    }
    public boolean CopiarVirtualaVirtual(){
        //agregar la logica
        return true;
    }

    public boolean Mover(){
        //logica de mover archivo
        return true;
    }

    public boolean Eliminar(){
        //logica de eleminar archivo
        return true;
    }

    private String generarStorageID(){
        //Aqui s puede modelar la logica de subir el archvo al storage y devolver su id para acceder luego
        return "prueba";
    }

}
