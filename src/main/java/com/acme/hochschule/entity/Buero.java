package com.acme.hochschule.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Speichert Informationen über Büros.
 * Annahme: Ein Professor hat nur ein Büro
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Buero {
    /**
     * Die Raumnummer des Büros.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Das Gebäude, wo das Büro sich befindet.
     */
    @NotBlank
    private String gebaeude;
}
