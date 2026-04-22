package com.cvmaker.Jobscorecv.Common.ExceptionHandling;

import com.cvmaker.Jobscorecv.Common.APIResponse.ApiBaseResponses;
import com.cvmaker.Jobscorecv.Common.Enums.ResponseStatus;
import com.cvmaker.Jobscorecv.Common.ExceptionHandling.CustomExceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.lang.InterruptedException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@ControllerAdvice
@CrossOrigin
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final String unknownStatus = ResponseStatus.UNKNOWN.getStatus();

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(
                    error.getField(),
                    error.getDefaultMessage());});

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ne){
        ne.printStackTrace();
        String code = String.valueOf(HttpStatus.NO_CONTENT.value());
        String status = ResponseStatus.ERROR.getStatus();
        String msg = ne.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiBaseResponses(code, status, msg));
    }

    @ExceptionHandler(DuplicateValuesException.class)
    public ResponseEntity<Object> handleDuplicateValuesException(DuplicateValuesException de){
        de.printStackTrace();
        String code = String.valueOf(HttpStatus.CONFLICT.value());
        String status = ResponseStatus.DUPLICATE.getStatus();
        String msg = de.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiBaseResponses(code, status, msg));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex){
        ex.printStackTrace();

        String code = String.valueOf(HttpStatus.CONFLICT);
        String status = ResponseStatus.ERROR.getStatus();
        String msg = ex.getMessage();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiBaseResponses(code, status, msg));
    }


    @ExceptionHandler(AssertionError.class)
    public ResponseEntity<Object> handleAssertionError(AssertionError ae){
        ae.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiBaseResponses(
                "No Enum Found",
                "41000",
                ae.getMessage()
        ));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ue){
        ue.printStackTrace();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiBaseResponses(
                HttpStatus.UNAUTHORIZED.name(),
                String.valueOf(HttpStatus.UNAUTHORIZED.value()),
                ue.getMessage()
        ));
    }

    @ExceptionHandler(UserStatusException.class)
    public ResponseEntity<Object> handleUserStatusException(UserStatusException ue){
        ue.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiBaseResponses(
                HttpStatus.CONFLICT.name(),
                String.valueOf(HttpStatus.CONFLICT.value()),
                ue.getMessage()
        ));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException ite){
        ite.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.hashCode()),
                ite.getMessage()
        ));
    }

    @ExceptionHandler(InsufficientTokenException.class)
    public ResponseEntity<Object> handleInsufficientTokenException(InsufficientTokenException ite){
        ite.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiBaseResponses(
                "Token Insufficient",
                "40000",
                ite.getMessage()
        ));
    }

    @ExceptionHandler(NullElementsException.class)
    public ResponseEntity<Object> NullElementsException(NullElementsException ne){

        ne.printStackTrace();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiBaseResponses(
                "Null Elements found on DB",
                "40000",
                ne.getMessage()
        ));
    }

    @ExceptionHandler(IdNotFoundInListException.class)
    public ResponseEntity<Object> handleIdNotFoundInListException(IdNotFoundInListException inle){

        inle.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiBaseResponses(
                HttpStatus.NOT_FOUND.toString(),
                "20000",
                inle.getMessage()
        ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex){

        ex.printStackTrace();

        String code = String.valueOf(HttpStatus.NOT_FOUND.value());
        String status = ResponseStatus.NOT_FOUND.getStatus();
        String msg = ex.getMessage();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiBaseResponses(code, status, msg));
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException aef){

        aef.printStackTrace();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiBaseResponses(
                HttpStatus.BAD_REQUEST.toString(),
                "20000",
                aef.getMessage()
        ));
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<Object> handleInterruptedException(InterruptedException ie){

        ie.printStackTrace();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ApiBaseResponses(
                "Have Interruption",
                "40000",
                ie.getMessage()
        ));
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<Object> handleExecutionException(ExecutionException ee){

        ee.printStackTrace();
        return ResponseEntity.ok(new ApiBaseResponses(
                "Function Execution Exception",
                "40000",
                ee.getMessage()
        ));
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Object> handleIllegalAccessException(IllegalAccessException ex) {

        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.CONFLICT).body(new ApiBaseResponses(
                        "Illegal Access",
                        "40000",
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException re) {

        re.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.CONFLICT).body(new ApiBaseResponses(
                        "Runtime Exception Occur",
                        "21000",
                        re.getMessage()
                ));
    }


}

