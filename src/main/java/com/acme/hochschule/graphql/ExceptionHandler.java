package com.acme.hochschule.graphql;

import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.service.ConstraintViolationsException;
import com.acme.hochschule.service.DekanExistsException;
import com.acme.hochschule.service.NameExistsException;
import com.acme.hochschule.service.NotFoundException;
import graphql.GraphQLError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.graphql.execution.ErrorType.BAD_REQUEST;
import static org.springframework.graphql.execution.ErrorType.NOT_FOUND;

/**
 * Abbildung von Exceptions auf GraphQLError.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 */
@ControllerAdvice
class ExceptionHandler {
    @GraphQlExceptionHandler
    GraphQLError onNotFound(final NotFoundException ex) {
        final var id = ex.getId();
        final var message = id == null
            ? STR."Keine Fakultaet gefunden: suchkriterien=\{ex.getSuchkriterien()}"
            : STR."Keine Fakultaet mit der ID \{id} gefunden";
        return GraphQLError.newError()
            .errorType(NOT_FOUND)
            .message(message)
            .build();
    }

    @GraphQlExceptionHandler
    GraphQLError onDekanExist(final DekanExistsException ex) {
        final var dekan = ex.getDekan();
        final var message = STR."Der Dekan \{dekan} ist ein Dekan von andere Fakultaet";
        return GraphQLError.newError()
            .errorType(NOT_FOUND)
            .message(message)
            .build();
    }

    @GraphQlExceptionHandler
    GraphQLError onNameExist(final NameExistsException ex) {
        final var name = ex.getName();
        final var message = STR."Der Name \{name} existiert bereits";
        return GraphQLError.newError()
            .errorType(NOT_FOUND)
            .message(message)
            .build();
    }

    @GraphQlExceptionHandler
    Collection<GraphQLError> onConstraintViolations(final ConstraintViolationsException ex) {
        return ex.getViolations()
            .stream()
            .map(this::violationToGraphQLError)
            .collect(Collectors.toList());
    }

    private GraphQLError violationToGraphQLError(final ConstraintViolation<Fakultaet> violation) {
        final List<Object> path = new ArrayList<>(5);
        path.add("input");
        for (final Path.Node node : violation.getPropertyPath()) {
            path.add(node.toString());
        }
        return GraphQLError.newError()
            .errorType(BAD_REQUEST)
            .message(violation.getMessage())
            .path(path)
            .build();
    }
}
