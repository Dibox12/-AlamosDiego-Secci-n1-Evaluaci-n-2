
package com.Alamos.BaseDeDatos.Controller;

import com.Alamos.BaseDeDatos.Model.Mueble;
import com.Alamos.BaseDeDatos.Service.MuebleServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleControlador {

    @Autowired
    private MuebleServicio servicio;

    @GetMapping
    public List<Mueble> listar() { return servicio.listar(); }

    @PostMapping
    public Mueble crear(@RequestBody Mueble mueble) { return servicio.crear(mueble); }

    @PutMapping("/{id}")
    public Mueble actualizar(@PathVariable Long id, @RequestBody Mueble mueble) {
        return servicio.actualizar(id, mueble);
    }

    @PutMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) { servicio.desactivar(id); }
}
