package org.cuentas.entity;

import java.sql.Timestamp;

public class Reporte {
    private Timestamp fecha;
    private String cliente;
    private String numero_cuenta;
    private String tipo;
    private float saldo_inicial;
    private boolean estado;
    private float movimiento;
    private float saldo_disponible;

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSaldo_inicial() {
        return saldo_inicial;
    }

    public void setSaldo_inicial(float saldo_inicial) {
        this.saldo_inicial = saldo_inicial;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public float getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(float movimiento) {
        this.movimiento = movimiento;
    }

    public float getSaldo_disponible() {
        return saldo_disponible;
    }

    public void setSaldo_disponible(float saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }

}
