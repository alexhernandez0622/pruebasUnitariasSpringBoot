package com.example.pruebasUnitarias.Controller;

import com.example.pruebasUnitarias.Entity.Producto;
import com.example.pruebasUnitarias.Service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoControllerTest {
    /*se utiliza para inyectar automáticamente los mocks */
    @InjectMocks
    private ProductoController productoController;

    /*Los mocks se utilizan para simular el comportamiento de objetos reales y se usan en lugar de los objetos reales durante las pruebas.*/
    @Mock
    private ProductoService productoService;

    /*Esta anotación de JUnit indica que el método setUp se ejecutará antes de cada prueba. En el método setUp, se inicializan los mocks utilizando MockitoAnnotations.openMocks(this), lo que configura los mocks antes de ejecutar cada prueba.*/
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCrearProducto() {
        // Crear un objeto de prueba Producto
        Producto producto = new Producto();

        // Configurar el comportamiento del mock de ProductoService
        Mockito.when(productoService.guardarProducto(Mockito.any(Producto.class))).thenReturn(producto);

        // Llamar al método del controlador que se está probando
        Producto resultado = productoController.crearProducto(producto);

        // Afirmar que el resultado no es nulo y que coincide con el objeto de prueba
        assertNotNull(resultado);
        assertEquals(producto, resultado);
    }

    @Test
    public void testObtenerProductoPorNombre() {
        // Definir un nombre para buscar
        String nombre = "Producto1";

        // Crear un objeto de prueba Producto
        Producto producto = new Producto();

        // Configurar el comportamiento del mock de ProductoService para devolver un Optional con el producto
        Mockito.when(productoService.buscarPorNombre(nombre)).thenReturn(Optional.of(producto));

        // Llamar al método del controlador que se está probando
        ResponseEntity<Producto> response = productoController.obtenerProductoPorNombre(nombre);

        // Afirmar que el código de estado de la respuesta es 200 y que el cuerpo de la respuesta coincide con el objeto de prueba
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(producto, response.getBody());
    }

    @Test
    public void testListarProductos() {
        // Crear una lista de productos de prueba
        List<Producto> productos = new ArrayList<>();

        // Configurar el comportamiento del mock de ProductoService para devolver la lista de productos
        Mockito.when(productoService.listarProductos()).thenReturn(productos);

        // Llamar al método del controlador que se está probando
        List<Producto> resultado = productoController.listarProductos();

        // Afirmar que el resultado no es nulo y que coincide con la lista de productos de prueba
        assertNotNull(resultado);
        assertEquals(productos, resultado);
    }

    @Test
    public void testActualizarProducto() {
        // Definir un ID para buscar un producto existente
        Long id = 1L;

        // Crear un objeto de prueba Producto existente y uno nuevo
        Producto productoExistente = new Producto();
        productoExistente.setId(id);
        Producto nuevoProducto = new Producto();
        nuevoProducto.setId(id);

        // Configurar el comportamiento del mock de ProductoService para devolver el producto existente al buscar por ID
        Mockito.when(productoService.buscarPorId(id)).thenReturn(Optional.of(productoExistente));

        // Configurar el mock de ProductoService para devolver el nuevo producto al guardarProducto
        Mockito.when(productoService.guardarProducto(Mockito.any(Producto.class))).thenReturn(nuevoProducto);

        // Llamar al método del controlador que se está probando
        ResponseEntity<Producto> response = productoController.actualizarProducto(id, nuevoProducto);

        // Afirmar que el código de estado de la respuesta es 200 y que el cuerpo de la respuesta coincide con el nuevo producto
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(nuevoProducto, response.getBody());
    }

    @Test
    public void testEliminarProducto()
    {
        // Definir un ID para eliminar un producto
        Long id = 1L;

        // Llamar al método del controlador que se esta probando
        ResponseEntity<Void> response = productoController.eliminarProducto(id);

        // Afirmar que el código de estado de la respuesta es el 204 (sin contenido)
        assertEquals(204, response.getStatusCodeValue());
    }
}
