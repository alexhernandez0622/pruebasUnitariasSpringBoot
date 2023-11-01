package com.example.pruebasUnitarias.Service;

import com.example.pruebasUnitarias.Entity.Producto;
import com.example.pruebasUnitarias.Repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    /*Insertaremos esta anotación para inyectar automáticamente los mocks*/
    @InjectMocks
    private ProductoService productoService;

    /*Esta anotación nos permite inicializar la simulación del comportamiento de los objetos*/
    @Mock
    private ProductoRepository productoRepository;

    /*Esta anotaciónd e JUnit indica que el método setUp se ejecutará antes de cada prueba, permitiendo que se inicialice primero las simulaciones y luego las pruebas de componentes*/
    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }

    @Test
   public void guardarProducto()
    {
        // Crear un objeto de prueba
        Producto producto = new Producto();

        // Configuramos el comportamiento del mock del repositorio para que me devuelva un dato al guardar el nombre del producto
        Mockito.when(productoRepository.save(Mockito.any(Producto.class))).thenReturn(producto);

        // Llamamos al método del servicio que se está testeando
        Producto resultado = productoService.guardarProducto(producto);

        // Afirmaremos que el dato de resultado coincida con el objeto que se está testeando
        assertEquals(producto, resultado);
    }

    @Test
    public void buscarPorNombre()
    {
        // Definir un nombre para buscar
        String nombre = "Producto1";

        // Crear un objeto de prueba
        Producto producto = new Producto();

        // Configurando el dato que se va a ingresar para buscarlo mediante su nombre
        Mockito.when(productoRepository.findByNombre(nombre)).thenReturn(Optional.of(producto));

        // Llamada al método del servicio que se está probando
        Optional<Producto> resultado = productoService.buscarPorNombre(nombre);

        // Afirmar que el resultado contiene un valor, si esta el dato que se busca y si conincide con el dato que está en el objeto
        assertTrue(resultado.isPresent());
        assertEquals(producto, resultado.get());
    }

    @Test
    public void listarProductos()
    {
        // Crear una lista de productos
        List<Producto> productos = new ArrayList<>();

        // Configuración para que me permita traer todos los datos
        Mockito.when(productoRepository.findAll()).thenReturn(productos);

        // Llamaremos al método que me permita llamar los productos, para realizar el test
        List<Producto> resultado = productoService.listarProductos();

        // Afirmaremos que los datos obtenidos coincida con la lista de productos de prueba
        assertEquals(productos, resultado);

    }

    @Test
    public void eliminarProducto()
    {
        // Definir un ID para eliminar un producto
        Long id = 1L;

        // Llamar al método del servicio que se está probando
        productoService.eliminarProducto(id);

        // Verificar que el método me elimino el id correspondiente
        Mockito.verify(productoRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void buscarPorId()
    {
        // Definir un ID para buscar un producto
        Long id = 1L;

        // Crear un objeto de prueba Producto
        Producto producto = new Producto();

        // Configurar el comportamiento del mock de ProductoRepository para devolver un Optional con el producto
        Mockito.when(productoRepository.findById(id)).thenReturn(Optional.of(producto));

        // Llamar al método del servicio que se está probando
        Optional<Producto> resultado = productoService.buscarPorId(id);

        // Afirmar que el resultado contiene un valor y que coincide con el objeto de prueba
        assertTrue(resultado.isPresent());
        assertEquals(producto, resultado);
    }
}