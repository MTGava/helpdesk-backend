package com.gavag.helpdesk;

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
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TecnicoRepository tecnicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Matheus Gava", "79699897058", "matheus.gava@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "08891423009", "linus.torvalds@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
