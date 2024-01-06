package com.acme.hochschule.graphql;

/**
 * Eingabedaten für einen Dekan.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 *
 * @param name Der Name des Dekans als unveränderliches Pflichtfeld.
 */
record DekanInput(
    String name
) {
}
