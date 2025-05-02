package com.raju.supplier_data_manager.advices;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(
            @NonNull Throwable ex,
            @NonNull DataFetchingEnvironment env) {

        if (ex instanceof com.raju.supplier_data_manager.exceptions.SupplierNotFoundException) {
            return handleSupplierNotFoundException((com.raju.supplier_data_manager.exceptions.SupplierNotFoundException) ex, env);
        } else if (ex instanceof IllegalArgumentException) {
            return handleIllegalArgumentException((IllegalArgumentException) ex, env);
        } else {
            return handleGenericException(ex, env);
        }
    }

    private GraphQLError handleSupplierNotFoundException(com.raju.supplier_data_manager.exceptions.SupplierNotFoundException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage() != null ? ex.getMessage() : "Supplier not found")
                .errorType(ErrorType.NOT_FOUND)
                .extensions(Map.of("errorCode", "SUPPLIER_NOT_FOUND"))
                .build();
    }

    private GraphQLError handleIllegalArgumentException(IllegalArgumentException ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage() != null ? ex.getMessage() : "Invalid input")
                .errorType(ErrorType.BAD_REQUEST)
                .extensions(Map.of("errorCode", "BAD_REQUEST"))
                .build();
    }

    private GraphQLError handleGenericException(Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred")
                .errorType(ErrorType.INTERNAL_ERROR)
                .extensions(Map.of("errorCode", "INTERNAL_ERROR"))
                .build();
    }
}