package com.ejemplo.catalogodelibros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> obtenerTodos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obtenerPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico crear(Topico topico) {
        return topicoRepository.save(topico);
    }

    public Topico actualizar(Long id, Topico topico) {
        if (topicoRepository.existsById(id)) {
            topico.setId(id);
            return topicoRepository.save(topico);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}