package api.produtos.com.gestaoprodutos.MODELS.ENTITY;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;
    @NonNull
    @Column(nullable = false, unique = true)
    private String nome;
    private String descricao;

    public Categoria ( Integer id ){
        this.id = id;
    }

}
