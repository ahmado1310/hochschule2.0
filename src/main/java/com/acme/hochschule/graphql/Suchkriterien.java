package com.acme.hochschule.graphql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Eine Value-Klasse für Eingabedaten passend zu Suchkriterien aus dem GraphQL-Schema.
 *
 * @param name      Der Name, der als Suchkriterium verwendet wird.
 * @param professor Der Name des Professors, der als Suchkriterium verwendet wird.
 * @param dekan     Der Name des Dekans, der als Suchkriterium verwendet wird.
 * @author <a href="mailto:haah104@h-ka.de"> Ahmad Hawarnah </a>
 */
record Suchkriterien(
    String name,
    String professor,
    String dekan
) {
    /**
     * Konvertierung in eine Map.
     *
     * @return Das konvertierte Map-Objekt
     */
    Map<String, List<String>> toMap() {
        final Map<String, List<String>> map = new HashMap<>(2, 1);
        if (name != null) {
            map.put("name", List.of(name));
        }

        if (professor != null) {
            map.put("professor", List.of(professor));
        }

        if (dekan != null) {
            map.put("dekan", List.of(dekan));
        }

        return map;
    }
}
