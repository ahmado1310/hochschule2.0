package com.acme.hochschule.graphql;

import com.acme.hochschule.service.FakultaetWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

/**
 * Eine Controller-Klasse für das Schreiben mit der GraphQL-Schnittstelle und den Typen aus dem GraphQL-Schema.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class FakultaetMutationController {
    private final FakultaetWriteService service;
    private final FakultaetInputMapper mapper;

    /**
     * Einen neuen Datensatz für eine Fakultät anlegen.
     *
     * @param input Die Eingabedaten für eine neue Fakultät
     * @return Die generierte ID für die neue Fakultät als Payload
     */
    @MutationMapping
    CreatePayload create(@Argument final FakultaetInput input) {
        log.debug("create: input = {}", input);
        final var fakultaetNew = mapper.toFakultaet(input);
        final var id = service.create(fakultaetNew).getId();
        log.debug("create: id= {}", id);
        return new CreatePayload(id);
    }
}
