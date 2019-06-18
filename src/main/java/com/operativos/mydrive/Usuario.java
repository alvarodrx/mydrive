package com.operativos.mydrive;

import com.google.appengine.repackaged.com.google.gson.Gson;

public class Usuario {
    private String nombre;
    private String correo;
    private String password;

    public Usuario(String nombre, String correo, String password){
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getSerializableObject(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
