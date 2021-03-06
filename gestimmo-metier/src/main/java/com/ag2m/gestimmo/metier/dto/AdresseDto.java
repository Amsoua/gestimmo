/**
 * 
 */
package com.ag2m.gestimmo.metier.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author mombaye
 *
 * Classe représentant un DTO de l'entité Bien
 */
@Getter @Setter @ToString(exclude= {"clients", "biens", "factures"})
public class AdresseDto extends IdentifiantDto implements Serializable{


	private static final long serialVersionUID = -6910515258322033658L;

	private String adresse;
	
	private String complementAdresse;
	
	private Integer codePostal;
	
	private String ville;
	
	private String pays;
	
	@JsonIgnore
	private List<ClientDto> clients;
	
	@JsonIgnore
	private List<BienDto> biens;
	
	@JsonIgnore
	private List<FactureDto> factures;
	
}
