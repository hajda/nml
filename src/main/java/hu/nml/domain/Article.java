package hu.nml.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Article.
 */
@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "leadin")
    private String leadin;
    
    @NotNull
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime created_at;
    
    @Column(name = "last_modified")
    private ZonedDateTime last_modified;
    
    @ManyToOne
    @JoinColumn(name = "article_author_user_id")
    private User article_author_user;

    @ManyToOne
    @JoinColumn(name = "article_uploadedby_user_id")
    private User article_uploadedby_user;

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

    public User getArticle_author_user() {
        return article_author_user;
    }

    public void setArticle_author_user(User user) {
        this.article_author_user = user;
    }

    public User getArticle_uploadedby_user() {
        return article_uploadedby_user;
    }

    public void setArticle_uploadedby_user(User user) {
        this.article_uploadedby_user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        if(article.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Article{" +
            "id=" + id +
            ", title='" + title + "'" +
            ", leadin='" + leadin + "'" +
            ", created_at='" + created_at + "'" +
            ", last_modified='" + last_modified + "'" +
            '}';
    }
}
