package com.acme.hochschule.rest;

import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Professor;

import java.util.List;

/**
 * Data Transfer Object (DTO) für Fakultätsinformationen.
 * @param name        Der Name der Fakultät.
 * @param professoren Liste der zugehörigen Professoren.
 * @param dekan der zugehörige Dekan
 * @author <a href="mailto:gale1012@h-ka.de">Ahmad Hawarnah</a>
 */
record FakultaetDTO(
    String name,
    List<Professor> professoren,
    Dekan dekan
) {

}
