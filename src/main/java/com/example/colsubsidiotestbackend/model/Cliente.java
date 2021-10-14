package com.example.colsubsidiotestbackend.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    
    private String nombre;
    private String documento;
    private String direccion;
    private String telefono;
    private ArrayList<Cuenta> cuentas;

    public Cliente(){}

    public Cliente (String nombre, String direccion, String telefono, String documento){
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.cuentas = new ArrayList<>();
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getDocumento() {
        return documento;
    }
    
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = (ArrayList<Cuenta>) cuentas;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
