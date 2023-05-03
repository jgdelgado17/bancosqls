package org.cuentas.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.cuentas.entity.Reporte;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ReporteService {
    @PersistenceContext
    EntityManager manager;

    public List<Reporte> generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Long idCliente) {
        String query = "SELECT "
                + "mv.fecha fecha, "
                + "cl.nombre nombre, "
                + "ct.numero_cuenta numero_cuenta, "
                + "ct.tipo_cuenta tipo, "
                + "mv.saldo_inicial saldo_inicial, "
                + "ct.estado estado, "
                + "mv.valor movimiento, "
                + "ct.saldo saldo_disponible, "
                + "mv.tipo_movimiento "
                + "FROM cliente cl "
                + "join cuenta ct "
                + "on cl.id = ct.cliente_id "
                + "join movimiento mv "
                + "on ct.id = mv.cuenta_id "
                + "WHERE cl.id = :idCliente "
                + "AND mv.fecha between :fechaInicio AND :fechaFin";

        List<Object[]> resultados = manager.createNativeQuery(query)
                .setParameter("idCliente", idCliente)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFin)
                .getResultList();

        List<Reporte> reportes = new ArrayList<>();

        for (Object[] resultado : resultados) {
            Reporte reporte = new Reporte();
            float valor_movimiento = tipoMovimiento(resultado);

            reporte.setFecha((Timestamp) resultado[0]);
            reporte.setCliente((String) resultado[1]);
            reporte.setNumero_cuenta((String) resultado[2]);
            reporte.setTipo((String) resultado[3]);
            reporte.setSaldo_inicial((float) resultado[4]);
            reporte.setEstado((boolean) resultado[5]);
            reporte.setMovimiento(valor_movimiento);
            reporte.setSaldo_disponible((float) resultado[7]);
            reportes.add(reporte);
        }
        return reportes;
    }

    private float tipoMovimiento(Object[] resultado) {
        String tipo_movimiento = (String) resultado[8];
        float valor_movimiento = (float) resultado[6];
        if (tipo_movimiento.equalsIgnoreCase("Retiro"))
            valor_movimiento = valor_movimiento * -1;
        return valor_movimiento;
    }
}
