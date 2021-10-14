package com.example.colsubsidiotestbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.colsubsidiotestbackend.exceptions.CuentaNotFoundException;
import com.example.colsubsidiotestbackend.model.Cliente;
import com.example.colsubsidiotestbackend.model.Cuenta;
import com.example.colsubsidiotestbackend.respositories.ClienteRepository;
import com.example.colsubsidiotestbackend.respositories.CuentaRepository;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.server.Client;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;

    public CuentaService(){
        this.cuentaRepository = new CuentaRepository();
        this.clienteRepository = new ClienteRepository();
    }

    public Cuenta crearCuenta(String documento){
        
        String numeroCuenta = this.generarNumero();
        Cuenta cuenta = new Cuenta(numeroCuenta, documento);
        // return cuentaRepository.save(cuenta);
        return cuenta;
        
    }

    public Cuenta modificarCuenta(Cuenta cuenta){
        Optional<Cuenta> cuentaOptional = this.cuentaRepository.findCuentaByNumber(cuenta.getNumero());
        if(cuentaOptional.isPresent()){
            return cuentaRepository.update(cuenta);
        }else{
            throw new CuentaNotFoundException("Esta cuenta no existe");
        }
    }

    public void eliminarCuenta(String number){
        cuentaRepository.delete(number);
    }

    public Cuenta getCuenta(String number){
        Optional<Cuenta> cuentaOptional = this.cuentaRepository.findCuentaByNumber(number);
        if(cuentaOptional.isPresent()){
            return cuentaOptional.get();
        }else{
            throw new CuentaNotFoundException("Esta cuenta no existe");
        }
    }

    public Cuenta modificarSaldo(String numero, char movimiento, double monto){
        Optional<Cuenta> cuentaOptional = this.cuentaRepository.findCuentaByNumber(numero);
        if(cuentaOptional.isPresent()){
            double nuevoSaldo = cuentaOptional.get().getSaldo();
            Cuenta cuenta = cuentaOptional.get();
            switch (movimiento){
                case 'c': 
                    nuevoSaldo -= monto;
                    if(nuevoSaldo>0){
                        throw new CuentaNotFoundException("No cuenta con fondos suficuentes");
                    }else{
                        cuenta.setSaldo(nuevoSaldo);
                    }
                    break;

                case 'd':
                    nuevoSaldo += monto;
                    cuenta.setSaldo(nuevoSaldo);
                    break;

                default:
                    break;
            }
            return cuentaRepository.update(cuenta);
        }else{
            throw new CuentaNotFoundException("Esta cuenta no existe");
        }
    }

    public List<Cuenta> getCuentas(String documento){
        Cliente cliente = clienteRepository.findClinenteByDocumento(documento);
        return cliente.getCuentas();
    }

    private String generarNumero(){
        StringBuilder  numero = new StringBuilder();
        for(int i=0; i<10; i++){
            numero.append((int)(Math.random()*10));
        }
        if(this.cuentaRepository.contains(numero.toString())){
            return generarNumero();
        }else{
            return numero.toString();
        }
    }
    
}