package br.com.everymind.exception;

import javax.persistence.EntityNotFoundException;

public class ProdutosNotFoundException extends EntityNotFoundException {

	public ProdutosNotFoundException(Long id) {

		super(String.format("Produto com id %s não existe", id));
	}

}
