package api.produtos.com.gestaoprodutos.CONTROLLER;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.DadosDuplicadosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST.ProdutoRequestDTO;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Produto;
import api.produtos.com.gestaoprodutos.SERVICES.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> criarProduto (@RequestBody @Valid ProdutoRequestDTO produtoDTO ){

        try {

            Produto produto = service.criarProduto( produtoDTO );
            return new ResponseEntity<>( produto, HttpStatus.OK );

        } catch ( AtributosInvalidosException e ){

            System.err.println( "\nDados inválidos informados" );
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );

        } catch ( DadosDuplicadosException e ){

            System.err.println( "\nJá existe um produto com esse nome" );
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao criar produto" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId ( @PathVariable Integer id ){

        try {

            Produto produto = service.buscarProdutoPorId( id );
            return new ResponseEntity<>( produto, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum produto com o id " + id );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao buscar produto" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<Page<Produto>> buscarProdutosPorCategoria ( @PageableDefault ( page = 0, size = 15, sort = "nome", direction = Sort.Direction.ASC ) Pageable pageable, @PathVariable Integer id ){

        try {
            Page<Produto> produtos = service.buscarProdutosPorCategoria( pageable, id );
            return new ResponseEntity<>( produtos, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum produto com essa categoria" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao buscar produtos por categoria" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listarProdutos ( @PageableDefault ( page = 0, size = 20, sort = "nome", direction = Sort.Direction.DESC ) Pageable pageable ){

        try {

            Page<Produto> produtos = service.listarProdutos( pageable );
            return new ResponseEntity<>( produtos, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum produto cadastrado" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao listar produtos" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto ( @RequestBody Produto produto, @PathVariable Integer id  ){

        try {

            produto = service.atualizarProduto( produto, id );
            return new ResponseEntity<>( produto, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nO produto selecionado não existe" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        }  catch ( AtributosInvalidosException e ) {

            System.err.println("\nDados inválidos informados");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao atualizar produto" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto ( @PathVariable Integer id ){

        try {

            service.deletarProduto( id );
            return new ResponseEntity<>( HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhumo produto cadastrado com esse id" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao deletar produto" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }
}
