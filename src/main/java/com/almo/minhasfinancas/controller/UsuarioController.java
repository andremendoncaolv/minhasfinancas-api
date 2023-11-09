package com.almo.minhasfinancas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almo.minhasfinancas.dto.UsuarioDTO;
import com.almo.minhasfinancas.model.entity.Usuario;
import com.almo.minhasfinancas.service.UsuarioService;
import com.almo.minhasfinancas.service.exception.ErroAutenticacao;
import com.almo.minhasfinancas.service.exception.RegraNegocioException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto){
		Usuario usuario = new Usuario(dto.getNome(), dto.getEmail(), dto.getSenha());
		
		try {
			Usuario usuarioSalvo = service.salvarUsuario(usuario);
			return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
