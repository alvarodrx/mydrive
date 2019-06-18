package com.operativos.mydrive;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;

public class Archivo {

    protected String name;
    protected String storageID;
    protected String virtual_path;
    protected String real_path;
    protected String extension;
    protected LocalDateTime uploadDate;
    protected LocalDateTime editDate;
    protected long size;

    public Archivo(File file, String virtual_path){

        this.name = file.getName();
        this.storageID = generarStorageID();
        this.uploadDate = LocalDateTime.now();
        this.real_path = file.getPath();
        this.size = file.length();
        this.virtual_path = virtual_path;

        try{
            this.extension = this.name.split(".")[1];
        }catch (Exception e){
            this.extension = "";
        }
    }

    public String getName(){
        return this.name;
    }

    public String getPath(){
        return virtual_path;
    }

    public String getRealPath(){
        return real_path;
    }

    public boolean copyVirtualToVirtual(Directorio dir){

        File file_ori = new File(this.real_path);
        File file_new = new File(dir.real_path + "/" + this.getName());

        try (InputStream reader = new FileInputStream(file_ori); OutputStream writer = new FileOutputStream(file_new))
        {
            while(reader.available() > 0){
                writer.write(reader.read());
            }

            Archivo archivo_new = new Archivo(file_new, dir.virtual_path + this.getName());
            dir.addChild(archivo_new);

            return true;

        }catch (Exception e){
            return false;
        }

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
