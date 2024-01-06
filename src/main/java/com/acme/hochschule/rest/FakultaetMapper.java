package com.acme.hochschule.rest;

import com.acme.hochschule.entity.Buero;
import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.entity.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 *Mapper zwischen Entity-Klassen. Siehe build\generated\sources\annotationProcessor\java\main\...\KundeMapperImpl.java.
 * @author <a href="mailto:haah104@h-ka.de">Ahmad Hawarnah</a>
 */
@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
interface FakultaetMapper {
    /**
     * Konvertiert ein FakultaetDTO in ein Entity-Objekt für Fakultaet.
     *
     * @param dto FakultaetDTO-Objekt ohne ID.
     * @return Eine Entity-Instanz von Fakultaet mit null als ID.
     */
    @Mapping(target = "id", ignore = true)
    Fakultaet toFakultaet(FakultaetDTO dto);

    /**
     * Konvertiert ein ProfessorDTO in ein Entity-Objekt für Professor.
     *
     * @param dto ProfessorDTO-Objekt ohne ID.
     * @return Eine Entity-Instanz von Professor mit null als ID.
     */
    @Mapping(target = "id", ignore = true)
    Professor toProfessor(ProfessorDTO dto);

    /**
     * Konvertiert ein DekanDto in ein Entity-Objekt für Dekan.
     *
     * @param dto DekanDTO-Objekt ohne ID.
     * @return Eine Entity-Instanz von Dekan mit null als ID.
     */
    @Mapping(target = "id", ignore = true)
    Dekan toDekan(DekanDTO dto);

    /**
     * Konvertiert ein BueroDTO in ein Entity-Objekt für Buero.
     *
     * @param dto BueroDTO-Objekt ohne ID.
     * @return Eine Entity-Instanz von Buero mit null als ID.
     */
    @Mapping(target = "id", ignore = true)
    Buero toBuero(BueroDTO dto);

}
