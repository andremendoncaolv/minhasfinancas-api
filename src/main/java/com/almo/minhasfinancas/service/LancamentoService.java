package com.almo.minhasfinancas.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.almo.minhasfinancas.enums.StatusLancamento;
import com.almo.minhasfinancas.model.entity.Lancamento;

public interface LancamentoService {
	
	Lancamento salvar(Lancamento lancamento);
	Lancamento atualizar(Lancamento lancamento);
	void deletar(Lancamento lancamento);
	List<Lancamento> buscar(Lancamento lancamentoFilto);
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	void validar(Lancamento lancamento);
	Optional<Lancamento> obterPorId(Long id);
	BigDecimal obterSaldoPorIdUsuario(Long id);
}
