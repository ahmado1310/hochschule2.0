package com.acme.hochschule.entity;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * Speichert Informationen über Fakultäten.
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Fakultaet {
    /**
     * Die UUID der Fakultät.
     */
    @EqualsAndHashCode.Include
    private UUID id;

    /**
     * Name der Fakultät.
     */
    @NotBlank
    private String name;

    /**
     * Liste aller Professoren, die an der Fakultät unterrichten.
     */
    @NotEmpty
    @UniqueElements
    private List<Professor> professoren;

    /**
     * Dekan der Fakultät.
     */
    @Valid
    private Dekan dekan;
}
