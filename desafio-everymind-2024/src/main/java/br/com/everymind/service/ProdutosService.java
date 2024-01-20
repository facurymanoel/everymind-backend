package br.com.everymind.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.everymind.dto.ProdutosDTO;
import br.com.everymind.exception.ProdutosNotFoundException;
import br.com.everymind.mapper.ProdutosMapper;
import br.com.everymind.model.Produtos;
import br.com.everymind.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	private static final ProdutosMapper produtosMapper = ProdutosMapper.INSTANCE;
	
	private ProdutosRepository produtosRepository;
	
	@Autowired
	public ProdutosService(ProdutosRepository produtosRepository) {
		 
		 this.produtosRepository = produtosRepository;
	}
	
	public List<ProdutosDTO> findAll(){
		
		return produtosRepository.findAll()
				                 .stream()
				                 .map(produtosMapper::toDTO)
				                 .collect(Collectors.toList());
	}
	
	//Método com Paginação
	public Page<ProdutosDTO> findAllPagina(int pagina) {
		
		PageRequest page = PageRequest.of(pagina, 5, Sort.by("nome"));
		Page<Produtos> list = produtosRepository.findAll(page);
		Page<ProdutosDTO> dto = list.map(p -> new ModelMapper().map(p, ProdutosDTO.class));
		return dto;
	}
	
	 public ProdutosDTO findById(Long id) {
		
		   return produtosRepository.findById(id)
				                   .map((p) -> produtosMapper.toDTO(p))
				                    .orElseThrow(() -> new ProdutosNotFoundException(id));
	 }
	 
	 @Transactional
	 public ProdutosDTO create(ProdutosDTO produtosDTO) {
		 
		   Produtos produtosToCreate = produtosMapper.toModel(produtosDTO);
		   Produtos createProdutos = produtosRepository.save(produtosToCreate);
		   return produtosMapper.toDTO(createProdutos);
		 
	 }
	 
	 @Transactional
     public ProdutosDTO update(Long id, ProdutosDTO produtosDTO) {
    	 
    	    verifyExists(id);
    	    Produtos produto = new Produtos();
    	    produto.setCodigoProduto(id);
    	    produtosDTO.setCodigoProduto(produto.getCodigoProduto());
    	    Produtos produtosToCreate = produtosMapper.toModel(produtosDTO);
    	    Produtos createProdutos = produtosRepository.save(produtosToCreate);
    	    return produtosMapper.toDTO(createProdutos);
     }
	 
	 @Transactional
	 public void delete(Long id) {
		    
		 verifyExists(id);
		 produtosRepository.deleteById(id);
	 }

	private void verifyExists(Long id) {
		  
		  produtosRepository.findById(id)
		                    .orElseThrow(() -> new ProdutosNotFoundException(id));
		
	}
}
