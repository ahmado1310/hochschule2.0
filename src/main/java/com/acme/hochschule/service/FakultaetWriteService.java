package com.acme.hochschule.service;

import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.repository.FakultaetRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Anwendungslogik für Fakultät.
 *
 * @author <a href="mailto:haah1014@kka.com">Ahmad Hawarnah</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FakultaetWriteService {
    private final FakultaetRepository fakultaetRepository;
    private final Validator validator;

    /**
     * Erstellt eine neue Fakultät und speichert sie in der Datenbank.
     *
     * @param fakultaet Das zu erstellende Fakultät-Objekt.
     * @return Das in der Datenbank gespeicherte Fakultät-Objekt.
     * @throws ConstraintViolationsException wenn die Validierung der Fakultätsdaten fehlschlägt.
     * @throws NameExistsException           wenn der Name der Fakultät bereits existiert.
     * @throws DekanExistsException          wenn der Dekan bereits einer anderen Fakultät zugeordnet ist.
     */
    public Fakultaet create(final Fakultaet fakultaet) {
        log.debug("creat: fakultaet{}", fakultaet);
        final var violations = validator.validate(fakultaet);
        if (!violations.isEmpty()) {
            log.debug("create: violations={}", violations);
            throw new ConstraintViolationsException(violations);
        }

        if (fakultaetRepository.isNameExisting(fakultaet.getName())) {
            throw new NameExistsException(fakultaet.getName());
        }

        if (fakultaetRepository.isDekanExisting(fakultaet.getDekan().name)) {
            throw new DekanExistsException(fakultaet.getDekan().name);
        }

        final var fakultaetDB = fakultaetRepository.create(fakultaet);
        log.debug("creat: {}", fakultaetDB);
        return fakultaetDB;
    }

    /**
     * Aktualisiert die Daten einer bestehenden Fakultät.
     *
     * @param id        Die eindeutige Identifikationsnummer der Fakultät.
     * @param fakultaet Die neuen Daten der Fakultät.
     * @throws ConstraintViolationsException wenn die Validierung der Fakultätsdaten fehlschlägt.
     * @throws NotFoundException             wenn keine Fakultät mit der gegebenen ID gefunden wurde.
     * @throws DekanExistsException          wenn der Dekan bereits einer anderen Fakultät zugeordnet ist.
     */
    public void update(final UUID id, final Fakultaet fakultaet) {
        log.debug("FakultaetID: {}", id);
        log.debug("update: {}", fakultaet);

        final var violations = validator.validate(fakultaet);
        if (!violations.isEmpty()) {
            log.debug("update: violations: {}", violations);
            throw new ConstraintViolationsException(violations);
        }

        final var fakultaetDbOptional = fakultaetRepository.findById(id);
        if (fakultaetDbOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        if (fakultaetRepository.isDekanExisting(fakultaet.getDekan().name)) {
            throw new DekanExistsException(fakultaet.getDekan().name);
        }

        fakultaet.setId(id);
        fakultaetRepository.update(fakultaet);
    }

}
