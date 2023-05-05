package org.cuentas.clientes;

import static io.restassured.RestAssured.given;

import org.cuentas.entity.Cliente;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ClienteMethodToBDTest {

    private static final Long ID = 10L;
    private static final String NOMBRE = "Jesus Gabriel";
    private static final String GENERO = "m";
    private static final int EDAD = 34;
    private static final String IDENTIFICACION = "2222222222";
    private static final String DIRECCION = "Direcion falsa";
    private static final String TELEFONO = "666666";
    private static final String CONTRASENIA = "00000000";
    private static final boolean ESTADO = true;

    @Test
    public void insertar_cliente_deberia_recibir_201() {
        Cliente cliente = new Cliente(ID, NOMBRE, GENERO, EDAD, IDENTIFICACION, DIRECCION, TELEFONO, CONTRASENIA,
                ESTADO);
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        "{"
                                + "    \"nombre\": \"Gabriel Osorio\","
                                + "    \"genero\": \"m\","
                                + "    \"edad\": 25,"
                                + "    \"identificacion\": \"9\","
                                + "    \"direccion\": \"13 de junio y Equinoccial\","
                                + "    \"telefono\": \"098874587\","
                                + "    \"contrasenia\": \"124500\","
                                + "    \"estado\": true"
                                + "}")
                .when().post("/clientes")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void listar_cliente_deberia_recibir_200() {
        given()
                .when().get("/clientes/2")
                .then()
                .statusCode(200)
                .log().body();
    }

    
}
