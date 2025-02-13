package api.produtos.com.gestaoprodutos.SERVICES;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.ENTITY.Fabricante;
import api.produtos.com.gestaoprodutos.REPOSITORY.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FabricanteService {

    private FabricanteRepository repository;

    public Fabricante criarFabricante (Fabricante fabricante ){

        if (fabricante.getNome().isEmpty() || fabricante.getNome().isBlank() ){
            throw new AtributosInvalidosException( "O nome do fabricante não pode ser vazio! " );
        }

        return repository.save( fabricante );

    }

    public Fabricante buscarFabricantePorId ( Integer id ){

        Optional<Fabricante> optional = repository.findById( id );

        if ( optional.isPresent() ){
            return optional.get();
        }

        throw new NenhumElementoEncontradoException("Nenhum fabricante foi encontrado com o id informado");
    }

    public List<Fabricante> listarFabricantes (){

        List<Fabricante> fabricantes = repository.findAll();

        if ( fabricantes.isEmpty() ){
            throw new NenhumElementoEncontradoException( "Nenhum fabricante foi localizado no banco de dados" );
        }

        return fabricantes;

    }

    public Fabricante atualizarFabricante ( Fabricante fabricante, Integer id ){

        if ( repository.existsById( id ) ){
            fabricante.setId( id );
            return criarFabricante( fabricante );
        }

        throw new NenhumElementoEncontradoException("O fabricante a ser atualizado não foi localizado");

    }

    public void deletarFabricante ( Integer id ) {

        if ( repository.existsById( id ) ){
            repository.deleteById( id );
        }

        throw new NenhumElementoEncontradoException("O fabricante a ser deletado não foi localizado");

    }
}
