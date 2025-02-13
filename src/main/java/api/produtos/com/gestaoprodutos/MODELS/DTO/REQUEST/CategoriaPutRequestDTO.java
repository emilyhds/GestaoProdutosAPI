package api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoriaPutRequestDTO (@NotNull @Positive Integer id, @NotBlank String nome, String descricao ) {
}
