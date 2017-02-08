package hu.nml.web.rest.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Section entity.
 */
public class SectionDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;


    private String leadin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getLeadin() {
        return leadin;
    }

    public void setLeadin(String leadin) {
        this.leadin = leadin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SectionDTO sectionDTO = (SectionDTO) o;

        if ( ! Objects.equals(id, sectionDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SectionDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", leadin='" + leadin + "'" +
            '}';
    }
}
