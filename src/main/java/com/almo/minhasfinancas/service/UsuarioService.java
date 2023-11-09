package com.almo.minhasfinancas.service;

import com.almo.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarEmail(String email);
}
