package com.ag2m.gestimmo.webapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ag2m.gestimmo.metier.constants.TechnicalErrorMessageConstants;
import com.ag2m.gestimmo.metier.dto.AppartementDto;
import com.ag2m.gestimmo.metier.exception.TechnicalException;
import com.ag2m.gestimmo.metier.service.AppartementService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AppartementController {

	@Autowired
	private AppartementService appartementService;

	/**
	 * Retourne tous les appartements sous format Json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appartements", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AppartementDto> findAllAppartement() {
		List<AppartementDto> appartements = appartementService.loadAllAppartement();
		return appartements;
	}

	/**
	 * Retourne tous les appartements sous format Json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appartements/recherche", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AppartementDto> findByCriteria(@RequestParam String libelle, @RequestParam String type,
			@RequestParam Long idBien) {
		List<AppartementDto> appartements = appartementService.findAppartementByCriteria(libelle, type, idBien);
		return appartements;
	}

	/**
	 * Retourne tous les appartements d'une réservation sous format Json
	 * 
	 * @return
	 */
	@RequestMapping(value = "/appartements/reservation/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AppartementDto> findAllAppartementOfReservation(@PathVariable("id") long id) {
		List<AppartementDto> appartements = appartementService.findAppartByReservation(id);
		return appartements;
	}

	/**
	 * Retourne l'appartement dont l'id est en paramètre, sous format Json
	 * 
	 * @return
	 * @throws TechnicalException
	 */
	@RequestMapping(value = "/appartements/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AppartementDto getAppartement(@PathVariable("id") long id) throws TechnicalException {

		AppartementDto appartement = appartementService.findAppartementById(id);

		return appartement;
	}

	/**
	 * Crée un nouvel appartement
	 * @throws TechnicalException 
	 * 
	 */
	@RequestMapping(value = "/appartements/create", method = RequestMethod.POST)
	public @ResponseBody AppartementDto saveAppartement(@RequestBody AppartementDto appartementDto) throws TechnicalException  {

		Optional.ofNullable(appartementDto).orElseThrow(() 
				-> new TechnicalException(TechnicalErrorMessageConstants.ERREUR_ENTREE_SUPP_NULL));
		
		return appartementService.createAppartement(appartementDto);

	}

	/**
	 * supprime l'appartement dont l'id est en paramètre
	 * 
	 * @throws TechnicalException
	 * 
	 */
	@RequestMapping(value = "/appartements/delete/id/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteAppartement(@PathVariable("id") long id) throws TechnicalException {
		AppartementDto appartement = appartementService.findAppartementById(id);
		appartementService.deleteAppartement(appartement);

	}
}
