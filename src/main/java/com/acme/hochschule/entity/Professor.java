package com.acme.hochschule.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Speichert Informationen über Profs.
 * Annahme: Ein Professor arbeitet nur in eine Fakultät
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Professor {
    /**
     * Die UUID des Professors.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Name des Professors.
     */
    @NotBlank
    @Size(min = 2, max = 32)
    private String name;

    /**
     * Das Büro des Professors.
     */
    @NotNull
    @Valid
    private Buero buero;
}
