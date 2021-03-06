/**
 * 
 */
package com.ag2m.gestimmo.metier.serviceImpl;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ag2m.gestimmo.metier.dto.AdresseDto;
import com.ag2m.gestimmo.metier.dto.AnomalieDto;
import com.ag2m.gestimmo.metier.dto.AppartementDto;
import com.ag2m.gestimmo.metier.dto.BienDto;
import com.ag2m.gestimmo.metier.dto.ClientDto;
import com.ag2m.gestimmo.metier.dto.DevisDto;
import com.ag2m.gestimmo.metier.dto.FactureDto;
import com.ag2m.gestimmo.metier.dto.ReservationDto;
import com.ag2m.gestimmo.metier.dto.RoleDto;
import com.ag2m.gestimmo.metier.dto.UtilisateurDto;
import com.ag2m.gestimmo.metier.exception.FunctionalException;
import com.ag2m.gestimmo.metier.exception.TechnicalException;
import com.ag2m.gestimmo.metier.mapper.Mapper;
import com.ag2m.gestimmo.metier.service.AdresseService;
import com.ag2m.gestimmo.metier.service.AnomalieService;
import com.ag2m.gestimmo.metier.service.AppartementService;
import com.ag2m.gestimmo.metier.service.BienService;
import com.ag2m.gestimmo.metier.service.ClientService;
import com.ag2m.gestimmo.metier.service.DevisService;
import com.ag2m.gestimmo.metier.service.FactureService;
import com.ag2m.gestimmo.metier.service.ParametrageService;
import com.ag2m.gestimmo.metier.service.ReservationService;
import com.ag2m.gestimmo.metier.service.RoleService;
import com.ag2m.gestimmo.metier.service.TaxeService;
import com.ag2m.gestimmo.metier.service.UtilisateurService;


