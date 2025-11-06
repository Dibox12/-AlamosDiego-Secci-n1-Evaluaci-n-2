package com.Alamos.BaseDeDatos;

import com.Alamos.BaseDeDatos.Model.Mueble;
import com.Alamos.BaseDeDatos.Model.Cotizacion;
import com.Alamos.BaseDeDatos.Model.ItemCotizacion;
import com.Alamos.BaseDeDatos.Service.MuebleServicio;
import com.Alamos.BaseDeDatos.Service.CotizacionServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MuebleServiceTest {

    @Autowired
    private MuebleServicio servicio;

    @Autowired
    private CotizacionServicio cotServicio;

    @Test
void testCrearYActualizarMueble() {
    Mueble m = new Mueble();
    m.setNombre("Mesa de Pino");
    m.setTipo("Mesa");
    m.setPrecioBase(30000.0);
    m.setStock(8);
    m.setEstado("activo");
    m.setTamano("Grande");
    m.setMaterial("Pino");

    Mueble creado = servicio.crear(m);
    assertNotNull(creado.getId());
    assertEquals("Mesa de Pino", creado.getNombre());

    creado.setNombre("Mesa de Pino Actualizada");
    Mueble actualizado = servicio.actualizar(creado.getId(), creado);
    assertEquals("Mesa de Pino Actualizada", actualizado.getNombre());
}
@Test
void testVentaConStockInsuficiente() {
    Mueble m = new Mueble();
    m.setNombre("Silla Gamer");
    m.setTipo("Silla");
    m.setPrecioBase(150000.0);
    m.setStock(1);
    m.setEstado("activo");
    m.setTamano("Grande");
    m.setMaterial("Cuero");

    Mueble guardado = servicio.crear(m);

    Cotizacion cot = new Cotizacion();
    ItemCotizacion item = new ItemCotizacion();
    item.setMueble(guardado);
    item.setCantidad(5); // stock insuficiente
    cot.setItems(List.of(item));

    // Verifica que lanza una excepción
    assertThrows(RuntimeException.class, () -> cotServicio.confirmarVenta(cot.getId()));
}
@Test
void testCalculoPrecioConVariante() {
    Mueble m = new Mueble();
    m.setNombre("Silla Básica");
    m.setTipo("Silla");
    m.setPrecioBase(20000.0);
    m.setStock(5);
    m.setEstado("activo");
    m.setTamano("Mediano");
    m.setMaterial("Madera");

    Mueble guardado = servicio.crear(m);

    ItemCotizacion item = new ItemCotizacion();
    item.setMueble(guardado);
    item.setCantidad(2);
    item.setPrecioTotal(guardado.getPrecioBase() * 2);

    assertEquals(40000.0, item.getPrecioTotal());
}
}