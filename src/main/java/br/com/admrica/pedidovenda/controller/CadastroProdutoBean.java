package br.com.admrica.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.admrica.pedidovenda.model.Categoria;
import br.com.admrica.pedidovenda.model.Produto;
import br.com.admrica.pedidovenda.repository.Categorias;
import br.com.admrica.pedidovenda.service.CadastroProdutoService;
import br.com.admrica.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private Produto produto;

	@NotNull
	private Categoria categoriaPai;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subcategorias;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {
		System.out.println("Inicializando...");
		if (FacesUtil.isNotPostback()) {
			categoriasRaizes = categorias.raizes();
		}
	}

	public void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();

	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();

		FacesUtil.addInfoMessage("Produto salvo com sucesso!");
	}

	public void carregarSubcategorias() {
		subcategorias = categorias.subcategoriasDe(categoriaPai);
	}

	public Produto getProduto() {
		return produto;
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}
}