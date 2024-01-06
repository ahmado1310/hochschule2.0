package com.acme.hochschule.rest;

import com.acme.hochschule.service.FakultaetReadService;
import com.acme.hochschule.service.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.MediaTypes.HAL_JSON_VALUE;

/**
 * Controller für die Get-Requests, um die Fakultäten zu bedienen.
 *
 * @author <a href="">Ahmad Hawarnh</a>
 */
@RestController
@RequestMapping(FakultaetGetController.REST_PATH)
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("TrailingComment")
public class FakultaetGetController {
    /**
     * Basispfad für die REST-Schnittstelle.
     */
    public static final String REST_PATH = "/rest"; //NOSONAR

    /**
     * Muster für eine UUID. [\dA-Fa-f]{8}{8}-([\dA-Fa-f]{8}{4}-){3}[\dA-Fa-f]{8}{12} enthält eine "capturing group"
     * und ist nicht zulässig.
     */
    public static final String ID_PATTERN =
        "[\\dA-Fa-f]{8}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{12}";
    private final FakultaetReadService service;
    private final UriHelper uriHelper;

    /**
     * Suche anhand der Fakultaet-Id als Pfad-Parameter.
     *
     * @param id      id des zu suchenden Produkts
     * @param request Das Request-Objekt, um Links für HATEOAS zu erstellen.
     * @return Gefundener Fakultät mit Atom-Links.
     */
    @Operation(summary = "Suche mit ID", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "Fakultaet gefunden")
    @ApiResponse(responseCode = "404", description = "Fakultaet nicht gefunden")
    @GetMapping(path = "{id:" + ID_PATTERN + "}", produces = HAL_JSON_VALUE)
    FakultaetModel getById(@PathVariable final UUID id, final HttpServletRequest request) {
        log.debug("getById: Id={}, Thread={}", id, Thread.currentThread().getName());

        // Geschäftslogik bzw. Anwendungskern
        final var fakultaet = service.findById(id);

        // HATEOAS
        final var model = new FakultaetModel(fakultaet);

        // evtl. Forwarding von einem API-Gateway
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var idUri = STR."\{baseUri}/\{fakultaet.getId()}";
        final var selfLink = Link.of(idUri);
        final var listLink = Link.of(baseUri, LinkRelation.of("list"));
        final var addLink = Link.of(baseUri, LinkRelation.of("add"));
        final var updateLink = Link.of(idUri, LinkRelation.of("update"));
        model.add(selfLink, listLink, addLink, updateLink);

        log.debug("getById: {}", model);
        return model;
    }

    /**
     * Suche mit Suchkriterien die passende Fakultät als Query-Parameter.
     *
     * @param suchkriterien Suchkriterien der zu suchenden Fakultät
     * @return Gefundene Fakultät.
     */
    @Operation(summary = "Suche mit Suchkriterien", tags = "Suchen")
    @ApiResponse(responseCode = "200", description = "CollectionModel mit den Fakultaeten")
    @ApiResponse(responseCode = "404", description = "keine Fakultaeten gefunden")
    @GetMapping(produces = HAL_JSON_VALUE)
    CollectionModel<FakultaetModel> get(
        @RequestParam @NonNull final MultiValueMap<String, String> suchkriterien,
        final HttpServletRequest request
    ) {
        log.debug("get: suchkriterien={}", suchkriterien);

        final var baseUri = uriHelper.getBaseUri(request).toString();

        // Geschaeftslogik bzw. Anwendungskern
        final var models = service.find(suchkriterien)
            .stream()
            .map(fakultaet -> {
                final var model = new FakultaetModel(fakultaet);
                model.add(Link.of(STR."\{baseUri}/\{fakultaet.getId()}"));
                return model;
            })
            .toList();

        log.debug("get {}", models);
        return CollectionModel.of(models);
    }

    /**
     * Behandelt {@link NotFoundException} und antwortet mit Status 404.
     *
     * @param ex die zu behandelnde Ausnahme
     * @return ResponseEntity mit Fehlermeldung
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        // Logik für den Umgang mit der Exception
        log.debug("Handling NotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
