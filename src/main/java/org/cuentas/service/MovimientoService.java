package org.cuentas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.cuentas.entity.Cuenta;
import org.cuentas.entity.Movimiento;
import org.cuentas.repository.MovimientoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MovimientoService {

    @Inject
    MovimientoRepository repository;

    @Inject
    CuentaService cuentaService;

    public List<Movimiento> findAll() {
        return repository.listAll();
    }

    public Movimiento findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Movimiento save(Movimiento movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        
        actualizar_cuenta(movimiento);

        repository.persist(movimiento);
        return movimiento;
    }

    @Transactional
    public Movimiento update(Long id, Movimiento movimiento) {

        actualizar_cuenta(movimiento);

        Movimiento upMovimiento = repository.findById(id);
        upMovimiento.setFecha(movimiento.getFecha());
        upMovimiento.setTipo_movimiento(movimiento.getTipo_movimiento());
        upMovimiento.setValor(movimiento.getValor());
        upMovimiento.setSaldo_inicial(movimiento.getSaldo_inicial());
        upMovimiento.setCuenta(movimiento.getCuenta());
        repository.persist(upMovimiento);
        return upMovimiento;
    }

    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    private void actualizar_cuenta(Movimiento movimiento) {
        Long id_cuenta = movimiento.getCuenta().getId();
        Cuenta cuenta_asociada = cuentaService.findById(id_cuenta);

        if (cuenta_asociada != null) {
            float saldo_inicial = cuenta_asociada.getSaldo();
            String tipo_movimiento = movimiento.getTipo_movimiento();

            movimiento.setSaldo_inicial(saldo_inicial);

            switch (tipo_movimiento) {
                case "Retiro":
                    if (saldo_disponible(movimiento, saldo_inicial))
                        cuenta_asociada.setSaldo(saldo_inicial - movimiento.getValor());
                    else
                        throw new IllegalArgumentException(
                                "ERROR: Saldo no disponible. Detail: Tiene un saldo de " + saldo_inicial
                                        + ", insuficiente para realizar el retiro");
                    break;
                case "Deposito":
                    cuenta_asociada.setSaldo(saldo_inicial + movimiento.getValor());
                    break;
            }
            cuentaService.update(id_cuenta, cuenta_asociada);
        }
    }

    private boolean saldo_disponible(Movimiento movimiento, float saldo_inicial) {
        if (saldo_inicial == 0.0 || (saldo_inicial - movimiento.getValor()) < 0.0)
            return false;
        return true;
    }
}
