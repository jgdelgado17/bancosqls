package org.cuentas.service;

import java.util.List;

import org.cuentas.entity.Cliente;
import org.cuentas.repository.ClienteRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository repository;

    public List<Cliente> findAll() {
        return repository.listAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        repository.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Long id, Cliente cliente) {
        Cliente upCliente = repository.findById(id);
        upCliente.setNombre(cliente.getNombre());
        upCliente.setGenero(cliente.getGenero());
        upCliente.setEdad(cliente.getEdad());
        upCliente.setIdentificacion(cliente.getIdentificacion());
        upCliente.setDireccion(cliente.getDireccion());
        upCliente.setTelefono(cliente.getTelefono());
        repository.persist(upCliente);
        return upCliente;
    }

    @Transactional
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

}
