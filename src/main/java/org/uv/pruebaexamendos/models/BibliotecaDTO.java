/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.pruebaexamendos.models;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author loken
 */
public class BibliotecaDTO {
    
    private int idDTO;
    private String nombreDTO;
    private Set<Libro> librosDTO = new HashSet<>();

    public int getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(int idDTO) {
        this.idDTO = idDTO;
    }

    public String getNombreDTO() {
        return nombreDTO;
    }

    public void setNombreDTO(String nombreDTO) {
        this.nombreDTO = nombreDTO;
    }

    public Set<Libro> getLibrosDTO() {
        return librosDTO;
    }

    public void setLibrosDTO(Set<Libro> librosDTO) {
        this.librosDTO = librosDTO;
    }
    
}
