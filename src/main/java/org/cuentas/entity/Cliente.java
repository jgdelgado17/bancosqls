package org.cuentas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cliente extends Persona {
    @Column(unique = true)
    private String contrasenia;
    private boolean estado;

    // @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // @JoinColumn(foreignKey = @ForeignKey(name = "cuenta_cliente_fk"))
    // @OnDelete(action = OnDeleteAction.RESTRICT)
    // private List<Cuenta> cuentas = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(Long id, String nombre, String genero, int edad, String identificacion, String direccion,
            String telefono, String contrasenia, boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((contrasenia == null) ? 0 : contrasenia.hashCode());
        result = prime * result + (estado ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (contrasenia == null) {
            if (other.contrasenia != null)
                return false;
        } else if (!contrasenia.equals(other.contrasenia))
            return false;
        if (estado != other.estado)
            return false;
        return true;
    }

    

}
