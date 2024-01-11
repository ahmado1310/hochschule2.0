package com.acme.hochschule.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.LAZY;

/**
 * Speichert Informationen über Fakultäten.
 *
 * @author <a href="">Ahmad Hawarnah</a>
 */
@Entity
@Table(name = "fakultaet")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"RequireEmptyLineBeforeBlockTagGroup"})
public class Fakultaet {
    /**
     * Die UUID der Fakultät.
     */
    @Id
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
    @OneToMany(cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "fakultaet_id")
    @OrderColumn(name = "idx", nullable = false)
    private List<Professor> professoren;

    /**
     * Dekan der Fakultät.
     */
    @Valid
    @OneToOne(optional = false, cascade = {PERSIST, REMOVE}, fetch = LAZY, orphanRemoval = true)
    private Dekan dekan;
}
