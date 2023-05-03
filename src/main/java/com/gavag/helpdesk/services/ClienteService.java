package com.gavag.helpdesk.services;

import com.gavag.helpdesk.domain.Pessoa;
import com.gavag.helpdesk.domain.Cliente;
import com.gavag.helpdesk.domain.dtos.ClienteDTO;
import com.gavag.helpdesk.repositories.PessoaRepository;
import com.gavag.helpdesk.repositories.ClienteRepository;
import com.gavag.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.gavag.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente com o ID: " + id + " não encontrado!"));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return repository.save(newObj);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente objToDelete = findById(id);
        if (objToDelete.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("O cliente com id " + id + " possui chamados e não pode ser deletado!");
        }
        repository.delete(objToDelete);
    }
}
