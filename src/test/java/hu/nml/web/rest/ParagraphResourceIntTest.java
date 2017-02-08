package hu.nml.web.rest;

import hu.nml.Application;
import hu.nml.domain.Paragraph;
import hu.nml.repository.ParagraphRepository;
import hu.nml.web.rest.dto.ParagraphDTO;
import hu.nml.web.rest.mapper.ParagraphMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ParagraphResource REST controller.
 *
 * @see ParagraphResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ParagraphResourceIntTest {

    private static final String DEFAULT_TEXT = "AAAAA";
    private static final String UPDATED_TEXT = "BBBBB";

    @Inject
    private ParagraphRepository paragraphRepository;

    @Inject
    private ParagraphMapper paragraphMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restParagraphMockMvc;

    private Paragraph paragraph;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParagraphResource paragraphResource = new ParagraphResource();
        ReflectionTestUtils.setField(paragraphResource, "paragraphRepository", paragraphRepository);
        ReflectionTestUtils.setField(paragraphResource, "paragraphMapper", paragraphMapper);
        this.restParagraphMockMvc = MockMvcBuilders.standaloneSetup(paragraphResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        paragraph = new Paragraph();
        paragraph.setText(DEFAULT_TEXT);
    }

    @Test
    @Transactional
    public void createParagraph() throws Exception {
        int databaseSizeBeforeCreate = paragraphRepository.findAll().size();

        // Create the Paragraph
        ParagraphDTO paragraphDTO = paragraphMapper.paragraphToParagraphDTO(paragraph);

        restParagraphMockMvc.perform(post("/api/paragraphs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paragraphDTO)))
                .andExpect(status().isCreated());

        // Validate the Paragraph in the database
        List<Paragraph> paragraphs = paragraphRepository.findAll();
        assertThat(paragraphs).hasSize(databaseSizeBeforeCreate + 1);
        Paragraph testParagraph = paragraphs.get(paragraphs.size() - 1);
        assertThat(testParagraph.getText()).isEqualTo(DEFAULT_TEXT);
    }

    @Test
    @Transactional
    public void checkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = paragraphRepository.findAll().size();
        // set the field null
        paragraph.setText(null);

        // Create the Paragraph, which fails.
        ParagraphDTO paragraphDTO = paragraphMapper.paragraphToParagraphDTO(paragraph);

        restParagraphMockMvc.perform(post("/api/paragraphs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paragraphDTO)))
                .andExpect(status().isBadRequest());

        List<Paragraph> paragraphs = paragraphRepository.findAll();
        assertThat(paragraphs).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllParagraphs() throws Exception {
        // Initialize the database
        paragraphRepository.saveAndFlush(paragraph);

        // Get all the paragraphs
        restParagraphMockMvc.perform(get("/api/paragraphs?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(paragraph.getId().intValue())))
                .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT.toString())));
    }

    @Test
    @Transactional
    public void getParagraph() throws Exception {
        // Initialize the database
        paragraphRepository.saveAndFlush(paragraph);

        // Get the paragraph
        restParagraphMockMvc.perform(get("/api/paragraphs/{id}", paragraph.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(paragraph.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingParagraph() throws Exception {
        // Get the paragraph
        restParagraphMockMvc.perform(get("/api/paragraphs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateParagraph() throws Exception {
        // Initialize the database
        paragraphRepository.saveAndFlush(paragraph);

		int databaseSizeBeforeUpdate = paragraphRepository.findAll().size();

        // Update the paragraph
        paragraph.setText(UPDATED_TEXT);
        ParagraphDTO paragraphDTO = paragraphMapper.paragraphToParagraphDTO(paragraph);

        restParagraphMockMvc.perform(put("/api/paragraphs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paragraphDTO)))
                .andExpect(status().isOk());

        // Validate the Paragraph in the database
        List<Paragraph> paragraphs = paragraphRepository.findAll();
        assertThat(paragraphs).hasSize(databaseSizeBeforeUpdate);
        Paragraph testParagraph = paragraphs.get(paragraphs.size() - 1);
        assertThat(testParagraph.getText()).isEqualTo(UPDATED_TEXT);
    }

    @Test
    @Transactional
    public void deleteParagraph() throws Exception {
        // Initialize the database
        paragraphRepository.saveAndFlush(paragraph);

		int databaseSizeBeforeDelete = paragraphRepository.findAll().size();

        // Get the paragraph
        restParagraphMockMvc.perform(delete("/api/paragraphs/{id}", paragraph.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Paragraph> paragraphs = paragraphRepository.findAll();
        assertThat(paragraphs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
