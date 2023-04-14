package edu.tus.winemanager.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import edu.tus.winemanager.dao.WineRepository;
import edu.tus.winemanager.dto.Rating;
import edu.tus.winemanager.dto.Wine;
import edu.tus.winemanager.exception.WineException;
import edu.tus.winemanager.exception.WineNotFoundException;
import edu.tus.winemanager.validation.ErrorMessage;
import edu.tus.winemanager.validation.WineValidator;


@RestController
@Service
public class WineService {
	
	@Autowired
	WineRepository wineRepo;
	
	@Autowired
	WineValidator wineValidator;
	
	
	@GetMapping("/wines")
	public Page<Wine> findAllWines(@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="size",defaultValue="20") int size, @RequestParam(value="sort",defaultValue="name")  String sort){
		PageRequest pageable = PageRequest.of(page, size,Sort.by(sort));
		Page<Wine> pageWine = wineRepo.findAll(pageable);
		return pageWine;	
	}
	
	@GetMapping("/wines/rating/{rating}")
	List<Wine> getWineQueryId(@PathVariable("rating") Rating rating) {
		List<Wine> wines=wineRepo.findTop3ByRating(rating);
		return wines;
	}
	
	
	@RequestMapping("/wines/{id}")
	Optional<Wine> getWineById(@PathVariable("id") Long id) {
		Optional<Wine> wine = wineRepo.findById(id);
		if (wine.isPresent()) {
			return wine;
		} else {
			throw new WineNotFoundException("No wine with id: " + id);
		}

	}
	
	@PostMapping("/wines")
	ResponseEntity createWine(@Valid @RequestBody Wine wine) {
		try {	
		wineValidator.validateWine(wine);
		Wine savedWine=wineRepo.save(wine);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("{id}")
				.buildAndExpand(savedWine.getId()).toUri();
		return ResponseEntity.created(location).build();
		}catch(WineException e) {
			ErrorMessage errorMessage=new ErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}
	
	

	


}
