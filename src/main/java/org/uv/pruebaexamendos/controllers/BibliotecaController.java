/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.pruebaexamendos.controllers;

import java.net.URI;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.pruebaexamendos.models.Biblioteca;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.uv.pruebaexamendos.models.BibliotecaDTO;
import org.uv.pruebaexamendos.repository.BibliotecaRepository;

/**
 *
 * @author loken
 */
@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @GetMapping
    public ResponseEntity<Page<Biblioteca>> listBibliotecas(Pageable pageable) {
        return ResponseEntity.ok(bibliotecaRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Biblioteca> saveBiblioteca(@RequestBody BibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setId(bibliotecaDTO.getIdDTO());
        biblioteca.setNombre(bibliotecaDTO.getNombreDTO());
        biblioteca.setLibros(bibliotecaDTO.getLibrosDTO());
        Biblioteca bibliotecaGuardada = bibliotecaRepository.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaGuardada.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> actualizarBiblioteca(@PathVariable Integer id, @RequestBody BibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setId(bibliotecaDTO.getIdDTO());
        biblioteca.setNombre(bibliotecaDTO.getNombreDTO());
        biblioteca.setLibros(bibliotecaDTO.getLibrosDTO());
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);

        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        biblioteca.setId(bibliotecaOptional.get().getId());
        bibliotecaRepository.save(biblioteca);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> obtenerBibliotecaPorId(@PathVariable Integer id) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);

        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(bibliotecaOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable Integer id) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(id);

        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        bibliotecaRepository.delete(bibliotecaOptional.get());
        return ResponseEntity.noContent().build();
    }
}
