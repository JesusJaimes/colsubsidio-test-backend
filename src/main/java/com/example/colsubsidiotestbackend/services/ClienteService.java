package com.example.colsubsidiotestbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.colsubsidiotestbackend.exceptions.ClienteNotFoundException;
import com.example.colsubsidiotestbackend.model.Cliente;
import com.example.colsubsidiotestbackend.model.Cuenta;
import com.example.colsubsidiotestbackend.respositories.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // @Autowired
    // public ClienteService(ClienteRepository clienteRepository){
    //     this.clienteRepository = clienteRepository;
    // }
    
    public ClienteService(){
        this.clienteRepository = new ClienteRepository();
    }

    public Cliente crearCliente(Cliente cliente){
        
        if(this.clienteRepository.contains(cliente.getDocumento())){
            throw new ClienteNotFoundException("Este usuario ya existe");
        }else{
            return clienteRepository.save(cliente);
        }
    }

    public Cliente modificarCliente(Cliente cliente){
        if(cliente!=null){
            return clienteRepository.update(cliente);
        }else{
            throw new ClienteNotFoundException("Este usuario no existe");
        }
    }

    public void eliminarCliente(String documento){
        clienteRepository.delete(documento);
    }

    public Cliente getCliente(String documento){
        Cliente cliente = this.clienteRepository.findClinenteByDocumento(documento);
        if(cliente!=null){
            return cliente;
        }else{
            throw new ClienteNotFoundException("Este usuario no existe");
        }
    }

    public List<Cliente> getClientes(){
        return clienteRepository.findAll();
    }

    public Cliente modificarCuentaClientes(Cuenta cuenta) {
        Cliente cliente = clienteRepository.findClinenteByDocumento(cuenta.getCliente());
        ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>) cliente.getCuentas();
        for(Cuenta c: cuentas){
            if(c.getNumero().equals(cuenta.getNumero())){
                cuentas.set(cuentas.indexOf(c), cuenta);
                break;
            }
        }
        cliente.setCuentas(cuentas);
        return clienteRepository.update(cliente);
    }
    
}
