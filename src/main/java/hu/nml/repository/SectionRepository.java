package hu.nml.repository;

import hu.nml.domain.Section;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Section entity.
 */
public interface SectionRepository extends JpaRepository<Section,Long> {

}
