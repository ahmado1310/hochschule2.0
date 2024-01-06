package com.acme.hochschule.graphql;

import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.service.FakultaetReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyMap;

/**
 * Eine Controller-Klasse für das Lesen von Fakultätsdaten mit der GraphQL-Schnittstelle.
 * Stellt Methoden zur Abfrage von Fakultätsdaten bereit.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class FakultaetQueryController {
    private final FakultaetReadService service;

    /**
     * Suche anhand der Fakultät-ID.
     *
     * @param id ID der zu suchenden Fakultät
     * @return Die gefundene Fakultät
     */
    @QueryMapping("fakultaet")
    Fakultaet findById(@Argument final UUID id) {
        log.debug("findById: id = {}", id);
        final var fakultaet = service.findById(id);
        log.debug("findById: Fakultaet= {}", fakultaet);
        return fakultaet;
    }

    /**
     * Suche mit diversen Suchkriterien.
     *
     * @param input Suchkriterien und ihre Werte, z.B. Name einer Fakultät
     * @return Die gefundenen Fakultäten als Collection
     */
    @QueryMapping("fakultaeten")
    Collection<Fakultaet> find(@Argument final Optional<Suchkriterien> input) {
        log.debug("find: suchkriterien = {}", input);
        final var suchkriterien = input.map(Suchkriterien::toMap).orElse(emptyMap());
        log.debug("find: suchkriterien = {}", suchkriterien);
        final var fakultaeten = service.find(suchkriterien);
        log.debug("find: fakultaeten = {}", fakultaeten);
        return fakultaeten;
    }
}
