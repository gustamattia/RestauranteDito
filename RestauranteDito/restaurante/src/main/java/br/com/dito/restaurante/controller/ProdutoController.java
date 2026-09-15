package br.com.dito.restaurante.controller;

import br.com.dito.restaurante.DTO.ProdutoDTO;
import br.com.dito.restaurante.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return produtoService.listarTodos();
    }

    @PostMapping
    public ProdutoDTO criarProduto(@RequestBody ProdutoDTO dto) {
        return produtoService.adicionarProduto(dto);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        return produtoService.atualizarProduto(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    @GetMapping("/nome")
    public List<ProdutoDTO> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarProdutosPorNome(nome);
    }

    @GetMapping("/precos")
    public List<ProdutoDTO> buscarEntrePrecos(@RequestParam Double min, @RequestParam Double max) {
        return produtoService.buscarProdutosEntrePrecos(min, max);
    }
}