/**
 * @author mombaye
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/spring-test-config.xml"})
public abstract class AbstractCommonTest {

	@Autowired
	protected BienService bienService;
	
	@Autowired
	protected AppartementService appartementService;
	
	@Autowired
	protected ReservationService reservationService;
	
	@Autowired
	protected AnomalieService anomalieService;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	EhCacheManagerFactoryBean cacheManager;
	
	@Autowired
	protected UtilisateurService utilisateurService;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected AdresseService adresseService;
	
	@Autowired
	protected ClientService clientService;
	
	@Autowired
	protected FactureService factureService;
	
	@Autowired
	protected DevisService devisService;
	
	@Autowired
	protected ParametrageService parametrageService;
	
	@Autowired
	protected TaxeService taxeService;
	
	@Autowired
	protected BCryptPasswordEncoder passwordEncoder;
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	
	/**
	 * Permet de créer un Bien 
	 * 
	 * @param libelle
	 * @param adresse
	 * @return
	 * @throws FunctionalException 
	 * @throws TechnicalException 
	 */
	protected BienDto createBien(String libelle, AdresseDto adresse) throws TechnicalException {
	
		BienDto bien = new BienDto();
		bien.setLibelle(libelle);
		bien.setAdresse(adresse);
		
		return bien;
	}
	
	
	/**
	 * Permet de créer une adresse
	 * 
	 * @param adresse
	 * @param complementAdresse
	 * @param codePostal
	 * @param ville
	 * @param pays
	 * 
	 * @return
	 */
	protected AdresseDto createAdresse(String adresse, String complementAdresse,
			Integer codePostal,  String ville, String pays) {
		
		AdresseDto adr = initAdresse(adresse, complementAdresse, codePostal, ville, pays);
		
		return adr;
		
	}


	/**
	 * @param adresse
	 * @param complementAdresse
	 * @param codePostal
	 * @param ville
	 * @param pays
	 * @return
	 */
	protected AdresseDto initAdresse(String adresse, String complementAdresse, Integer codePostal, String ville,
			String pays) {
		AdresseDto adr = new AdresseDto();
		adr.setAdresse(adresse);
		adr.setCodePostal(codePostal);
		adr.setComplementAdresse(complementAdresse);
		adr.setVille(ville);
		adr.setPays(pays);
		return adr;
	}
	
	
	/**
	 * Permet de créer un client
	 * 
	 * @param nom
	 * @param prenom
	 * @param adresseEmail
	 * @param numeroPieceIdentite
	 * @param typePieceIdentite
	 * @param telephone
	 * @param adresse
	 * @return
	 * @throws FunctionalException 
	 * @throws TechnicalException 
	 */
	protected ClientDto createClient(String nom, String prenom, String adresseEmail,
			String numeroPieceIdentite, String typePieceIdentite, String telephone,
			AdresseDto adresse) throws TechnicalException {
		
		ClientDto client = initClient(nom, prenom, adresseEmail, numeroPieceIdentite, typePieceIdentite, telephone,
				adresse);
		
		client = clientService.createClient(client);
		
		return client;
	}


	/**
	 * @param nom
	 * @param prenom
	 * @param adresseEmail
	 * @param numeroPieceIdentite
	 * @param typePieceIdentite
	 * @param telephone
	 * @param adresse
	 * @return
	 */
	protected ClientDto initClient(String nom, String prenom, String adresseEmail, String numeroPieceIdentite,
			String typePieceIdentite, String telephone, AdresseDto adresse) {
		ClientDto client = new ClientDto();
		client.setAdresse(adresse);
		client.setAdresseEmail(adresseEmail);
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setTelephone(telephone);
		client.setNumeroPieceIdentite(numeroPieceIdentite);
		client.setTypePieceIdentite(typePieceIdentite);
		return client;
	}
	
	
	/**
	 * Permet de créer une Facture
	 * 
	 * @param client
	 * @param taxeSejour
	 * @param adresseFacturation
	 * @param tva
	 * @param remise
	 * @return
	 * @throws TechnicalException 
	 * @throws FunctionalException 
	 */
	protected FactureDto createFacture(ClientDto client, Double taxeSejour,
			AdresseDto adresseFacturation, Double tva, Double remise) throws FunctionalException, TechnicalException {
		
		FactureDto facture = initFacture(client, taxeSejour, adresseFacturation, tva, remise);
		
		facture = factureService.createFacture(facture);
		
		return facture;
		
	}


	/**
	 * Initialise un nouvel objet facture
	 * 
	 * @param client
	 * @param taxeSejour
	 * @param adresseFacturation
	 * @param tva
	 * @param remise
	 * @return
	 * @throws TechnicalException 
	 * @throws FunctionalException 
	 */
	protected FactureDto initFacture(ClientDto client, Double taxeSejour, AdresseDto adresseFacturation, Double tva,
			Double remise) throws FunctionalException, TechnicalException {
		FactureDto facture = new FactureDto();
		facture.setAdresseFacturation(adresseFacturation);
		facture.setClient(client);
		facture.setRemise(remise);
		facture.setTaxeSejour(taxeSejour);
		facture.setDateCreation(LocalDateTime.now());
		facture.setTva(tva);
		
		facture = factureService.createFacture(facture);
		
		return facture;
	}
	
	
	/**
	 * Permet de créer un Devis en BDD 
	 * 
	 * @param nom
	 * @param prenom
	 * @param adresseEmail
	 * @param telephone
	 * @param numeroDevis
	 * @param dateChekin
	 * @param dateCheckout
	 * @param facture
	 * @return
	 * @throws TechnicalException 
	 */
	protected DevisDto createDevis(String nom, String prenom, String adresseEmail,
			String telephone, LocalDateTime dateChekin, LocalDateTime dateCheckout, FactureDto facture) throws TechnicalException {
		
		DevisDto devis = new DevisDto();
		devis.setAdresseEmail(adresseEmail);
		devis.setNom(nom);
		devis.setPrenom(prenom);
		devis.setTelephone(telephone);
		devis.setDateCheckin(dateChekin);
		devis.setDateCheckout(dateCheckout);
		devis.setDateCreation(LocalDateTime.now());
		
		devis.setFacture(facture);
		
		devis = devisService.createDevis(devis);
		
		return devis;
		
	}
	
	/**
	 * Permet de créer un appartement
	 * 
	 * @param libelle
	 * @param bien
	 * @param type
	 * @param prix
	 * @return
	 * @throws FunctionalException 
	 */
	protected AppartementDto createAppartement(String libelle, BienDto bien, String type, Double prix) throws TechnicalException {
		
		AppartementDto appartement = new AppartementDto();
		appartement.setLibelle(libelle);
		appartement.setBien(bien);
		appartement.setType(type);
		appartement.setPrix(prix);
		
		appartement = appartementService.createAppartement(appartement);
		
		return appartement;
	}
	
	
	/**
	 * Permet de créer une réservation
	 * 
	 * @param dateCheckin
	 * @param dateCheckout
	 * @param note
	 * @param petitDej
	 * @param statut
	 * @param appartements
	 * @param prix
	 * @param dateCreation
	 * @param dateAnnulation
	 * @param client
	 * @param facture
	 * @return
	 * @throws TechnicalException 
	 * @throws FunctionalException 
	 */
	protected ReservationDto createReservation(LocalDateTime dateCheckin, LocalDateTime dateCheckout, 
			String note, Boolean petitDej, String statut, List<AppartementDto> appartements, 
			Double prix, LocalDateTime dateCreation, LocalDateTime dateAnnulation, ClientDto client,
			FactureDto facture) throws TechnicalException, FunctionalException{
		
		final ReservationDto reservation = new ReservationDto();
		
		reservation.setDateCheckin(dateCheckin);
		reservation.setDateCheckout(dateCheckout);
		reservation.setNote(note);
		reservation.setPetitDej(petitDej);
		reservation.setStatut(statut);
		reservation.setDateCheckin(dateCheckin);
		reservation.setDateCheckout(dateCheckout);
		reservation.setDateCreation(dateCreation);
		reservation.setFacture(facture);
		reservation.setPrix(prix);
		reservation.setClient(client);
		reservation.setFacture(facture);
		reservation.setDateAnnulation(dateAnnulation);
		reservation.setAppartements(appartements);
		
		return reservationService.createReservation(reservation); 
		
	}
	
	/**
	 * Permet de créer une anomalie
	 * 
	 * @param appartement
	 * @param commentaire
	 * @param dateOuverture
	 * @param dateTraitement
	 * @param description
	 * @param statut
	 * @param titre
	 * @return
	 * @throws FunctionalException 
	 */
	protected AnomalieDto createAnomalie(AppartementDto appartement, String commentaire, 
			LocalDateTime dateOuverture, LocalDateTime dateTraitement, 
			String description, String statut, String titre) throws FunctionalException{
		
		AnomalieDto anomalie = new AnomalieDto();
		anomalie.setAppartement(appartement);
		anomalie.setCommentaire(commentaire);
		anomalie.setDateOuverture(dateOuverture);
		anomalie.setDateTraitement(dateTraitement);
		anomalie.setDescription(description);
		anomalie.setStatut(statut);
		anomalie.setTitre(titre);
		
		anomalie = anomalieService.createAnomalie(anomalie);
		 return anomalie;
	}
	
	/**
	 * Permet de créer un utilisateur.
	 * 
	 * @param username
	 * @param password
	 * @param enabled
	 * @param roles
	 * @return
	 */
	protected UtilisateurDto createUtilisateur(String username, String password, String nom, 
			String prenom, String adresseEmail, boolean enabled) {
		
		UtilisateurDto utilisateur = new UtilisateurDto();
		String encodedPassword = passwordEncoder.encode(password);
		utilisateur.setPassword(encodedPassword);
		utilisateur.setUsername(username);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setAdresseEmail(adresseEmail);
		utilisateur.setEnabled(enabled);
		
		utilisateur = utilisateurService.saveOrUpdate(utilisateur);
		return utilisateur;
	}
	
	/**
	 * Permet de créer un Role.
	 * 
	 * @param role
	 * @param utilisateur
	 * @return
	 */
	protected RoleDto createRole(String role, UtilisateurDto utilisateur) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRole(role);
		roleDto.setUtilisateur(utilisateur);
		
		roleDto = roleService.saveOrUpdate(roleDto);
		return roleDto;
	}
	
}
