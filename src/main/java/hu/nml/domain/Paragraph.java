package hu.nml.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Paragraph.
 */
@Entity
@Table(name = "paragraph")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Paragraph implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "text", nullable = false)
    private String text;
    
    @ManyToOne
    @JoinColumn(name = "paragraphs_in_section_id")
    private Section paragraphs_in_section;

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

    public Section getParagraphs_in_section() {
        return paragraphs_in_section;
    }

    public void setParagraphs_in_section(Section section) {
        this.paragraphs_in_section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paragraph paragraph = (Paragraph) o;
        if(paragraph.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paragraph.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Paragraph{" +
            "id=" + id +
            ", text='" + text + "'" +
            '}';
    }
}
