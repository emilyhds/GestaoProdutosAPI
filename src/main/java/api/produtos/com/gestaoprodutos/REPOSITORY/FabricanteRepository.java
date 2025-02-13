package api.produtos.com.gestaoprodutos.REPOSITORY;

import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository <Fabricante, Integer > {
}
