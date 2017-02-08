package hu.nml.repository;

import hu.nml.domain.Paragraph;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Paragraph entity.
 */
public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {

}
