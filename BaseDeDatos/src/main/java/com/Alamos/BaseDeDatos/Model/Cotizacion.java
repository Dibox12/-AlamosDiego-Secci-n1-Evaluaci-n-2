package com.Alamos.BaseDeDatos.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean confirmada;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL)
    @JsonManagedReference // 👈 Evita la recursión hacia atrás
    private List<ItemCotizacion> items;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    public List<ItemCotizacion> getItems() { return items; }
    public void setItems(List<ItemCotizacion> items) { this.items = items; }
}
