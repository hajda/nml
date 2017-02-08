package hu.nml.web.rest.mapper;

import hu.nml.domain.*;
import hu.nml.web.rest.dto.ParagraphDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Paragraph and its DTO ParagraphDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ParagraphMapper {

    @Mapping(source = "paragraphs_in_section.id", target = "paragraphs_in_sectionId")
    ParagraphDTO paragraphToParagraphDTO(Paragraph paragraph);

    @Mapping(source = "paragraphs_in_sectionId", target = "paragraphs_in_section")
    Paragraph paragraphDTOToParagraph(ParagraphDTO paragraphDTO);

    default Section sectionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Section section = new Section();
        section.setId(id);
        return section;
    }
}
