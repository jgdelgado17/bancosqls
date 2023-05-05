package org.cuentas.entity;

import org.hibernate.annotations.Check;
import org.wildfly.common.annotation.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero_cuenta;

    @Check(constraints = "tipo_cuenta = 'Ahorro' OR tipo_cuenta = 'Corriente'")
    private String tipo_cuenta;
    private float saldo;
    private boolean estado;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "cuenta_cliente_fk"))
    private Cliente cliente;

    // @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Movimiento> movimientos = new ArrayList<>();

    public Cuenta() {
    }

    public Cuenta(Long id, String numero_cuenta, String tipo_cuenta, float saldo, boolean estado, Cliente cliente) {
        this.id = id;
        this.numero_cuenta = numero_cuenta;
        this.tipo_cuenta = tipo_cuenta;
        this.saldo = saldo;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((numero_cuenta == null) ? 0 : numero_cuenta.hashCode());
        result = prime * result + ((tipo_cuenta == null) ? 0 : tipo_cuenta.hashCode());
        result = prime * result + Float.floatToIntBits(saldo);
        result = prime * result + (estado ? 1231 : 1237);
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cuenta other = (Cuenta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (numero_cuenta == null) {
            if (other.numero_cuenta != null)
                return false;
        } else if (!numero_cuenta.equals(other.numero_cuenta))
            return false;
        if (tipo_cuenta == null) {
            if (other.tipo_cuenta != null)
                return false;
        } else if (!tipo_cuenta.equals(other.tipo_cuenta))
            return false;
        if (Float.floatToIntBits(saldo) != Float.floatToIntBits(other.saldo))
            return false;
        if (estado != other.estado)
            return false;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        return true;
    }

    

}
