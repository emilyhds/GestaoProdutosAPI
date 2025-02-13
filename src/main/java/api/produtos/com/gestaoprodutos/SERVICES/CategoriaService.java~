package api.produtos.com.gestaoprodutos.SERVICES;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import api.produtos.com.gestaoprodutos.REPOSITORY.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository repository;

    public Categoria criarCategoria ( Categoria categoria ){

        if (categoria.getNome().isEmpty() || categoria.getNome().isBlank() ){
            throw new AtributosInvalidosException( "O nome da categoria não pode ser vazio! " );
        }

        return repository.save( categoria );

    }

    public Categoria buscarCategoriaPorId ( Integer id ){

        Optional<Categoria> optional = repository.findById( id );

        if ( optional.isPresent() ){
            return optional.get();
        }

        throw new NenhumElementoEncontradoException("Nenhuma categoria foi encontrada com o id informado");
    }

    public List<Categoria> listarCategorias (){

        List<Categoria> categorias = repository.findAll();

        if ( categorias.isEmpty() ){
            throw new NenhumElementoEncontradoException( "Nenhuma categoria foi localizada no banco de dados" );
        }

        return categorias;

    }

    public Categoria atualizarCategoria ( Categoria categoria, Integer id ){

        if ( repository.existsById( id ) ){
            categoria.setId( id );
            return criarCategoria( categoria );
        }

        throw new NenhumElementoEncontradoException("A categoria a ser atualizada não foi localizada");

    }

    public void deletarCategoria ( Integer id ) {

        if ( repository.existsById( id ) ){
            repository.deleteById( id );
        }

        throw new NenhumElementoEncontradoException("A categoria a ser deletada não foi localizada");

    }
}
