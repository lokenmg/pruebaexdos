/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.pruebaexamendos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.pruebaexamendos.models.Libro;

/**
 *
 * @author loken
 */
public interface LibrosRepository extends JpaRepository<Libro, Integer>{
    
}
