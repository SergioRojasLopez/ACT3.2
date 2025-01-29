package org.example;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

/**
 * Esta NamedQueries es una consulta predefinida que la reutlizo en el main
 */
@NamedQueries({
        @NamedQuery(name = "Juego.findAll", query = "SELECT j FROM Juego j"),
})
public class Juego {
    @Id
    /**
     * GeneratedValue(IDENTITY) -> Indicamos que el valor de la clave primaria
     * será generado automáticamente por la base de datos al insertar un nuevo registro.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJuego;

    @Column(name = "Videojuego", nullable = false, length = 50)
    private String nombreJuego;

    @Basic
    @Column(name = "Fecha", nullable = false)
    private Date fechaJuego;

    @Column(name = "Plataforma", nullable = false, length = 50)
    private String plataforma;

    /**
     * ManyToMany -> Indica que un juego puede estar asociado a varios desarrolladores y, a su vez, una desarrolladora puede estar asociado a varios juegos.
     * JoinTable -> Especifica el nombre de la tabla que se creará en la base de datos para establecer la relación entre los desarrolladores y los juegos.
     * JoinColumns -> Especificamos el nombre de la columna que se creará en la base de datos para establecer la relación entre los desarrolladores y los juegos.
     * InverseJoinColumns -> Especifica el nombre de la columna que se creará en la base de datos para vincular los frikis con los juegos.
     */

    @ManyToMany
    @JoinTable(name = "Juego_Desarrollador",
            joinColumns = @JoinColumn(name = "juego_id"),
            inverseJoinColumns = @JoinColumn(name = "desarrollador_id")
    )
    Set<Desarrollador> desarrolladores = new HashSet<>();


    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Date getFechaJuego() {
        return fechaJuego;
    }

    public void setFechaJuego(Date fechaJuego) {
        this.fechaJuego = fechaJuego;
    }

    public Set<Desarrollador> getDesarrolladores() {
        return desarrolladores;
    }

    public void setDesarrolladores(Set<Desarrollador> desarrolladores) {
        this.desarrolladores = desarrolladores;
    }
}
