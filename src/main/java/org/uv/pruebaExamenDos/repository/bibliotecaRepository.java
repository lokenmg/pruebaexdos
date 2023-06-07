/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.pruebaExamenDos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.pruebaExamenDos.models.Biblioteca;

/**
 *
 * @author loken
 */
public interface bibliotecaRepository extends JpaRepository<Biblioteca, Integer> {
    
}
