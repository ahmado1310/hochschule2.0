package com.acme.hochschule.service;

import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.repository.FakultaetRepository2;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Anwendungslogik für Fakultät.
 *
 * @author <a href="mailto:haah1014@kka.com">Ahmad Hawarnah</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FakultaetReadService {
    private final FakultaetRepository2 fakultaetRepository;

    /**
     * Finde bestimmte Fakultäten bei ID.
     *
     * @param id ID
     * @return zutreffende Fakultäten.
     */
    public @NonNull Fakultaet findById(final UUID id) {
        log.debug("findById: id={}", id);
        final var fakultaet = fakultaetRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        log.debug("findById: {}", fakultaet);
        return fakultaet;
    }

    /**
     * Fakultät anhand von Suchkriterien als Collection suchen.
     *
     * @param suchkriterien Die Suchkriterien
     * @return Die gefundene Fakultät oder eine leere Collection
     * @throws NotFoundException Falls keine Kunden gefunden wurden
     */
    @SuppressWarnings({"ReturnCount", "NestedIfDepth"})
    public @NonNull Collection<Fakultaet> find(@NonNull final Map<String, List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return fakultaetRepository.findAll();
        }

        if (suchkriterien.size() == 1) {
            final var name = suchkriterien.get("name");
            if (name != null && name.size() == 1) {
                final var fakultaet = fakultaetRepository.findByName(name.getFirst());
                if (fakultaet.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                log.debug("find (name): {}", fakultaet);
                return fakultaet;
            }

            final var professor = suchkriterien.get("professor");
            if (professor != null && professor.size() == 1) {
                final var fakultaet = fakultaetRepository.findByProfessor(professor.getFirst());
                if (fakultaet.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                log.debug("find (professor): {}", professor);
                return fakultaet;
            }

            final var dekan = suchkriterien.get("dekan");
            if (dekan != null && dekan.size() == 1) {
                final var fakultaet = fakultaetRepository.findByDekan(dekan.getFirst());
                if (fakultaet.isEmpty()) {
                    throw new NotFoundException(suchkriterien);
                }
                log.debug("find (dekan): {}", dekan);
                return fakultaet;
            }
        }

        final var fakultaet = fakultaetRepository.find(suchkriterien);
        if (fakultaet.isEmpty()) {
            throw new NotFoundException(suchkriterien);
        }
        log.debug("find: {}", fakultaet);
        return fakultaet;
    }
}
