package com.acme.hochschule.graphql;

import com.acme.hochschule.entity.Buero;
import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.entity.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Mapper zwischen Entity-Klassen. Siehe build\generated\sources\annotationProcessor\java\main\...\KundeMapperImpl.java.
 *
 * @author <a href="mailto:haah104@h-ka.de">Ahmad Hawarnah</a>
 */
@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
interface FakultaetInputMapper {
    /**
     * Konvertiert ein {@code FakultaetInput} in ein {@code Fakultaet}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code FakultaetInput}-Objekt.
     * @return Ein {@code Fakultaet}-Entity-Objekt mit {@code null} als ID.
     */
    @Mapping(target = "id", ignore = true)
    Fakultaet toFakultaet(FakultaetInput input);

    /**
     * Konvertiert ein {@code ProfessorInput} in ein {@code Professor}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code ProfessorInput}-Objekt.
     * @return Ein {@code Professor}-Entity-Objekt mit {@code null} als ID.
     */
    @Mapping(target = "id", ignore = true)
    Professor toProfessor(ProfessorInput input);

    /**
     * Konvertiert ein {@code DekanInput} in ein {@code Dekan}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code DekanInput}-Objekt.
     * @return Ein {@code Dekan}-Entity-Objekt mit {@code null} als ID.
     */
    @Mapping(target = "id", ignore = true)
    Dekan toDekan(DekanInput input);

    /**
     * Konvertiert ein {@code BueroInput} in ein {@code Buero}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code BueroInput}-Objekt.
     * @return Ein {@code Buero}-Entity-Objekt mit {@code null} als ID.
     */
    @Mapping(target = "id", ignore = true)
    Buero toBuero(BueroInput input);
}
