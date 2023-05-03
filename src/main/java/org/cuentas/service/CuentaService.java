package org.cuentas.service;

import java.util.List;

import org.cuentas.entity.Cuenta;
import org.cuentas.repository.CuentaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CuentaService {

    @Inject
    CuentaRepository repository;

    public List<Cuenta> findAll() {
        return repository.listAll();
    }

    public Cuenta findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cuenta save(Cuenta cuenta) {
        repository.persist(cuenta);
        return cuenta;
    }

    @Transactional
    public Cuenta update(Long id, Cuenta cuenta) {
        Cuenta upCuenta = repository.findById(id);
        upCuenta.setNumero_cuenta(cuenta.getNumero_cuenta());
        upCuenta.setTipo_cuenta(cuenta.getTipo_cuenta());
        upCuenta.setSaldo(cuenta.getSaldo());
        upCuenta.setEstado(cuenta.isEstado());
        upCuenta.setCliente(cuenta.getCliente());
        repository.persist(upCuenta);
        return upCuenta;
    }

    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
