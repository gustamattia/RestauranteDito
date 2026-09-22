package br.com.dito.restaurante.DTO;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double preco;

    // Construtor vazio, necessário para o Spring instanciar o objeto
    // corretamente ao receber dados de requisições (ex: formulários)
    public ProdutoDTO() {
    }

    // Constructor
    public ProdutoDTO(Long id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
}