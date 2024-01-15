package br.com.everymind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.everymind.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

	  @Query("select p from Produtos p where p.nomeProduto like %?1%")
	  List<Produtos> findUserByNome(String nome);
}
