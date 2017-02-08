package hu.nml.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Paragraph entity.
 */
public class ParagraphDTO implements Serializable {

    private Long id;

    @NotNull
    private String text;


    private Long paragraphs_in_sectionId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getParagraphs_in_sectionId() {
        return paragraphs_in_sectionId;
    }

    public void setParagraphs_in_sectionId(Long sectionId) {
        this.paragraphs_in_sectionId = sectionId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParagraphDTO paragraphDTO = (ParagraphDTO) o;

        if ( ! Objects.equals(id, paragraphDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ParagraphDTO{" +
            "id=" + id +
            ", text='" + text + "'" +
            '}';
    }
}
