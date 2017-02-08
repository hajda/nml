package hu.nml.repository;

import hu.nml.domain.Article;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Article entity.
 */
public interface ArticleRepository extends JpaRepository<Article,Long> {

    @Query("select article from Article article where article.article_author_user.login = ?#{principal.username}")
    List<Article> findByArticle_author_userIsCurrentUser();

    @Query("select article from Article article where article.article_uploadedby_user.login = ?#{principal.username}")
    List<Article> findByArticle_uploadedby_userIsCurrentUser();

}
