package com.acme.hochschule.repository;

import com.acme.hochschule.entity.Fakultaet;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.acme.hochschule.repository.DB.FAKULTAETEN;
import static java.util.Collections.emptyList;
import static java.util.UUID.randomUUID;

/**
 * Repository für den DB-Zugriff bei Fakultäten.
 *
 * @author <a href="mailto:Ahmad.Hawarnah@example.com">Ahmad Hawarnah</a>
 */
@Repository
@Slf4j
public class FakultaetRepository {
    /**
     * Eine Fakultät anhand ihrer ID suchen.
     *
     * @param id Die Id der gesuchten Fakultät
     * @return Optional mit der gefundenen Fakultät oder leeres Optional
     */
    public Optional<Fakultaet> findById(final UUID id) {
        log.debug("findById: ID={}", id);
        final var result = FAKULTAETEN.stream()
            .filter(fakultaet -> Objects.equals(fakultaet.getId(), id))
            .findFirst();
        log.debug("findByID: {}", result);
        return result;
    }

    /**
     * Fakultät anhand von Suchkriterien ermitteln.
     * Z.B. mit GET <a href="https://localhost:8080/rest?name=Informatik">
     * https://localhost:8080/rest?name=Informatik</a>
     *
     * @param suchkriterien Suchkriterien.
     * @return Gefundene Fakultät oder leere Collection.
     */
    public @NonNull Collection<Fakultaet> find(final Map<String, ? extends List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return findAll();
        }

        for (final var entry : suchkriterien.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> {
                    return findByName(entry.getValue().getFirst());
                }
                case "professor" -> {
                    return findByProfessor(entry.getValue().getFirst());
                }
                case "dekan" -> {
                    return findByDekan(entry.getValue().getFirst());
                }
                default -> {
                    log.debug("find: ungültiges Suchkriterium={}", entry.getKey());
                    return emptyList();
                }

            }
        }
        return emptyList();
    }

    /**
     * Alle Fakultaeten als Collection ermitteln.
     *
     * @return Alle Fakultaeten
     */
    public @NonNull Collection<Fakultaet> findAll() {
        return FAKULTAETEN;
    }

    /**
     * Findet Fakultäten anhand eines Namensbestandteils.
     *
     * @param name Teil des Namens, nach dem gefiltert wird.
     * @return Sammlung von Fakultäten, deren Namen den Suchbegriff enthalten.
     */
    public Collection<Fakultaet> findByName(final String name) {
        log.debug("findByName: name={}", name);
        final var fakultaeten = FAKULTAETEN.stream()
            .filter(fakultaet -> fakultaet.getName().contains(name))
            .toList();
        log.debug("findByName: fakultaet={}", fakultaeten);
        return fakultaeten;
    }

    /**
     * Findet Fakultäten anhand der Zugehörigkeit eines Professors.
     *
     * @param professor Suchbegriff für den Namen des Professors.
     * @return Sammlung von Fakultäten mit dem gesuchten Professor.
     */
    public Collection<Fakultaet> findByProfessor(final String professor) {
        log.debug("findByProfesor: professor={}", professor);
        final var fakultaeten = FAKULTAETEN.stream()
            .filter(fakultaet -> fakultaet.getProfessoren().stream()
                .anyMatch(prof -> prof.getName().contains(professor))
            )
            .toList();
        log.debug("findByProfesor: {}", fakultaeten);
        return fakultaeten;
    }

    /**
     * Findet Fakultäten anhand der Zugehörigkeit eines Dekans.
     *
     * @param dekan Suchbegriff für den Namen des Dekans.
     * @return Sammlung von Fakultäten, in denen der gesuchte Dekan zugeordnet ist.
     */
    public Collection<Fakultaet> findByDekan(final String dekan) {
        log.debug("findByDekan: Dekan={}", dekan);
        final var fakultaeten = FAKULTAETEN.stream()
            .filter(fakultaet -> fakultaet.getDekan().name.contains(dekan))
            .toList();
        log.debug("findByDekan: {}", fakultaeten);
        return fakultaeten;
    }

    /**
     * Einen neuen Kunden anlegen.
     *
     * @param fakultaet Das Objekt des neu anzulegenden Kunden.
     * @return Der neu angelegte Fakultaet mit generierter ID
     */
    public @NonNull Fakultaet create(final @NonNull Fakultaet fakultaet) {
        log.debug("create: {}", fakultaet);
        fakultaet.setId(randomUUID());
        FAKULTAETEN.add(fakultaet);
        log.debug("create: {}", fakultaet);
        return fakultaet;
    }

    /**
     * Aktualisiert die Daten einer bestehenden Fakultät.
     *
     * @param fakultaet Die Fakultät mit den neuen Daten, einschließlich der ID der zu aktualisierenden Fakultät.
     */
    public void update(final @NonNull Fakultaet fakultaet) {
        log.debug("update: {}", fakultaet);
        final OptionalInt index = IntStream
            .range(0, FAKULTAETEN.size())
            .filter(i -> Objects.equals(FAKULTAETEN.get(i).getId(), fakultaet.getId()))
            .findFirst();
        if (index.isEmpty()) {
            return;
        }
        FAKULTAETEN.set(index.getAsInt(), fakultaet);
        log.debug("update: {}", fakultaet);
    }

    /**
     * Überprüft, ob eine Fakultät mit dem angegebenen Namen existiert.
     *
     * @param name Der zu überprüfende Name der Fakultät.
     * @return true, wenn eine Fakultät mit dem Namen existiert, andernfalls false.
     */
    public boolean isNameExisting(final String name) {
        log.debug("isNameExisting: name={}", name);
        final var count = FAKULTAETEN.stream()
            .filter(fakultaet -> Objects.equals(fakultaet.getName(), name))
            .count();
        log.debug("isNameExisting: count={}", count);
        return count > 0L;
    }

    public boolean isDekanExisting(final String dekan) {
        log.debug("isDekanExisting: dekan={}", dekan);
        final var count = FAKULTAETEN.stream()
            .filter(fakultaet -> Objects.equals(
                fakultaet.getDekan().name, dekan))
            .count();
        log.debug("isDekanExisting: count={}", count);
        return count > 0L;
    }
}
