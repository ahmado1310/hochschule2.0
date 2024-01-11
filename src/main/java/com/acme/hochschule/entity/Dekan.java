package com.acme.hochschule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

/**
 * Speichert Informationen über Dekane.
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Entity
@Table(name = "dekan")
@NoArgsConstructor
@AllArgsConstructor
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
    @Id
    @EqualsAndHashCode.Include
    public UUID id;

    /**
     * Name des Dekans.
     */
    @NotBlank
    @Size(min = 2, max = 32)
    public String name;

}
