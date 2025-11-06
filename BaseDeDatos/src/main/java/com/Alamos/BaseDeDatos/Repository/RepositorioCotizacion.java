package com.Alamos.BaseDeDatos.Repository;

import com.Alamos.BaseDeDatos.Model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioCotizacion extends JpaRepository<Cotizacion, Long> {}
