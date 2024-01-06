package com.acme.hochschule.rest;

import com.acme.hochschule.service.ConstraintViolationsException;
import com.acme.hochschule.service.DekanExistsException;
import com.acme.hochschule.service.FakultaetWriteService;
import com.acme.hochschule.service.NameExistsException;
import com.acme.hochschule.service.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.UUID;

import static com.acme.hochschule.rest.FakultaetGetController.ID_PATTERN;
import static com.acme.hochschule.rest.FakultaetGetController.REST_PATH;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

/**
 * Controller um die POST und PUT Requests um die Fakultäten zu bedienen.
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Controller
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
class FakultaetWriteController {
    private static final String PROBLEM_PATH = "/problem/";
    private final FakultaetWriteService service;
    private final FakultaetMapper mapper;
    private final UriHelper uriHelper;

    /**
     * Fügt eine neue Fakultät hinzu.
     *
     * @param fakultaetDTO Übertragungsobjekt mit den Daten der neuen Fakultät.
     * @param request      Benötigt, um die URI für den Antwort-Header zu generieren.
     * @return ResponseEntity<Void> mit dem URI der neu erstellten Fakultät im 'Location'-Header.
     */
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Eine neue Fakultaet anlegen", tags = "Neuanlegen")
    @ApiResponse(responseCode = "201", description = "Fakultaet neu angelegt")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "422", description = "Validierungsfehler")
    ResponseEntity<Void> post(@RequestBody final FakultaetDTO fakultaetDTO, final HttpServletRequest request) {
        log.debug("post: {}", fakultaetDTO);

        final var fakultaetInput = mapper.toFakultaet(fakultaetDTO);
        final var fakultaet = service.create(fakultaetInput);
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(STR."\{baseUri}/\{fakultaet.getId()}");
        return created(location).build();
    }

    /**
     * Aktualisiert eine Fakultät anhand ihrer ID.
     *
     * @param id           Die ID der Fakultät.
     * @param fakultaetDTO Die neuen Daten der Fakultät.
     */
    @Operation(summary = "Eine Fakultaet mit neuen Werten aktualisieren", tags = "Aktualisieren")
    @ApiResponse(responseCode = "204", description = "Aktualisiert")
    @ApiResponse(responseCode = "400", description = "Syntaktische Fehler im Request-Body")
    @ApiResponse(responseCode = "404", description = "Fakultaet nicht vorhanden")
    @ApiResponse(responseCode = "422", description = "Ungueltige Werte, Name vorhanden oder Dekan ist vorhanden")
    @PutMapping(path = "{id:" + ID_PATTERN + "}", consumes = APPLICATION_JSON_VALUE)
    void put(@PathVariable final UUID id, @RequestBody final FakultaetDTO fakultaetDTO) {
        log.debug("id = {}, {}", id, fakultaetDTO);
        final var fakultaetInput = mapper.toFakultaet(fakultaetDTO);
        service.update(id, fakultaetInput);
    }

    @ExceptionHandler
    ProblemDetail onConstraintViolations(
        final ConstraintViolationsException ex,
        final HttpServletRequest request
    ) {
        log.debug("onConstraintViolations: {}", ex.getMessage());

        final var fakultaetViolations = ex.getViolations()
            .stream()
            .map(violation -> STR."\{violation.getPropertyPath()}: " +
                STR."\{violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName()} " +
                violation.getMessage())
            .toList();
        log.trace("onConstraintViolations: {}", fakultaetViolations);
        final String detail;
        if (fakultaetViolations.isEmpty()) {
            detail = "N/A";
        } else {
            final var violationsStr = fakultaetViolations.toString();
            detail = violationsStr.substring(1, violationsStr.length() - 2);
        }

        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, detail);
        problemDetail.setType(URI.create(STR."\{PROBLEM_PATH}\{ProblemType.CONSTRAINTS.getValue()}"));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));

        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail onNameExists(final NameExistsException ex, final HttpServletRequest request) {
        log.debug("onNameExists: {}", ex.getMessage());

        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setType(URI.create(STR."\{PROBLEM_PATH}\{ProblemType.CONSTRAINTS.getValue()}"));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));

        return problemDetail;
    }

    @ExceptionHandler
    ProblemDetail onDekanExists(final DekanExistsException ex, final HttpServletRequest request) {
        log.debug("onDekanExists: {}", ex.getMessage());

        final var problemDetail = ProblemDetail.forStatusAndDetail(UNPROCESSABLE_ENTITY, ex.getMessage());
        problemDetail.setType(URI.create(STR."\{PROBLEM_PATH}\{ProblemType.CONSTRAINTS.getValue()}"));
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));

        return problemDetail;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        // Logik für den Umgang mit der Exception
        log.debug("Handling NotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
