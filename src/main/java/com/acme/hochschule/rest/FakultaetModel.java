package com.acme.hochschule.rest;

import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.entity.Professor;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

/**
 * Model-Klasse für Spring HATEOAS. @lombok.Data fasst die Annotationsn @ToString, @EqualsAndHashCode, @Getter, @Setter
 * und @RequiredArgsConstructor zusammen.
 *
 * @author <a href="mailto:haah1014@h-ka.de">Ahmad Hawarnah</a>
 */
@JsonPropertyOrder({
    "name", "professoren", "dekan"
})
@Relation(collectionRelation = "Fakultaeten", itemRelation = "Fakultaet")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Getter
@Setter
@ToString(callSuper = true)
class FakultaetModel extends RepresentationModel<FakultaetModel>{
    private final String name;
    private final List<Professor> professoren;
    private final Dekan dekan;

    FakultaetModel(final Fakultaet fakultaet){
        name = fakultaet.getName();
        professoren = fakultaet.getProfessoren();
        dekan = fakultaet.getDekan();
    }
}
