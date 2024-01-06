package com.acme.hochschule.graphql;

import com.acme.hochschule.entity.Buero;

/**
 * Eingabedaten für einen Professor.
 *
 * @author Ahmad Hawarnah <a href="mailto:haah1014@h-ka.de">haah1014@h-ka.de</a>
 * @param name Der Name des Professors als unveränderliches Pflichtfeld.
 * @param buero Das Büro des Professors.
 */
record ProfessorInput(
    String name,
    Buero buero
) {
}
