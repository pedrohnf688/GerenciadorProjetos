package com.pedrohnf688.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.pedrohnf688.api.helper.PasswordUtils;
import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.repository.CredencialRepository;
import com.pedrohnf688.api.service.impl.CredencialServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ProjetoApplication {

	@Autowired
	private CredencialRepository cr;

	@Autowired
	private CredencialServiceImpl csi;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			Usuario u1 = new Usuario();
			u1.setDataNascimento(new Date());
			u1.setDescricao("Sou formado em análise e desenvolvedor  de sistemas");
			u1.setNome("Pedro Henrique");
			u1.setCargo("Programador");
			u1.setTipoSexo(EnumTipoSexo.MASCULINO);
			u1.setTipoUsuario(EnumTipoUsuario.ROLE_ADMINISTRADOR);

			Credencial c1 = new Credencial();
			c1.setEmail("pedrohnf2014@email.com");
			c1.setSenha(PasswordUtils.gerarBCrypt("12345"));
			c1.setUsername("pedrohnf688");
			c1.setUsuario(u1);
			this.cr.save(c1);
						
			Usuario u2 = new Usuario();
			u2.setDataNascimento(new Date());
			u2.setDescricao("kkkk");
			u2.setNome("Pedro");
			u2.setCargo("Programador II");
			u2.setTipoSexo(EnumTipoSexo.MASCULINO);
			u2.setTipoUsuario(EnumTipoUsuario.ROLE_DESENVOLVEDOR);

			Credencial c2 = new Credencial();
			c2.setEmail("pedrohnf@email.com");
			c2.setSenha(PasswordUtils.gerarBCrypt("12345"));
			c2.setUsername("pedrohnf");
			c2.setUsuario(u2);
			this.cr.save(c2);

			

		};
	}

}
