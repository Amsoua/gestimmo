package com.ag2m.gestimmo.metier.ioparam;

import java.util.List;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author asouane
 * Classe finaleent non utilisée ais a conserver au cas.
 * Classe contenant les critères de recherche 
 * d'appartements
 */
@Getter @Setter
@ToString
public class FactureCriteria {

	private String nom;
	private String prenom;
	private String adresseEmail;
	private String numeroPiece;
	private String typePiece;
	private String telephone;
	private List<String> statut;
	private String libelle;
	private LocalDateTime dateCheckin;  
	private LocalDateTime dateCheckout; 
	private String numero;
}
