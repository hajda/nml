package hu.nml.web.rest;

import hu.nml.Application;
import hu.nml.domain.Section;
import hu.nml.repository.SectionRepository;
import hu.nml.web.rest.dto.SectionDTO;
import hu.nml.web.rest.mapper.SectionMapper;

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
 * Test class for the SectionResource REST controller.
 *
 * @see SectionResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SectionResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAA";
    private static final String UPDATED_TITLE = "BBBBB";
    private static final String DEFAULT_LEADIN = "AAAAA";
    private static final String UPDATED_LEADIN = "BBBBB";

    @Inject
    private SectionRepository sectionRepository;

    @Inject
    private SectionMapper sectionMapper;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSectionMockMvc;

    private Section section;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SectionResource sectionResource = new SectionResource();
        ReflectionTestUtils.setField(sectionResource, "sectionRepository", sectionRepository);
        ReflectionTestUtils.setField(sectionResource, "sectionMapper", sectionMapper);
        this.restSectionMockMvc = MockMvcBuilders.standaloneSetup(sectionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        section = new Section();
        section.setTitle(DEFAULT_TITLE);
        section.setLeadin(DEFAULT_LEADIN);
    }

    @Test
    @Transactional
    public void createSection() throws Exception {
        int databaseSizeBeforeCreate = sectionRepository.findAll().size();

        // Create the Section
        SectionDTO sectionDTO = sectionMapper.sectionToSectionDTO(section);

        restSectionMockMvc.perform(post("/api/sections")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sectionDTO)))
                .andExpect(status().isCreated());

        // Validate the Section in the database
        List<Section> sections = sectionRepository.findAll();
        assertThat(sections).hasSize(databaseSizeBeforeCreate + 1);
        Section testSection = sections.get(sections.size() - 1);
        assertThat(testSection.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSection.getLeadin()).isEqualTo(DEFAULT_LEADIN);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = sectionRepository.findAll().size();
        // set the field null
        section.setTitle(null);

        // Create the Section, which fails.
        SectionDTO sectionDTO = sectionMapper.sectionToSectionDTO(section);

        restSectionMockMvc.perform(post("/api/sections")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sectionDTO)))
                .andExpect(status().isBadRequest());

        List<Section> sections = sectionRepository.findAll();
        assertThat(sections).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSections() throws Exception {
        // Initialize the database
        sectionRepository.saveAndFlush(section);

        // Get all the sections
        restSectionMockMvc.perform(get("/api/sections?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(section.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].leadin").value(hasItem(DEFAULT_LEADIN.toString())));
    }

    @Test
    @Transactional
    public void getSection() throws Exception {
        // Initialize the database
        sectionRepository.saveAndFlush(section);

        // Get the section
        restSectionMockMvc.perform(get("/api/sections/{id}", section.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(section.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.leadin").value(DEFAULT_LEADIN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSection() throws Exception {
        // Get the section
        restSectionMockMvc.perform(get("/api/sections/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSection() throws Exception {
        // Initialize the database
        sectionRepository.saveAndFlush(section);

		int databaseSizeBeforeUpdate = sectionRepository.findAll().size();

        // Update the section
        section.setTitle(UPDATED_TITLE);
        section.setLeadin(UPDATED_LEADIN);
        SectionDTO sectionDTO = sectionMapper.sectionToSectionDTO(section);

        restSectionMockMvc.perform(put("/api/sections")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sectionDTO)))
                .andExpect(status().isOk());

        // Validate the Section in the database
        List<Section> sections = sectionRepository.findAll();
        assertThat(sections).hasSize(databaseSizeBeforeUpdate);
        Section testSection = sections.get(sections.size() - 1);
        assertThat(testSection.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSection.getLeadin()).isEqualTo(UPDATED_LEADIN);
    }

    @Test
    @Transactional
    public void deleteSection() throws Exception {
        // Initialize the database
        sectionRepository.saveAndFlush(section);

		int databaseSizeBeforeDelete = sectionRepository.findAll().size();

        // Get the section
        restSectionMockMvc.perform(delete("/api/sections/{id}", section.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Section> sections = sectionRepository.findAll();
        assertThat(sections).hasSize(databaseSizeBeforeDelete - 1);
    }
}
