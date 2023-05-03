package com.gavag.helpdesk.services;

import com.gavag.helpdesk.domain.Chamado;
import com.gavag.helpdesk.repositories.ChamadoRepository;
import com.gavag.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado com o ID: " + id + " não encontrado!"));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }
}
