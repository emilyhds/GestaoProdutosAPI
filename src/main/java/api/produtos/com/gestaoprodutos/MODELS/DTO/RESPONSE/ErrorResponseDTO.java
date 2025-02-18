package api.produtos.com.gestaoprodutos.MODELS.DTO.RESPONSE;

import java.time.Instant;

public record ErrorResponseDTO(String mensagem, Instant instant) {
}
