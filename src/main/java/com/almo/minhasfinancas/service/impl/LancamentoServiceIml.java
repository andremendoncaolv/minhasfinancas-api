package com.almo.minhasfinancas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.almo.minhasfinancas.enums.StatusLancamento;
import com.almo.minhasfinancas.model.entity.Lancamento;
import com.almo.minhasfinancas.model.repository.LancamentoRepository;
import com.almo.minhasfinancas.service.LancamentoService;
import com.almo.minhasfinancas.service.exception.RegraNegocioException;

import jakarta.transaction.Transactional;

public class LancamentoServiceIml implements LancamentoService{
	
	@Autowired
	private LancamentoRepository repository;

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
	}

	@Override
	@Transactional
	public List<Lancamento> buscar(Lancamento lancamentoFilto) {
		Example example = Example.of(lancamentoFilto, 
				ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida");
		}
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido.");
		}
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() != 4 ) {
			throw new RegraNegocioException("Informe um mês válido.");
		}
		if(lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("informe um Usuário.");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um Valor válido.");
		}
		if( lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um tipo de Lançamento.");
		}
	}

}
