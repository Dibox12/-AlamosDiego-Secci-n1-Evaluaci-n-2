package com.Alamos.BaseDeDatos.Repository;

import com.Alamos.BaseDeDatos.Model.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioMueble extends JpaRepository<Mueble, Long> {}
