package api.produtos.com.gestaoprodutos.REPOSITORY;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer > {
}
