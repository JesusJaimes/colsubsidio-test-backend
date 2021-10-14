package com.example.colsubsidiotestbackend.respositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import com.example.colsubsidiotestbackend.model.Cuenta;

public class CuentaRepository {

    public Hashtable<String, Cuenta> tablaCuenta;

    public CuentaRepository(){
        this.tablaCuenta = new Hashtable<>();
    }

    public Cuenta save(Cuenta cuenta) {
        return this.tablaCuenta.put(cuenta.getNumero(), cuenta);
    }

    public Cuenta update(Cuenta cuenta) {
        return this.tablaCuenta.replace(cuenta.getNumero(), cuenta);
    }

    public void delete(String numero){
        this.tablaCuenta.remove(numero);
    }

    public Optional<Cuenta> findCuentaByNumber(String numero){
        Optional<Cuenta> cuenta = Optional.empty();
        if(this.tablaCuenta.containsKey(numero)){
            cuenta = Optional.of(this.tablaCuenta.get(numero));
        }
        return cuenta;
    }

    public List<Cuenta> findAll(){
        return new ArrayList<>(tablaCuenta.values());
    }

    public boolean contains(String numero){
        if(this.tablaCuenta.containsKey(numero)){
            return true;
        }else{
            return false;
        }
    }
    
}
