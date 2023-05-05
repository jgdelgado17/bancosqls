package org.cuentas.cuenta;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.cuentas.controller.CuentaController;
import org.cuentas.entity.Cliente;
import org.cuentas.entity.Cuenta;
import org.cuentas.repository.CuentaRepository;
import org.cuentas.service.CuentaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class CuentaMethodTest {
    private static final Long ID_CL = 10L;
    private static final String NOMBRE = "Jesus Gabriel";
    private static final String GENERO = "m";
    private static final int EDAD = 34;
    private static final String IDENTIFICACION = "1";
    private static final String DIRECCION = "Direcion falsa";
    private static final String TELEFONO = "1";
    private static final String CONTRASENIA = "1";
    private static final boolean ESTADO_C = true;
    private static final Cliente cliente = new Cliente(ID_CL, NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO,
            CONTRASENIA, ESTADO_C);

    private static final Long ID = 1L;
    private static final String NUMERO_CUENTA = "111";
    private static final String TIPO_CUENTA = "Debito";
    private static final float SALDO = 1000;
    private static final boolean ESTADO = true;
    private static final Cuenta cuenta = new Cuenta(ID, NUMERO_CUENTA, TIPO_CUENTA, SALDO, ESTADO, cliente);

    private static final List<Cuenta> cuentas = new ArrayList<>();

    @Inject
    private static CuentaRepository repository;

    @Inject
    private static CuentaService service;

    @Inject
    private static CuentaController controller;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CuentaRepository.class);
        service = new CuentaService(repository);
        controller = new CuentaController(service);
    }

    @Test
    public void listar_cuentas_deberia_recibir_200() {
        // Arrange        
        cuentas.add(cuenta);

        Mockito.when(service.findAll()).thenReturn(cuentas);

        // Act
        Response response = controller.getAllCuentas();

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cuentas, response.getEntity());
    }

    @Test
    public void listar_cuentas_vacio_deberia_recibir_204() {
        // Arrange
        Mockito.when(service.findAll()).thenReturn(cuentas);

        // Act
        Response response = controller.getAllCuentas();

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatus());
        Assertions.assertEquals(cuentas, response.getEntity());
    }

    @Test
    public void listar_cuenta_deberia_recibir_200() {
        // Arrange
        Mockito.when(service.findById(ID)).thenReturn(cuenta);

        // Act
        Response response = controller.getCuentaById(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cuenta, response.getEntity());
    }

    @Test
    public void listar_cuenta_inexistente_deberia_recibir_404() {
        // Arrange
        Mockito.when(service.findById(7L)).thenReturn(cuenta);

        // Act
        Response response = controller.getCuentaById(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }

    @Test
    public void crear_cuenta_deberia_recibir_201() {
        // Arrange
        Mockito.doNothing().when(repository).persist(cuenta);

        // Act
        Response response = controller.postCuenta(cuenta);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatus());
        Assertions.assertEquals(cuenta, response.getEntity());
    }

    @Test
    public void actualizar_cuenta_deberia_recibir_200() {
        // Arrange
        Cuenta cuentaUpdate = new Cuenta(ID, NUMERO_CUENTA, TIPO_CUENTA, SALDO, ESTADO, cliente);
        Mockito.when(service.findById(ID)).thenReturn(cuenta);

        // Act
        Response response = controller.putCuenta(ID, cuentaUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatus());
        Assertions.assertEquals(cuenta, response.getEntity());
    }

    @Test
    public void actualizar_cuenta_inexistente_deberia_recibir_404() {
        // Arrange
        Cuenta cuentaUpdate = new Cuenta(ID, NUMERO_CUENTA, TIPO_CUENTA, SALDO, false, cliente);
        Mockito.when(service.findById(7L)).thenReturn(null);

        // Act
        Response response = controller.putCuenta(7L, cuentaUpdate);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }

    @Test
    public void eliminar_cuenta_deberia_recibir_204() {
        // Arrange
        Mockito.when(service.findById(ID)).thenReturn(cuenta);

        // Act
        Response response = controller.deleteCuenta(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NO_CONTENT, response.getStatus());
    }

    @Test
    public void eliminar_cuenta_inexistente_deberia_recibir_404() {
        // Arrange
        Mockito.when(service.findById(7L)).thenReturn(cuenta);

        // Act
        Response response = controller.deleteCuenta(ID);

        // Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.getStatus());
    }
}
