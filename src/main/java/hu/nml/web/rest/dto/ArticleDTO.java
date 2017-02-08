package hu.nml.web.rest.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Article entity.
 */
public class ArticleDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;


    private String leadin;


    @NotNull
    private ZonedDateTime created_at;


    private ZonedDateTime last_modified;


    private Long article_author_userId;
    private Long article_uploadedby_userId;
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
    public ZonedDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(ZonedDateTime created_at) {
        this.created_at = created_at;
    }
    public ZonedDateTime getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(ZonedDateTime last_modified) {
        this.last_modified = last_modified;
    }

    public Long getArticle_author_userId() {
        return article_author_userId;
    }

    public void setArticle_author_userId(Long userId) {
        this.article_author_userId = userId;
    }
    public Long getArticle_uploadedby_userId() {
        return article_uploadedby_userId;
    }

    public void setArticle_uploadedby_userId(Long userId) {
        this.article_uploadedby_userId = userId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticleDTO articleDTO = (ArticleDTO) o;

        if ( ! Objects.equals(id, articleDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", leadin='" + leadin + "'" +
            ", created_at='" + created_at + "'" +
            ", last_modified='" + last_modified + "'" +
            '}';
    }
}
