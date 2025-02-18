package api.produtos.com.gestaoprodutos.SERVICES;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.DadosDuplicadosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST.ProdutoRequestDTO;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Produto;
import api.produtos.com.gestaoprodutos.REPOSITORY.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoRepository repository;
    private CategoriaService categoriaService;

    public Produto criarProduto ( ProdutoRequestDTO produtoDTO ){

        if ( repository.existsByNome( produtoDTO.getNome() ) ){
            throw new DadosDuplicadosException("Já existe um produto com esse nome");
        } else if ( repository.existsByCodigoDeBarras( produtoDTO.getCodigoDeBarras() ) ){
            throw new DadosDuplicadosException("Já existe um produto com esse codigo de barras");
        }

        Produto produto = produtoDTO.converter();

        return repository.save( produto );

    }

    public Produto buscarProdutoPorId ( Integer id ){

        Optional<Produto> optional = repository.findById( id );

        if ( optional.isPresent() ){
            return optional.get();
        }

        throw new NenhumElementoEncontradoException("Nenhum produto foi encontrado com o id informado");
    }

    public Page<Produto> listarProdutos (Pageable pageable){
        
        return repository.findAll(pageable);

    }

    public Produto atualizarProduto ( Produto produto, Integer id ){

        if ( repository.existsById( id ) ){
            produto.setId( id );

            if (produto.getNome() == null || produto.getNome().isBlank() || produto.getPreco() <= 0 || produto.getEstoque() < 0 || produto.getPeso() <= 0){

                throw new AtributosInvalidosException( "Os atributos do produto não atendem os requisitos! " );

            }

            return repository.save( produto );
        }

        throw new NenhumElementoEncontradoException("O produto a ser atualizado não foi localizado");

    }

    public void deletarProduto ( Integer id ) {

        if ( repository.existsById( id ) ){
            repository.deleteById( id );
            return;
        }

        throw new NenhumElementoEncontradoException("O produto a ser deletado não foi localizado");

    }

    public Page<Produto> buscarProdutosPorCategoria(Pageable pageable, Integer id) {
        Categoria categoria = categoriaService.buscarCategoriaPorId( id );
        return repository.findByCategoria( pageable, categoria );
    }
}
