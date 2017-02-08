package hu.nml.web.rest.mapper;

import hu.nml.domain.*;
import hu.nml.web.rest.dto.SectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Section and its DTO SectionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SectionMapper {

    SectionDTO sectionToSectionDTO(Section section);

    Section sectionDTOToSection(SectionDTO sectionDTO);
}
