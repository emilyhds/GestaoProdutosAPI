package api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaPostRequestDTO(@NotBlank String nome, String descricao ) {

    public Categoria converter() {
        return Categoria.builder()
                .nome( this.nome )
                .descricao( this.descricao )
                .build();
    }
}
