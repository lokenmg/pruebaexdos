/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.pruebaExamenDos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.pruebaExamenDos.models.Libro;

/**
 *
 * @author loken
 */
public interface LibrosRepository extends JpaRepository<Libro, Integer>{
    
}
