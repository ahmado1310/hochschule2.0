package com.acme.hochschule.rest;

import com.acme.hochschule.entity.Buero;
/**
 * ValueObject für das Neuanlegen und Ändern eines Professors.
 * Dient als Datenübertragungsobjekt, um Informationen eines Professors zwischen den Schichten zu übermitteln.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 * @param name Der vollständige Name des Professors.
 * @param buero Das Büro, das dem Professor zugewiesen ist.
 */
record ProfessorDTO(
    String name,
    Buero buero
) {
}
