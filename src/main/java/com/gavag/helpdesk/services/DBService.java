package com.gavag.helpdesk.services;

import com.gavag.helpdesk.domain.Chamado;
import com.gavag.helpdesk.domain.Cliente;
import com.gavag.helpdesk.domain.Tecnico;
import com.gavag.helpdesk.domain.enums.Perfil;
import com.gavag.helpdesk.domain.enums.Prioridade;
import com.gavag.helpdesk.domain.enums.Status;
import com.gavag.helpdesk.repositories.ChamadoRepository;
import com.gavag.helpdesk.repositories.ClienteRepository;
import com.gavag.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Matheus Gava", "79699897058", "matheus.gava@mail.com", encoder.encode("123"));
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "08891423009", "linus.torvalds@mail.com", encoder.encode("123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
