package com.pedrohnf688.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.pedrohnf688.api.helper.PasswordUtils;
import com.pedrohnf688.api.modelo.Credencial;
import com.pedrohnf688.api.modelo.Equipe;
import com.pedrohnf688.api.modelo.Projeto;
import com.pedrohnf688.api.modelo.Solicitacao;
import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumStatusSolicitacao;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;
import com.pedrohnf688.api.modelo.enums.EnumTipoSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;
import com.pedrohnf688.api.service.impl.CredencialServiceImpl;
import com.pedrohnf688.api.service.impl.EquipeServiceImpl;
import com.pedrohnf688.api.service.impl.ProjetoServiceImpl;
import com.pedrohnf688.api.service.impl.SolicitacaoServiceImpl;
import com.pedrohnf688.api.service.impl.UsuarioServiceImpl;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ProjetoApplication {

	@Autowired
	private CredencialServiceImpl csi;

	@Autowired
	private EquipeServiceImpl esi;

	@Autowired
	private UsuarioServiceImpl usi;

	@Autowired
	private SolicitacaoServiceImpl ssi;
	
	@Autowired
	private ProjetoServiceImpl psi;
	
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
			this.csi.salvar(c1);

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
			this.csi.salvar(c2);

			Equipe e = new Equipe();
			e.setDateCreated(new Date());
			e.setTitulo("teste Equipe");
			e.setQtdMembros(0);
			this.esi.salvar(e);

			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			listaUsuarios.add(u1);
			listaUsuarios.add(u2);

			e.setListaUsuarios(listaUsuarios);
			e.setQtdMembros(listaUsuarios.size());
			u1.setEquipe(e);
			this.usi.salvar(u1);
			u2.setEquipe(e);
			this.usi.salvar(u2);
			this.esi.salvar(e);

			Solicitacao s = new Solicitacao();
			s.setProblema("");
			s.setDateCreated(new Date());
			s.setDescricao("Chat para grupo de estudo");
			s.setStatusSolicitacao(EnumStatusSolicitacao.EM_ANALISE);
			s.setTipoCategoria(EnumTipoCategoria.APLICACAO_MOBILE);
			s.setTitulo("App chat");
			s.setUsuario(u1);
			this.ssi.salvar(s);

			this.ssi.deletarPorId(1L);

			Projeto p = new Projeto();
			p.setDateCreated(new Date());
			p.setInfoContato("390845704");
			p.setQtdDiasPrevista(15);
			p.setDescricao("fkjdifigjrijgeiger");
			p.setTipoCategoria(EnumTipoCategoria.APLICACAO_MOBILE);
			p.setCustoInicial(103.5);
			p.setStatusProjeto(EnumStatusProjeto.ANALISANDO_VIABILIDADE);
			p.setTitulo("ewczzzxxe");
			p.setSolicitacao(s);
			p.setEquipe(e);
			this.psi.salvar(p);

			
			
		};
	}

}
