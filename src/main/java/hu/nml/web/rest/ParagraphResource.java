package hu.nml.web.rest;

import com.codahale.metrics.annotation.Timed;
import hu.nml.domain.Paragraph;
import hu.nml.repository.ParagraphRepository;
import hu.nml.web.rest.util.HeaderUtil;
import hu.nml.web.rest.dto.ParagraphDTO;
import hu.nml.web.rest.mapper.ParagraphMapper;
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
 * REST controller for managing Paragraph.
 */
@RestController
@RequestMapping("/api")
public class ParagraphResource {

    private final Logger log = LoggerFactory.getLogger(ParagraphResource.class);
        
    @Inject
    private ParagraphRepository paragraphRepository;
    
    @Inject
    private ParagraphMapper paragraphMapper;
    
    /**
     * POST  /paragraphs -> Create a new paragraph.
     */
    @RequestMapping(value = "/paragraphs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ParagraphDTO> createParagraph(@Valid @RequestBody ParagraphDTO paragraphDTO) throws URISyntaxException {
        log.debug("REST request to save Paragraph : {}", paragraphDTO);
        if (paragraphDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("paragraph", "idexists", "A new paragraph cannot already have an ID")).body(null);
        }
        Paragraph paragraph = paragraphMapper.paragraphDTOToParagraph(paragraphDTO);
        paragraph = paragraphRepository.save(paragraph);
        ParagraphDTO result = paragraphMapper.paragraphToParagraphDTO(paragraph);
        return ResponseEntity.created(new URI("/api/paragraphs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("paragraph", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paragraphs -> Updates an existing paragraph.
     */
    @RequestMapping(value = "/paragraphs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ParagraphDTO> updateParagraph(@Valid @RequestBody ParagraphDTO paragraphDTO) throws URISyntaxException {
        log.debug("REST request to update Paragraph : {}", paragraphDTO);
        if (paragraphDTO.getId() == null) {
            return createParagraph(paragraphDTO);
        }
        Paragraph paragraph = paragraphMapper.paragraphDTOToParagraph(paragraphDTO);
        paragraph = paragraphRepository.save(paragraph);
        ParagraphDTO result = paragraphMapper.paragraphToParagraphDTO(paragraph);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("paragraph", paragraphDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paragraphs -> get all the paragraphs.
     */
    @RequestMapping(value = "/paragraphs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public List<ParagraphDTO> getAllParagraphs() {
        log.debug("REST request to get all Paragraphs");
        return paragraphRepository.findAll().stream()
            .map(paragraphMapper::paragraphToParagraphDTO)
            .collect(Collectors.toCollection(LinkedList::new));
            }

    /**
     * GET  /paragraphs/:id -> get the "id" paragraph.
     */
    @RequestMapping(value = "/paragraphs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ParagraphDTO> getParagraph(@PathVariable Long id) {
        log.debug("REST request to get Paragraph : {}", id);
        Paragraph paragraph = paragraphRepository.findOne(id);
        ParagraphDTO paragraphDTO = paragraphMapper.paragraphToParagraphDTO(paragraph);
        return Optional.ofNullable(paragraphDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /paragraphs/:id -> delete the "id" paragraph.
     */
    @RequestMapping(value = "/paragraphs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteParagraph(@PathVariable Long id) {
        log.debug("REST request to delete Paragraph : {}", id);
        paragraphRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("paragraph", id.toString())).build();
    }
}
