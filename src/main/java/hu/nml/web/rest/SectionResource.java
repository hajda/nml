package hu.nml.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.nml.domain.Section;
import hu.nml.repository.SectionRepository;
import hu.nml.web.rest.util.HeaderUtil;
import hu.nml.web.rest.dto.SectionDTO;
import hu.nml.web.rest.mapper.SectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Section.
 */
@RestController
@RequestMapping("/api")
public class SectionResource {

    private final Logger log = LoggerFactory.getLogger(SectionResource.class);
        
    @Inject
    private SectionRepository sectionRepository;
    
    @Inject
    private SectionMapper sectionMapper;
    
    /**
     * POST  /sections -> Create a new section.
     */
    @RequestMapping(value = "/sections",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SectionDTO> createSection(@Valid @RequestBody SectionDTO sectionDTO) throws URISyntaxException {
        log.debug("REST request to save Section : {}", sectionDTO);
        if (sectionDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("section", "idexists", "A new section cannot already have an ID")).body(null);
        }
        Section section = sectionMapper.sectionDTOToSection(sectionDTO);
        section = sectionRepository.save(section);
        SectionDTO result = sectionMapper.sectionToSectionDTO(section);
        return ResponseEntity.created(new URI("/api/sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("section", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sections -> Updates an existing section.
     */
    @RequestMapping(value = "/sections",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SectionDTO> updateSection(@Valid @RequestBody SectionDTO sectionDTO) throws URISyntaxException {
        log.debug("REST request to update Section : {}", sectionDTO);
        if (sectionDTO.getId() == null) {
            return createSection(sectionDTO);
        }
        Section section = sectionMapper.sectionDTOToSection(sectionDTO);
        section = sectionRepository.save(section);
        SectionDTO result = sectionMapper.sectionToSectionDTO(section);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("section", sectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sections -> get all the sections.
     */
    @RequestMapping(value = "/sections",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<SectionDTO> getAllSections() {
        log.debug("REST request to get all Sections");
        return sectionRepository.findAll().stream()
            .map(sectionMapper::sectionToSectionDTO)
            .collect(Collectors.toCollection(LinkedList::new));
            }

    /**
     * GET  /sections/:id -> get the "id" section.
     */
    @RequestMapping(value = "/sections/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SectionDTO> getSection(@PathVariable Long id) {
        log.debug("REST request to get Section : {}", id);
        Section section = sectionRepository.findOne(id);
        SectionDTO sectionDTO = sectionMapper.sectionToSectionDTO(section);
        return Optional.ofNullable(sectionDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sections/:id -> delete the "id" section.
     */
    @RequestMapping(value = "/sections/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSection(@PathVariable Long id) {
        log.debug("REST request to delete Section : {}", id);
        sectionRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("section", id.toString())).build();
    }
}
