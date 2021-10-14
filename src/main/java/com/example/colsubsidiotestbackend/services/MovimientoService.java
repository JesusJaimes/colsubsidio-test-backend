package com.example.colsubsidiotestbackend.services;

import java.util.List;
import java.util.Optional;

import com.example.colsubsidiotestbackend.exceptions.MovimientoNotFoundException;
import com.example.colsubsidiotestbackend.model.Movimiento;
import com.example.colsubsidiotestbackend.respositories.MovimientoRepository;

import org.springframework.stereotype.Service;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;

    public MovimientoService(){
        this.movimientoRepository = new MovimientoRepository();
    }

    public Movimiento crearMovimiento(Movimiento movimiento){
        long id = this.generarId();
        Optional<Movimiento> movimientoOptional = this.movimientoRepository.findMovimientoById(id);
        if(movimientoOptional.isPresent()){
            return crearMovimiento(movimiento);
        }else{
            movimiento.setId(id);
            return movimientoRepository.save(movimiento);
        }
    }

    public Movimiento modificarMovimiento(Movimiento movimiento){
        Optional<Movimiento> movimientoOptional = this.movimientoRepository.findMovimientoById(movimiento.getId());
        if(movimientoOptional.isPresent()){
            return movimientoRepository.update(movimiento);
        }else{
            throw new MovimientoNotFoundException("Esta movimiento no existe");
        }
    }

    public void eliminarMovimiento(long id){
        movimientoRepository.delete(id);
    }

    public Movimiento getMovimiento(long id){
        Optional<Movimiento> movimientoOptional = this.movimientoRepository.findMovimientoById(id);
        if(movimientoOptional.isPresent()){
            return movimientoOptional.get();
        }else{
            throw new MovimientoNotFoundException("Esta movimiento no existe");
        }
    }

    public List<Movimiento> getMovimientos(){
        return movimientoRepository.findAll();
    }

    private long generarId(){
        StringBuilder  id = new StringBuilder();
        for(int i=0; i<10; i++){
            id.append(Math.random()*10);
        }
        return Long.parseLong(id.toString());
    }
    
}
