package br.com.everymind.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.everymind.dto.ProdutosDTO;
import br.com.everymind.model.Produtos;

@Mapper
public interface ProdutosMapper {

	ProdutosMapper INSTANCE = Mappers.getMapper(ProdutosMapper.class);

	Produtos toModel(ProdutosDTO produtosDTO);

	ProdutosDTO toDTO(Produtos produtos);

}
