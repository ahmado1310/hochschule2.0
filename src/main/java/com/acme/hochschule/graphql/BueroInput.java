package com.acme.hochschule.graphql;

/**
 * Eingabedaten für ein Büro.
 * @author Ahmad Hawarnah <a href="mailto:haah104@h-ka.de">haah104@h-ka.de</a>
 * @param gebaeude Das Gebäude, in dem sich das Büro befindet, als unveränderliches Pflichtfeld.
 */
record BueroInput(
    String gebaeude
) {
}
