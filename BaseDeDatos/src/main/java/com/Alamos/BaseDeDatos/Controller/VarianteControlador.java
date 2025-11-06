package com.Alamos.BaseDeDatos.Controller;

import com.Alamos.BaseDeDatos.Model.Variante;
import com.Alamos.BaseDeDatos.Repository.RepositorioVariante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteControlador {

    @Autowired
    private RepositorioVariante repo;

    @GetMapping
    public List<Variante> listar() {
        return repo.findAll();
    }

    @PostMapping
    public Variante crear(@RequestBody Variante variante) {
        return repo.save(variante);
    }
}
