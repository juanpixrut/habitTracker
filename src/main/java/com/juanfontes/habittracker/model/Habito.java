package com.juanfontes.habittracker.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Habito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    private boolean completado;

    public Habito(){

    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
        public void setDescripcion(String desc){
        this.descripcion = desc;
    }
        public void setFechaInicio(LocalDate fecha){
        this.fechaInicio = fecha;
    }

        public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }
    
        public LocalDate getFechaInicio(){
        return fechaInicio;
    }

        public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public boolean isCompletado(){
        return completado;
    }

    public void setCompletado(boolean estado){
        this.completado = estado;
    }

    
}
