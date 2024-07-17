package com.ejemplo.catalogodelibros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<Topico> obtenerTodos() {
        return topicoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> obtenerPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obtenerPorId(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topico crear(@Valid @RequestBody Topico topico) {
        return topicoService.crear(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizar(@PathVariable Long id, @Valid @RequestBody Topico topico) {
        Topico actualizado = topicoService.actualizar(id, topico);
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        if (!topicoService.eliminar(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }
    }
}