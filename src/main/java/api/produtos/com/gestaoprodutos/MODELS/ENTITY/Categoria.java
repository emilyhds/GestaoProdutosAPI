package api.produtos.com.gestaoprodutos.MODELS.ENTITY;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;
    @NonNull
    @Column(nullable = false)
    private String nome;
    private String descricao;

    public Categoria ( Integer id ){
        this.id = id;
    }

}
