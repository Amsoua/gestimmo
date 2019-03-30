package com.ag2m.gestimmo.metier.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.ag2m.gestimmo.metier.dao.FactureDao;
import com.ag2m.gestimmo.metier.entite.Facture;
import com.ag2m.gestimmo.metier.exception.FunctionalException;
import com.ag2m.gestimmo.metier.ioparam.FactureCriteria;

@Repository
public class FactureDaoImpl extends AbstractDao<Long, Facture> implements FactureDao {

	/**
	 * Méthde laissée de coté pour motif de doublon avec recherche réservations
	 */
	@Override
	public List<Facture> findFactureByCriteria(FactureCriteria factureCriteria) throws FunctionalException {
		List<Predicate> predicates = new ArrayList<>();
		// Initialiser un builder de requête
		CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<Facture> criteria = criteriaBuilder.createQuery(Facture.class);

		// select from factures
		Root<Facture> factures = criteria.from(Facture.class);
		criteria.select(factures);

		// Conditions de filtre sur les paramètres d'entrée
		if (StringUtils.isNotEmpty(factureCriteria.getNom())) {
			Predicate nomCondition = criteriaBuilder.like(criteriaBuilder.upper(factures.get("client").get("nom")),
					factureCriteria.getNom().toUpperCase());
			predicates.add(nomCondition);
		}
		
		if (StringUtils.isNotEmpty(factureCriteria.getNumero())) {
			Predicate numCondition = criteriaBuilder.like(criteriaBuilder.upper(factures.get("numeroFacture")),
					factureCriteria.getNumero().toUpperCase());
			predicates.add(numCondition);
		}
		if (StringUtils.isNotEmpty(factureCriteria.getPrenom())) {
			Predicate prenomCondition = criteriaBuilder.like(
					criteriaBuilder.upper(factures.get("client").get("prenom")),
					factureCriteria.getPrenom().toUpperCase());
			predicates.add(prenomCondition);
		}
		if (StringUtils.isNotEmpty(factureCriteria.getAdresseEmail())) {
			Predicate emailCondition = criteriaBuilder.like(
					criteriaBuilder.upper(factures.get("client").get("adresseEmail")),
					factureCriteria.getAdresseEmail().toUpperCase());
			predicates.add(emailCondition);
		}
		if (StringUtils.isNotEmpty(factureCriteria.getNumeroPiece())) {
			Predicate numeroCondition = criteriaBuilder.like(
					criteriaBuilder.upper(factures.get("client").get("numeroPieceIdentite")),
					factureCriteria.getNumeroPiece().toUpperCase());
			predicates.add(numeroCondition);
		}
		if (StringUtils.isNotEmpty(factureCriteria.getTypePiece())) {
			Predicate typePieceCondition = criteriaBuilder.like(
					criteriaBuilder.upper(factures.get("client").get("typePieceIdentite")),
					factureCriteria.getTypePiece().toUpperCase());
			predicates.add(typePieceCondition);
		}
		if (StringUtils.isNotEmpty(factureCriteria.getTelephone())) {
			Predicate telCondition = criteriaBuilder.like(
					criteriaBuilder.upper(factures.get("client").get("telephone")),
					factureCriteria.getTelephone().toUpperCase());
			predicates.add(telCondition);
		}

		if (factureCriteria.getStatut() != null) {
			Predicate statutCondition = criteriaBuilder.and(factures.get("statut").
					in(factureCriteria.getStatut()));
			predicates.add(statutCondition);
		}

		if (StringUtils.isNotEmpty(factureCriteria.getLibelle()) && factureCriteria.getLibelle() != null) {
			Join<Object, Object> reservations = factures.join("reservations");
			Join<Object, Object> appartements = reservations.join("appartements");
			Predicate libelleCondition = criteriaBuilder.like(criteriaBuilder.upper(appartements.get("libelle")), 
					factureCriteria.getLibelle().toUpperCase());
			predicates.add(libelleCondition);
		}

		// Date check-in
		if (factureCriteria.getDateCheckin() != null) {
			Join<Object, Object> reservations = factures.join("reservations");
			Predicate dateCheckinCondition = criteriaBuilder.greaterThanOrEqualTo(reservations.get("dateCheckin"),
					factureCriteria.getDateCheckin());
			predicates.add(dateCheckinCondition);
		}
		// Date check-out
		if (factureCriteria.getDateCheckout() != null) {
			Join<Object, Object> reservations = factures.join("reservations");
			Predicate dateCheckoutCondition = criteriaBuilder.and(criteriaBuilder
					.lessThanOrEqualTo(reservations.get("dateCheckin"), factureCriteria.getDateCheckout()));
			predicates.add(dateCheckoutCondition);
		}
		// where
		if (!predicates.isEmpty()) {
			Predicate[] allConditions = predicates.toArray(new Predicate[predicates.size()]);
			criteria.where(allConditions);
		}

		return getCurrentSession().createQuery(criteria).getResultList();
	}

	@Override
	public String findLastNumFacture(){
		
		//Initialiser un builder de requête
		CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
		CriteriaQuery<String> criteria = criteriaBuilder.createQuery(String.class);
		
		//select numeroFacture from Facture
		Root<Facture> facture = criteria.from(Facture.class);
		criteria.select(facture.get("numeroFacture"));
		 
		//Récupérer uniquement la facture avec le plus grand id
		Subquery<Long> sub = criteria.subquery(Long.class);
		Root<Facture> subRoot = sub.from(Facture.class);
		sub.select(criteriaBuilder.max(subRoot.get("id")));
		 
		//condition : retourner le numéro de facture dont l'id correspond au max id
		criteria.where(criteriaBuilder.equal(facture.get("id"), sub));
		return getCurrentSession().createQuery(criteria).uniqueResult();		
	}
	
}
