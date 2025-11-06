
package com.Alamos.BaseDeDatos.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "item_cotizacion")
public class ItemCotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Mueble mueble;
    @ManyToOne private Variante variante;
    private int cantidad;
    private double precioTotal;

    @ManyToOne
    @JoinColumn(name = "cotizacion_id")
    @JsonBackReference // 👈 Ignora la referencia inversa para evitar loops
    private Cotizacion cotizacion;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Mueble getMueble() { return mueble; }
    public void setMueble(Mueble mueble) { this.mueble = mueble; }

    public Variante getVariante() { return variante; }
    public void setVariante(Variante variante) { this.variante = variante; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }
}
