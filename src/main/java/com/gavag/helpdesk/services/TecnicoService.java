package com.gavag.helpdesk.services;

import com.gavag.helpdesk.domain.Tecnico;
import com.gavag.helpdesk.repositories.TecnicoRepository;
import com.gavag.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico com o id: " + id + " não encontrado!"));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }
}