package edu.tus.winemanager.dao;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.tus.winemanager.dto.Rating;
import edu.tus.winemanager.dto.Wine;



@Repository
public interface WineRepository extends CrudRepository<Wine, Long>{

	Page<Wine> findAll(Pageable pageable);

	Optional<Wine> findByNameAndYear(String name, int year);

	List<Wine> findByCountry(String country);

	List<Wine> findTop3ByRating(Rating rating);

	

}
