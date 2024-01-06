package com.acme.hochschule.graphql;

import java.util.UUID;

/**
 * Value-Klasse für das Resultat, wenn an der GraphQL-Schnittstelle eine neue Fakultät angelegt wurde.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 *
 * @param id id die eindeutige ID der neu angelegten Fakultät.
 */
record CreatePayload(UUID id) {
}
