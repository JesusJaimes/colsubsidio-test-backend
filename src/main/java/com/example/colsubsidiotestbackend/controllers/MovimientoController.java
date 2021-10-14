package com.example.colsubsidiotestbackend.controllers;

import java.util.List;

import com.example.colsubsidiotestbackend.model.Cuenta;
import com.example.colsubsidiotestbackend.model.Movimiento;
import com.example.colsubsidiotestbackend.services.ClienteService;
import com.example.colsubsidiotestbackend.services.CuentaService;
import com.example.colsubsidiotestbackend.services.MovimientoService;

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
@RequestMapping("colsubsidio/api/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;
    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    @Autowired
    public MovimientoController (MovimientoService movimientoService, CuentaService cuentaService, ClienteService clienteService){
        this.movimientoService = movimientoService;
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }

    @PostMapping("/add/{numero}")
    public  ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento, @PathVariable("numero") String numero){
        Movimiento newMovimiento = movimientoService.crearMovimiento(movimiento);
        Cuenta cuenta = cuentaService.getCuenta(numero);
        newMovimiento.setCuenta(cuenta);
        cuenta.getMovimientos().add(newMovimiento);
        cuentaService.modificarCuenta(cuenta);
        cuentaService.modificarSaldo(cuenta.getNumero(), newMovimiento.getTipo(), newMovimiento.getValor());
        clienteService.modificarCuentaClientes(cuenta);
        return new ResponseEntity<>(newMovimiento, HttpStatus.CREATED);
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

    @GetMapping("/find/{user}")
    public ResponseEntity<Cuenta> getCuenta(@PathVariable("user") String user){
        Cuenta cuenta = cuentaService.getCuenta(user);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    // @GetMapping("/all")
    // public ResponseEntity<List<Cuenta>> getCuentas () {
    //     List<Cuenta> cuentas = cuentaService.getCuentas();
    //     return new ResponseEntity<>(cuentas, HttpStatus.OK);
    // }
    
}
