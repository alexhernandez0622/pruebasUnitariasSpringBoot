package com.example.pruebasUnitarias.Service;

import com.example.pruebasUnitarias.Entity.Producto;
import com.example.pruebasUnitarias.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService
{
    @Autowired
    private ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombre(nombre);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }
}
