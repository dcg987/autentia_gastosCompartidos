package com.autentia.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import javax.inject.Inject;

@MappedEntity
public class Friend {

    @Id
    @GeneratedValue
    private int id;
    private String nombre;
    private int id_grupo;

    @Inject
    public Friend(int id, String nombre, int id_grupo) {
        this.id = id;
        this.nombre = nombre;
        this.id_grupo = id_grupo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

}
