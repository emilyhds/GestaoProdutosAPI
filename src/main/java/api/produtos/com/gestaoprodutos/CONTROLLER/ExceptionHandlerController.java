package api.produtos.com.gestaoprodutos.CONTROLLER;

import api.produtos.com.gestaoprodutos.EXCEPTION.AtributosInvalidosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.DadosDuplicadosException;
import api.produtos.com.gestaoprodutos.EXCEPTION.NenhumElementoEncontradoException;
import api.produtos.com.gestaoprodutos.MODELS.DTO.RESPONSE.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler ( NoSuchElementException.class )
    public ResponseEntity<ErrorResponseDTO> handleNoSuchElementException (NoSuchElementException e ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO( e.getMessage(), Instant.now() );
        return new ResponseEntity<>( errorResponseDTO, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler (AtributosInvalidosException.class )
    public ResponseEntity<ErrorResponseDTO> handleAtributosInvalidosException (AtributosInvalidosException e ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO( e.getMessage(), Instant.now() );
        return new ResponseEntity<>( errorResponseDTO, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler (DadosDuplicadosException.class )
    public ResponseEntity<ErrorResponseDTO> handleDadosDuplicadosException (DadosDuplicadosException e ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO( e.getMessage(), Instant.now() );
        return new ResponseEntity<>( errorResponseDTO, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler ( NenhumElementoEncontradoException.class )
    public ResponseEntity<ErrorResponseDTO> handleNenhumElementoEncontradoException (NenhumElementoEncontradoException e ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO( e.getMessage(), Instant.now() );
        return new ResponseEntity<>( errorResponseDTO, HttpStatus.NO_CONTENT );
    }

    @ExceptionHandler ( Exception.class )
    public ResponseEntity<ErrorResponseDTO> handleException (Exception e ) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO( e.getMessage(), Instant.now() );
        return new ResponseEntity<>( errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
