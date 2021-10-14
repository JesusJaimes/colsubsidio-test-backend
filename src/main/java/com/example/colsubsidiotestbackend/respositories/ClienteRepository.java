package com.example.colsubsidiotestbackend.respositories;

import com.example.colsubsidiotestbackend.model.Cliente;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;


public class ClienteRepository {

    public Hashtable<String, Cliente> tablaClientes;

    public ClienteRepository(){
        this.tablaClientes = new Hashtable<>();
    }

    public Cliente save(Cliente cliente) {
        return this.tablaClientes.put(cliente.getDocumento(), cliente);
    }

    public Cliente update(Cliente cliente) {
        return this.tablaClientes.replace(cliente.getDocumento(), cliente);
    }

    public void delete(String documento){
        this.tablaClientes.remove(documento);
    }

    public Cliente findClinenteByDocumento(String documento){
        Cliente cliente = this.tablaClientes.getOrDefault(documento, new Cliente());
        
        return cliente;
    }

    public List<Cliente> findAll(){
        return new ArrayList<>(tablaClientes.values());
    }

    public boolean contains(String documento){
        if(this.tablaClientes.containsKey(documento)){
            return true;
        }else{
            return false;
        }
    }
    
}
