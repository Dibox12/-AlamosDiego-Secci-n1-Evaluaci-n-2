package com.Alamos.BaseDeDatos.Controller;

import com.Alamos.BaseDeDatos.Model.Cotizacion;
import com.Alamos.BaseDeDatos.Service.CotizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionControlador {

    @Autowired
    private CotizacionServicio servicio;

    @PostMapping
    public Cotizacion crear(@RequestBody Cotizacion cotizacion) {
        return servicio.crearCotizacion(cotizacion);
    }

    @PutMapping("/{id}/confirmar")
    public Cotizacion confirmar(@PathVariable Long id) {
        return servicio.confirmarVenta(id);
    }
}
