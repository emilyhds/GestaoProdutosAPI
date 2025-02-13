package api.produtos.com.gestaoprodutos.CONTROLLER;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.DTO.REQUEST.CategoriaRequestDTO;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Categoria;
import api.produtos.com.gestaoprodutos.SERVICES.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping ("/categoria")
public class CategoriaController {

    private CategoriaService service;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria (@RequestBody @Valid CategoriaRequestDTO categoria ){

        try {

            categoria = service.criarCategoria( categoria );
            return new ResponseEntity<>( categoria, HttpStatus.OK );

        } catch ( AtributosInvalidosException e ){

            System.err.println( "\nDados inválidos informados" );
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao criar categoria" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId ( @PathVariable Integer id ){

        try {

            Categoria categoria = service.buscarCategoriaPorId( id );
            return new ResponseEntity<>( categoria, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhuma categoria com o id " + id );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao buscar categoria" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping
    public ResponseEntity< List <Categoria> > listarCategorias (  ){

        try {

            List<Categoria> categorias = service.listarCategorias();
            return new ResponseEntity<>( categorias, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhuma categoria cadastrada" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao listar categorias" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria ( @RequestBody Categoria categoria, @PathVariable Integer id  ){

        try {

            categoria = service.atualizarCategoria( categoria, id );
            return new ResponseEntity<>( categoria, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nA categoria selecionada não existe" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        }  catch ( AtributosInvalidosException e ) {

            System.err.println("\nDados inválidos informados");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao atualizar categoria" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria ( @PathVariable Integer id ){

        try {

            service.deletarCategoria( id );
            return new ResponseEntity<>( HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhuma categoria cadastrada com esse id" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao deletar categoria" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }
}
