package com.acme.hochschule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
 * Speichert Informationen über Profs.
 * Annahme: Ein Professor arbeitet nur in eine Fakultät
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Entity
@Table(name="professor")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Professor {
    /**
     * Die UUID des Professors.
     */
    @Id
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
    @ManyToOne
    @JoinColumn(name = "buero_id")
    @NotNull
    @Valid
    private Buero buero;
}
