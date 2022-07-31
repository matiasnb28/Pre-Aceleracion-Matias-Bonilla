package com.preaceleracion.alkemy.controllers;

import com.preaceleracion.alkemy.dto.PeliculaBasicDTO;
import com.preaceleracion.alkemy.dto.PeliculaDTO;
import com.preaceleracion.alkemy.servicies.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class PeliculaController {


    private PeliculaService peliculaService;

    public PeliculaController(@Autowired @Lazy PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO movie){

        PeliculaDTO savedMovie= peliculaService.save(movie);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO>put(@PathVariable Long id, @RequestBody PeliculaDTO edit){
        PeliculaDTO putMovie = peliculaService.putMovie(id,edit);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(putMovie);
    }

    //Get para traer una LISTA de imagen, titulo y fecha de creacion
    @GetMapping("/all")
    public  ResponseEntity<List<PeliculaBasicDTO>> getAllBasic(){
        List<PeliculaBasicDTO> basicMovies = peliculaService.getAllBasics();
        return ResponseEntity.status(HttpStatus.OK).body(basicMovies);
    }

    // Get para todos los atributos y con Filtros

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String dateCreation,
            @RequestParam(required = false) Set<Long> gender,
            @RequestParam(required = false, defaultValue = "ASC") String order)
    {
        List<PeliculaDTO> moviesListDto = this.peliculaService.getByFilters(title, image, dateCreation,gender,order);

        return ResponseEntity.status(HttpStatus.OK).body(moviesListDto);

    }




}
