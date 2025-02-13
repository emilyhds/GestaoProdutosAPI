package api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO( @NotBlank String nome, String descricao ) {
}
