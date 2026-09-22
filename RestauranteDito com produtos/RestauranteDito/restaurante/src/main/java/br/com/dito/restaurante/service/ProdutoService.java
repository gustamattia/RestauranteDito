package br.com.dito.restaurante.service;

import br.com.dito.restaurante.DTO.ProdutoDTO;
import br.com.dito.restaurante.model.Produto;
import br.com.dito.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    // Adicionar novo produto
    public ProdutoDTO adicionarProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    // Atualizar produto
    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    // Buscar produto por id (usado na tela de edição)
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }

    // Deletar produto
    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }

    // Métodos de consulta personalizados que criamos no Repository
    public List<ProdutoDTO> buscarProdutosPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome)
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarProdutosEntrePrecos(Double min, Double max) {
        return produtoRepository.findByPrecoBetween(min, max)
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getPreco()))
                .collect(Collectors.toList());
    }
}