package com.almo.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almo.minhasfinancas.model.entity.Usuario;
import com.almo.minhasfinancas.model.repository.UsuarioRepository;
import com.almo.minhasfinancas.service.UsuarioService;
import com.almo.minhasfinancas.service.exception.ErroAutenticacao;
import com.almo.minhasfinancas.service.exception.RegraNegocioException;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("O usuário não encontrado para o email informado");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean exite = repository.existsByEmail(email);
		if(exite) {
			throw new RegraNegocioException("Já exite usuário cadastrado com este email!");
		}
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
