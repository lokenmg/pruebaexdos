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
import org.uv.pruebaexamendos.models.Libro;
import org.uv.pruebaexamendos.repository.LibrosRepository;
import org.uv.pruebaexamendos.repository.bibliotecaRepository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.uv.pruebaexamendos.models.Biblioteca;

/**
 *
 * @author loken
 */
@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibrosRepository libroRepository;

    @Autowired
    private bibliotecaRepository bibliotecaRepository;

    @GetMapping
    public ResponseEntity<Page<Libro>> listarLibros(Pageable pageable) {
        return ResponseEntity.ok(libroRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        Libro libroGuardado = libroRepository.save(libro);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libroGuardado.getIsbn()).toUri();

        return ResponseEntity.created(ubicacion).body(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@RequestBody Libro libro, @PathVariable Integer isbn) {
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepository.findById(libro.getBiblioteca().getId());

        if (!bibliotecaOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Libro> libroOptional = libroRepository.findById(isbn);
        if (!libroOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libro.setBiblioteca(bibliotecaOptional.get());
        libro.setIsbn(libroOptional.get().getIsbn());
        libroRepository.save(libro);

        return ResponseEntity.noContent().build();
    }
    
    	@DeleteMapping("/{id}")
	public ResponseEntity<Libro> eliminarLibro(@PathVariable Integer isbn){
		Optional<Libro> libroOptional = libroRepository.findById(isbn);
		
		if(!libroOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		libroRepository.delete(libroOptional.get());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Integer isbn){
		Optional<Libro> libroOptional = libroRepository.findById(isbn);
		
		if(!libroOptional.isPresent()){
			return ResponseEntity.unprocessableEntity().build();
		}
		
		return ResponseEntity.ok(libroOptional.get());
	}
}
