package com.acme.hochschule.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Speichert Informationen über Dekane.
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Dekan {
    /**
     * Die UUID des Dekans.
     *
     */
    @EqualsAndHashCode.Include
    public UUID id;

    /**
     * Name des Dekans.
     */
    @NotBlank
    @Size(min = 2, max = 32)
    public String name;

}
