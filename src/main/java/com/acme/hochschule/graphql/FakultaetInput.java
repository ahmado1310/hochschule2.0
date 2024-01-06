package com.acme.hochschule.graphql;

import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Professor;
import java.util.List;

/**
 * Eingabedaten für eine Fakultät.
 *
 * @author Ahmad Hawarnah <a href="mailto:haah1014@h-ka.de">haah1014@h-ka.de</a>
 * @param name Der Name der Fakultät als unveränderliches Pflichtfeld.
 * @param professoren Eine Liste von Professoren, die der Fakultät zugeordnet sind.
 * @param dekan Der Dekan der Fakultät.
 */
record FakultaetInput(
    String name,
    List<Professor> professoren,
    Dekan dekan
) {
}
