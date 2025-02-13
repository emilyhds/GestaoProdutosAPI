package api.produtos.com.gestaoprodutos.MODELS.ENTITY;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Fabricante;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String nome;

    @NonNull
    private Float preco;

    @NonNull
    private Integer estoque;

    @NonNull
    @Column(nullable = false)
    private String dataValidade;

    @NonNull
    @Column(nullable = false)
    private String descricao;

    @NonNull
    @Column(nullable = false)
    private String codigoDeBarras;

    @NonNull
    private Float peso;

    @NonNull
    @Column(nullable = false)
    private String medida;

    @ManyToOne ( cascade = CascadeType.PERSIST ) //Permite cadastrar um novo fabricante a partir de um produto
    @JoinColumn ( nullable = false )
    private Fabricante fabricante;

    @ManyToOne ( cascade = CascadeType.PERSIST )
    @JoinColumn ( nullable = false )
    private Categoria categoria;

}
