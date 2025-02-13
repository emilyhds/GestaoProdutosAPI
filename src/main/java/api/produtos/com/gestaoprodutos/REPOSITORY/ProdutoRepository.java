package api.produtos.com.gestaoprodutos.REPOSITORY;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer > {

    public boolean existsByNome ( String nome );
    public boolean existsByCodigoDeBarras ( String codigoDeBarras );

}
