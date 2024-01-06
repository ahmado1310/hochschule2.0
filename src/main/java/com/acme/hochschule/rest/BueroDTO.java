package com.acme.hochschule.rest;

/**
 * Data Transfer Object (DTO) für Büroinformationen.
 * <p>
 * Enthält die Bezeichnung des Gebäudes, in dem sich das Büro befindet.
 * </p>
 *
 * @param gebaeude Der Name des Gebäudes.
 * @author <a href="mailto:gale1012@h-ka.de">Ahmad Hawarnah</a>
 */
record BueroDTO(
    String gebaeude
) {
}
