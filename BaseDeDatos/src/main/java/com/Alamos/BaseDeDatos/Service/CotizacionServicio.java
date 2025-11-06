package com.Alamos.BaseDeDatos.Service;

import com.Alamos.BaseDeDatos.Model.*;
import com.Alamos.BaseDeDatos.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionServicio {
    @Autowired private RepositorioCotizacion cotRepo;
    @Autowired private RepositorioMueble muebleRepo;
    @Autowired private RepositorioVariante varianteRepo;

    public Cotizacion crearCotizacion(Cotizacion cot) {
    for (ItemCotizacion item : cot.getItems()) {
        // Buscar el mueble real desde la base de datos
        Mueble muebleBD = muebleRepo.findById(item.getMueble().getId())
                .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

        item.setMueble(muebleBD); // Reemplazamos el objeto con el de BD

        double precio = muebleBD.getPrecioBase();

        // Si tiene variante, buscarla también desde la BD
        if (item.getVariante() != null && item.getVariante().getId() != null) {
            Variante varBD = varianteRepo.findById(item.getVariante().getId())
                    .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
            item.setVariante(varBD);
            precio += varBD.getIncrementoPrecio();
        }

        item.setPrecioTotal(precio * item.getCantidad());
        item.setCotizacion(cot);
    }

    cot.setConfirmada(false);
    return cotRepo.save(cot);
}


    public Cotizacion confirmarVenta(Long id) {
        Cotizacion cot = cotRepo.findById(id).orElseThrow();
        if (cot.isConfirmada()) throw new RuntimeException("Cotización ya confirmada");
        for (ItemCotizacion item : cot.getItems()) {
            Mueble m = item.getMueble();
            if (m.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para " + m.getNombre());
            }
            m.setStock(m.getStock() - item.getCantidad());
            muebleRepo.save(m);
        }
        cot.setConfirmada(true);
        return cotRepo.save(cot);
    }
}
