package com.example.colsubsidiotestbackend.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.colsubsidiotestbackend.model.Cliente;
import com.example.colsubsidiotestbackend.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("colsubsidio/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController (ClienteService clienteService){
        this.clienteService = clienteService;
    }
    
    @PostMapping("/add")
    public  ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        if(cliente.getCuentas()==null){
            cliente.setCuentas(new ArrayList<>());
        }
        Cliente newCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> modificarCliente(@RequestBody Cliente cliente) {
        Cliente updateCliente = clienteService.modificarCliente(cliente);
        return new ResponseEntity<>(updateCliente, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{documento}")
    public ResponseEntity<?> deleteCliente(@PathVariable("documento") String documento) {
        clienteService.eliminarCliente(documento);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{documento}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("documento") String documento){
        Cliente cliente = clienteService.getCliente(documento);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getClientes () {
        List<Cliente> clientes = clienteService.getClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }
}
