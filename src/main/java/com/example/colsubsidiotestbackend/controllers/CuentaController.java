package com.example.colsubsidiotestbackend.controllers;

import java.util.List;

import com.example.colsubsidiotestbackend.model.Cliente;
import com.example.colsubsidiotestbackend.model.Cuenta;
import com.example.colsubsidiotestbackend.services.ClienteService;
import com.example.colsubsidiotestbackend.services.CuentaService;

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
@RequestMapping("colsubsidio/api/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    @Autowired
    public CuentaController (CuentaService cuentaService , ClienteService clienteService){
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }
    
    @PostMapping("/add/{documento}")
    public  ResponseEntity<Cuenta> createCuenta(@PathVariable("documento") String documento){
        Cliente cliente = clienteService.getCliente(documento);
        Cuenta newCuenta = cuentaService.crearCuenta(documento);
        cliente.getCuentas().add(newCuenta);
        clienteService.modificarCliente(cliente);
        return new ResponseEntity<>(newCuenta, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Cuenta> modificarCuenta(@RequestBody Cuenta cuenta) {
        Cuenta updateCuenta = cuentaService.modificarCuenta(cuenta);
        return new ResponseEntity<>(updateCuenta, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{number}")
    public ResponseEntity<?> deleteCuenta(@PathVariable("number") String number) {
        cuentaService.eliminarCuenta(number);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{documento}")
    public ResponseEntity<List<Cuenta>> getCuentas(@PathVariable("documento") String documento){
        List<Cuenta> cuentas = cuentaService.getCuentas(documento);
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }
    
}
