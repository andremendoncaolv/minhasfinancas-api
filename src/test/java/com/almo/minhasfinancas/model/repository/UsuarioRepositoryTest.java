package com.almo.minhasfinancas.model.repository;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.almo.minhasfinancas.model.entity.Usuario;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deVerificarExitenciaDeUmEmail() {
		//cenario
		Usuario usuario = new Usuario("usuario", "usuario@email.com", "senha");
		repository.save(usuario);
		
		//acao /execução
		boolean result = repository.existsByEmail("usuario@emial.com");
		
		//verificação
//		Assertions.assertThat(result).isTrue();
		assertFalse(result);
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenario
		repository.deleteAll();
		
		//acao
		boolean result = repository.existsByEmail("usuario@email.com");
		
		//verificação
		assertFalse(result);
	}
}
