package api.produtos.com.gestaoprodutos.CONTROLLER;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Fabricante;
import api.produtos.com.gestaoprodutos.SERVICES.FabricanteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fabricante")
public class FabricanteController {

    private FabricanteService service;

    @PostMapping
    public ResponseEntity<Fabricante> criarFabricante (@RequestBody Fabricante fabricante ){

        try {

            fabricante = service.criarFabricante( fabricante );
            return new ResponseEntity<>( fabricante, HttpStatus.OK );

        } catch ( AtributosInvalidosException e ){

            System.err.println( "\nDados inválidos informados" );
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao criar fabricante" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> buscarFabricantePorId ( @PathVariable Integer id ){

        try {

            Fabricante fabricante = service.buscarFabricantePorId( id );
            return new ResponseEntity<>( fabricante, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum fabricante com o id " + id );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao buscar fabricante" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @GetMapping
    public ResponseEntity<List<Fabricante>> listarFabricantes (  ){

        try {

            List<Fabricante> fabricantes = service.listarFabricantes();
            return new ResponseEntity<>( fabricantes, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum fabricante cadastrado" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao listar fabricantes" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Fabricante> atualizarFabricante ( @RequestBody Fabricante fabricante, @PathVariable Integer id  ){

        try {

            fabricante = service.atualizarFabricante( fabricante, id );
            return new ResponseEntity<>( fabricante, HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nO fabricante selecionado não existe" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        }  catch ( AtributosInvalidosException e ) {

            System.err.println("\nDados inválidos informados");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao atualizar fabricante" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFabricante ( @PathVariable Integer id ){

        try {

            service.deletarFabricante( id );
            return new ResponseEntity<>( HttpStatus.OK );

        } catch ( NenhumElementoEncontradoException e ){

            System.err.println( "\nNão há nenhum fabricante cadastrado com esse id" );
            return new ResponseEntity<>( HttpStatus.NO_CONTENT );

        } catch ( Exception e ){

            System.err.println( "\nErro não previsto ao deletar fabricante" );
            e.printStackTrace();
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );

        }

    }

}
