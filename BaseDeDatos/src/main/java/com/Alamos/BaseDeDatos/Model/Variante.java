package com.Alamos.BaseDeDatos.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "variante")
public class Variante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private Double incrementoPrecio;

    @ManyToOne
    @JoinColumn(name = "mueble_id")
    private Mueble mueble;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getIncrementoPrecio() { return incrementoPrecio; }
    public void setIncrementoPrecio(Double incrementoPrecio) { this.incrementoPrecio = incrementoPrecio; }

    public Mueble getMueble() { return mueble; }
    public void setMueble(Mueble mueble) { this.mueble = mueble; }
}
