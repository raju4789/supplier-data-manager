package com.raju.supplier_data_manager.advices;

import com.raju.supplier_data_manager.dtos.common.CommonApiResponse;
import com.raju.supplier_data_manager.dtos.common.ErrorDetails;
import com.raju.supplier_data_manager.exceptions.SupplierNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles RuntimeException and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the RuntimeException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(SupplierNotFoundException.class)
    public ResponseEntity<CommonApiResponse<String>> handleSupplierNotFoundException(SupplierNotFoundException ex) {
        log.error("SupplierNotFoundException occurred: {}", ex.getMessage(), ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ex.getMessage())
                .build();
        CommonApiResponse<String> commonApiResponse = CommonApiResponse.<String>builder()
                .errorDetails(errorDetails)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonApiResponse);
    }

    /**
     * Handles RuntimeException and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the RuntimeException
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonApiResponse<String>> handleRuntimeException(RuntimeException ex) {
        log.error("RuntimeException occurred: {}", ex.getMessage(), ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ex.getMessage())
                .build();
        CommonApiResponse<String> commonApiResponse = CommonApiResponse.<String>builder()
                .errorDetails(errorDetails)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonApiResponse);
    }

    /**
     * Handles generic Exception and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the Exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonApiResponse<String>> handleDefaultException(Exception ex) {
        log.error("Exception occurred: {}", ex.getMessage(), ex);
        ErrorDetails errorDetails = ErrorDetails.builder()
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .errorMessage(ex.getMessage())
                .build();
        CommonApiResponse<String> commonApiResponse = CommonApiResponse.<String>builder()
                .errorDetails(errorDetails)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonApiResponse);
    }
}
