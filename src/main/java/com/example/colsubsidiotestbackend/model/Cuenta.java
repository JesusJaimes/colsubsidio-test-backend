package com.example.colsubsidiotestbackend.model;

import java.util.ArrayList;

public class Cuenta {

    private String numero;
    private double saldo;
    private String cliente;
    private ArrayList<Movimiento> movimientos;

    public Cuenta(String numero, String cliente){
        this.movimientos = new ArrayList<>();
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = 0;
    }

    public String getCliente() {
        return cliente;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return this.movimientos;
    }

    public String getNumero() {
        return this.numero;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
}
