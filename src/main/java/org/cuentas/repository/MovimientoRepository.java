package org.cuentas.repository;

import org.cuentas.entity.Movimiento;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface MovimientoRepository extends PanacheRepository<Movimiento> {

}
