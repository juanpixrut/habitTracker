package com.juanfontes.habittracker.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habito> habitos = new ArrayList<>();

    public Usuario(){

    }

    public Usuario(String email){
        this.email = email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setHabito(Habito habito){
        habitos.add(habito);
    }

    public List<Habito> getHabitos(){
        return habitos;
    }

    public int getId(){
        return this.id;
    }

    public static Usuario RequestToUsuario(RegisterRequest request){
        Usuario user = new Usuario();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

}
