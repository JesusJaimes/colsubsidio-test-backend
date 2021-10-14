package com.example.colsubsidiotestbackend.respositories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import com.example.colsubsidiotestbackend.model.Movimiento;

public class MovimientoRepository {

    public Hashtable<Long, Movimiento> tablaMovimiento;

    public MovimientoRepository(){
        this.tablaMovimiento = new Hashtable<>();
    }

    public Movimiento save(Movimiento movimiento) {
        return this.tablaMovimiento.put(movimiento.getId(), movimiento);
    }

    public Movimiento update(Movimiento movimiento) {
        return this.tablaMovimiento.replace(movimiento.getId(), movimiento);
    }

    public void delete(long id){
        this.tablaMovimiento.remove(id);
    }

    public Optional<Movimiento> findMovimientoById(long id){
        Optional<Movimiento> movimiento = Optional.empty();
        if(this.tablaMovimiento.containsKey(id)){
            movimiento = Optional.of(this.tablaMovimiento.get(id));
        }
        return movimiento;
    }

    public List<Movimiento> findAll(){
        return new ArrayList<>(tablaMovimiento.values());
    }
    
}