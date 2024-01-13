package br.com.everymind.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.everymind.dto.ProdutosDTO;
import br.com.everymind.service.ProdutosService;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutosController {

	private ProdutosService produtosService;

	@Autowired
	public ProdutosController(ProdutosService produtosService) {

		this.produtosService = produtosService;
	}

	// @GetMapping(value = "/", produces = "application/json")
	@GetMapping
	public List<ProdutosDTO> findAll() {

		return produtosService.findAll();

	}

	@GetMapping("/{id}")
	public ProdutosDTO findByID(@PathVariable Long id) {

		return produtosService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutosDTO created(@Valid @RequestBody ProdutosDTO produtosDTO) {

		return produtosService.create(produtosDTO);
	}

	@PutMapping("/{id}")
	public ProdutosDTO update(@PathVariable Long id, @Valid @RequestBody ProdutosDTO produtosDTO) {

		return produtosService.update(id, produtosDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		produtosService.delete(id);
	}

}
