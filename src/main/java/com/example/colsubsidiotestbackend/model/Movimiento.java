package com.example.colsubsidiotestbackend.model;

import java.time.LocalDate;

public class Movimiento {

    private long id;
    private char tipo;
    private double valor;
    private LocalDate fecha;
    private Cuenta cuenta;

    public Movimiento(){

    }

    public Movimiento(char tipo, double valor, LocalDate fecha){
        this.tipo = tipo;
        this.fecha = fecha;
        this.valor = valor;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public char getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
}
