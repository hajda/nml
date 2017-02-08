package hu.nml.web.rest.mapper;

import hu.nml.domain.*;
import hu.nml.web.rest.dto.ArticleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Article and its DTO ArticleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArticleMapper {

    @Mapping(source = "article_author_user.id", target = "article_author_userId")
    @Mapping(source = "article_uploadedby_user.id", target = "article_uploadedby_userId")
    ArticleDTO articleToArticleDTO(Article article);

    @Mapping(source = "article_author_userId", target = "article_author_user")
    @Mapping(source = "article_uploadedby_userId", target = "article_uploadedby_user")
    Article articleDTOToArticle(ArticleDTO articleDTO);

    default User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
