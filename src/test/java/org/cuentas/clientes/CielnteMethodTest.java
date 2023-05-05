package org.cuentas.clientes;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.cuentas.controller.ClienteController;
import org.cuentas.entity.Cliente;
import org.cuentas.repository.ClienteRepository;
import org.cuentas.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class CielnteMethodTest {

    private static final Long ID = 10L;
    private static final String NOMBRE = "Jesus Gabriel";
    private static final String GENERO = "m";
    private static final int EDAD = 34;
    private static final String IDENTIFICACION = "1";
    private static final String DIRECCION = "Direcion falsa";
    private static final String TELEFONO = "1";
    private static final String CONTRASENIA = "1";
    private static final boolean ESTADO = true;
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final Cliente cliente = new Cliente(ID, NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO,
            CONTRASENIA, ESTADO);

    @Inject
    private static ClienteRepository repository;

    @Inject
    private static ClienteService service;

    @Inject
    private static ClienteController controller;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ClienteRepository.class);
        service = new ClienteService(repository);
        controller = new ClienteController(service);
    }

    @Test
    public void listar_clientes_deberia_recibir_200() {
        // Arrange
        clientes.add(new Cliente(ID, NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO, CONTRASENIA, ESTADO));
        Mockito.when(service.findAll()).thenReturn(clientes);

        // Act
        Response response = controller.getAllClientes();

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(clientes, response.getEntity());
    }

    @Test
    public void listar_clientes_vacio_deberia_recibir_204() {
        // Arrange
        Mockito.when(service.findAll()).thenReturn(clientes);

        // Act
        Response response = controller.getAllClientes();

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatus());
        Assertions.assertEquals(clientes, response.getEntity());
    }

    @Test
    public void listar_cliente_deberia_recibir_200() {
        // Arrange
        Mockito.when(service.findById(ID)).thenReturn(cliente);

        // Act
        Response response = controller.getClienteById(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cliente, response.getEntity());
    }

    @Test
    public void listar_cliente_inexistente_deberia_recibir_404() {
        // Arrange
        Mockito.when(service.findById(7L)).thenReturn(cliente);

        // Act
        Response response = controller.getClienteById(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }

    @Test
    public void crear_cliente_deberia_recibir_201() {
        // Arrange
        Mockito.doNothing().when(repository).persist(cliente);

        // Act
        Response response = controller.postCliente(cliente);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatus());
        Assertions.assertEquals(cliente, response.getEntity());
    }

    @Test
    public void actualizar_cliente_deberia_recibir_200() {
        // Arrange
        Cliente clienteUpdate = new Cliente(ID, "Jesus Gabriel Delgado Leal", GENERO, EDAD, IDENTIFICACION, DIRECCION,
                TELEFONO, CONTRASENIA, ESTADO);
        Mockito.when(service.findById(ID)).thenReturn(cliente);

        // Act
        Response response = controller.putCliente(ID, clienteUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cliente, response.getEntity());
    }

    @Test
    public void actualizar_cliente_inexistente_deberia_recibir_404() {
        // Arrange
        Cliente clienteUpdate = new Cliente(ID, "Jesus Gabriel Delgado Leal", GENERO, EDAD, IDENTIFICACION, DIRECCION,
                TELEFONO, CONTRASENIA, ESTADO);
        Mockito.when(service.findById(7L)).thenReturn(null);

        // Act
        Response response = controller.putCliente(7L, clienteUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }

    @Test
    public void eliminar_cliente_deberia_recibir_204() {
        // Arrange
        Mockito.when(service.findById(ID)).thenReturn(cliente);

        // Act
        Response response = controller.deleteCliente(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatus());
    }

    @Test
    public void eliminar_cliente_inexistente_deberia_recibir_404() {
        // Arrange
        Mockito.when(service.findById(7L)).thenReturn(cliente);

        // Act
        Response response = controller.deleteCliente(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }

}
