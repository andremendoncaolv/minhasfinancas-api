package com.almo.minhasfinancas.service;

import java.util.List;

import com.almo.minhasfinancas.enums.StatusLancamento;
import com.almo.minhasfinancas.model.entity.Lancamento;

public interface LancamentoService {
	
	Lancamento salvar(Lancamento lancamento);
	Lancamento atualizar(Lancamento lancamento);
	void deletar(Lancamento lancamento);
	List<Lancamento> buscar(Lancamento lancamentoFilto);
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	void validar(Lancamento lancamento);

}
