package br.com.everymind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.everymind.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

}
