package org.example;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Desarrollador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDesarrollador;

    @Column(name = "Desarrolladora",nullable = false, length = 50)
    private String nombre;

    @Column(name = "Pais",nullable = false, length = 50)
    private String pais;


    @ManyToMany
    Set<Juego> juegos = new HashSet<>();

    public void setIdDesarrollador(int idDesarrollador) {
        this.idDesarrollador = idDesarrollador;
    }

    public int getIdDesarrollador() {
        return idDesarrollador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Set<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(Set<Juego> juegos) {
        this.juegos = juegos;
    }
}
