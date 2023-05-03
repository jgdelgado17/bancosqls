package org.cuentas.repository;

import org.cuentas.entity.Cuenta;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CuentaRepository extends PanacheRepository<Cuenta> {

}
