package com.Alamos.BaseDeDatos.Service;

import com.Alamos.BaseDeDatos.Model.Mueble;
import com.Alamos.BaseDeDatos.Repository.RepositorioMueble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MuebleServicio {
    @Autowired
    private RepositorioMueble repo;

    public List<Mueble> listar() { return repo.findAll(); }
    public Mueble crear(Mueble mueble) { return repo.save(mueble); }
    public Mueble actualizar(Long id, Mueble datos) {
        Mueble mueble = repo.findById(id).orElseThrow();
        mueble.setNombre(datos.getNombre());
        mueble.setTipo(datos.getTipo());
        mueble.setPrecioBase(datos.getPrecioBase());
        mueble.setStock(datos.getStock());
        mueble.setEstado(datos.getEstado());
        mueble.setTamano(datos.getTamano());
        mueble.setMaterial(datos.getMaterial());
        return repo.save(mueble);
    }
    public void desactivar(Long id) {
        Mueble mueble = repo.findById(id).orElseThrow();
        mueble.setEstado("inactivo");
        repo.save(mueble);
    }
}
