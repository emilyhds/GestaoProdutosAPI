package api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Fabricante;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProdutoRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private Float preco;

    @NotNull
    @PositiveOrZero
    private Integer estoque;

    @NotBlank
    private String dataValidade;

    private String descricao;

    @NotBlank
    private String codigoDeBarras;

    @NotNull
    private Float peso;

    @NotBlank
    private String medida;

    @NotNull
    private Fabricante fabricante;

    @NotNull
    private Categoria categoria;

    public Produto converter () {
        return Produto.builder()
                .nome( this.nome )
                .preco( this.preco )
                .estoque( this.estoque )
                .dataValidade( this.dataValidade )
                .descricao( this.descricao )
                .codigoDeBarras( this.codigoDeBarras )
                .peso( this.peso )
                .medida( this.medida )
                .fabricante( this.fabricante )
                .categoria( this.categoria )
                .build();
    }

}
