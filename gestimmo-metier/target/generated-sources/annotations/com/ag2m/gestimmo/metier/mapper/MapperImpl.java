package com.ag2m.gestimmo.metier.mapper;

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
import com.ag2m.gestimmo.metier.entite.Adresse;
import com.ag2m.gestimmo.metier.entite.Anomalie;
import com.ag2m.gestimmo.metier.entite.Appartement;
import com.ag2m.gestimmo.metier.entite.Bien;
import com.ag2m.gestimmo.metier.entite.Client;
import com.ag2m.gestimmo.metier.entite.Devis;
import com.ag2m.gestimmo.metier.entite.Facture;
import com.ag2m.gestimmo.metier.entite.Reservation;
import com.ag2m.gestimmo.metier.entite.Role;
import com.ag2m.gestimmo.metier.entite.Utilisateur;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-19T15:27:02+0000",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_74 (Oracle Corporation)"
)
@Component
public class MapperImpl implements Mapper {

    @Override
    public UtilisateurDto utilisateurToUtilisateurDto(Utilisateur utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        UtilisateurDto utilisateurDto = new UtilisateurDto();

        utilisateurDto.setId( utilisateur.getId() );
        utilisateurDto.setUsername( utilisateur.getUsername() );
        utilisateurDto.setPassword( utilisateur.getPassword() );
        utilisateurDto.setEnabled( utilisateur.isEnabled() );
        utilisateurDto.setNom( utilisateur.getNom() );
        utilisateurDto.setPrenom( utilisateur.getPrenom() );
        utilisateurDto.setAdresseEmail( utilisateur.getAdresseEmail() );

        processRoleForUtilisateurDto( utilisateurDto );

        return utilisateurDto;
    }

    @Override
    public Utilisateur utilisateurDtoToUtilisateur(UtilisateurDto utilisateur) {
        if ( utilisateur == null ) {
            return null;
        }

        Utilisateur utilisateur1 = new Utilisateur();

        utilisateur1.setId( utilisateur.getId() );
        utilisateur1.setUsername( utilisateur.getUsername() );
        utilisateur1.setPassword( utilisateur.getPassword() );
        utilisateur1.setEnabled( utilisateur.isEnabled() );
        utilisateur1.setNom( utilisateur.getNom() );
        utilisateur1.setPrenom( utilisateur.getPrenom() );
        utilisateur1.setAdresseEmail( utilisateur.getAdresseEmail() );

        processRoleForUtilisateur( utilisateur1 );

        return utilisateur1;
    }

    @Override
    public RoleDto roleToRoleDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setRole( role.getRole() );
        roleDto.setUtilisateur( utilisateurToUtilisateurDto( role.getUtilisateur() ) );

        return roleDto;
    }

    @Override
    public Role roleDtoToRole(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        role.setRole( roleDto.getRole() );
        role.setUtilisateur( utilisateurDtoToUtilisateur( roleDto.getUtilisateur() ) );

        return role;
    }

    @Override
    public DevisDto devisToDevisDto(Devis devis) {
        if ( devis == null ) {
            return null;
        }

        DevisDto devisDto = new DevisDto();

        devisDto.setId( devis.getId() );
        devisDto.setNom( devis.getNom() );
        devisDto.setPrenom( devis.getPrenom() );
        devisDto.setAdresseEmail( devis.getAdresseEmail() );
        devisDto.setTelephone( devis.getTelephone() );
        try {
            devisDto.setFacture( processFactureForDevisDto( devis.getFacture() ) );
        }
        catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        devisDto.setNumeroDevis( devis.getNumeroDevis() );
        devisDto.setDateCheckin( devis.getDateCheckin() );
        devisDto.setDateCheckout( devis.getDateCheckout() );
        devisDto.setDateCreation( devis.getDateCreation() );

        return devisDto;
    }

    @Override
    public Devis devisDtoToDevis(DevisDto devisDto) {
        if ( devisDto == null ) {
            return null;
        }

        Devis devis = new Devis();

        devis.setId( devisDto.getId() );
        devis.setNom( devisDto.getNom() );
        devis.setPrenom( devisDto.getPrenom() );
        devis.setAdresseEmail( devisDto.getAdresseEmail() );
        devis.setTelephone( devisDto.getTelephone() );
        devis.setNumeroDevis( devisDto.getNumeroDevis() );
        devis.setDateCheckin( devisDto.getDateCheckin() );
        devis.setDateCheckout( devisDto.getDateCheckout() );
        devis.setDateCreation( devisDto.getDateCreation() );
        try {
            devis.setFacture( processFactureForDevis( devisDto.getFacture() ) );
        }
        catch ( JsonProcessingException e ) {
            throw new RuntimeException( e );
        }

        return devis;
    }

    @Override
    public ClientDto clientToClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto clientDto = new ClientDto();

        clientDto.setId( client.getId() );
        clientDto.setNom( client.getNom() );
        clientDto.setPrenom( client.getPrenom() );
        clientDto.setAdresseEmail( client.getAdresseEmail() );
        clientDto.setNumeroPieceIdentite( client.getNumeroPieceIdentite() );
        clientDto.setTypePieceIdentite( client.getTypePieceIdentite() );
        clientDto.setTelephone( client.getTelephone() );
        clientDto.setAdresse( adresseToAdresseDto( client.getAdresse() ) );

        processReservationForClientDto( clientDto );

        return clientDto;
    }

    @Override
    public Client clientDtoToClient(ClientDto client) {
        if ( client == null ) {
            return null;
        }

        Client client1 = new Client();

        client1.setId( client.getId() );
        client1.setNom( client.getNom() );
        client1.setPrenom( client.getPrenom() );
        client1.setAdresseEmail( client.getAdresseEmail() );
        client1.setNumeroPieceIdentite( client.getNumeroPieceIdentite() );
        client1.setTypePieceIdentite( client.getTypePieceIdentite() );
        client1.setTelephone( client.getTelephone() );
        client1.setAdresse( adresseDtoToAdresse( client.getAdresse() ) );

        processReservationForClient( client1 );

        return client1;
    }

    @Override
    public AdresseDto adresseToAdresseDto(Adresse adresse) {
        if ( adresse == null ) {
            return null;
        }

        AdresseDto adresseDto = new AdresseDto();

        adresseDto.setId( adresse.getId() );
        adresseDto.setAdresse( adresse.getAdresse() );
        adresseDto.setComplementAdresse( adresse.getComplementAdresse() );
        adresseDto.setCodePostal( adresse.getCodePostal() );
        adresseDto.setVille( adresse.getVille() );
        adresseDto.setPays( adresse.getPays() );

        processClientAndBienForAdressetDto( adresseDto );

        return adresseDto;
    }

    @Override
    public Adresse adresseDtoToAdresse(AdresseDto adresse) {
        if ( adresse == null ) {
            return null;
        }

        Adresse adresse1 = new Adresse();

        adresse1.setId( adresse.getId() );
        adresse1.setAdresse( adresse.getAdresse() );
        adresse1.setComplementAdresse( adresse.getComplementAdresse() );
        adresse1.setCodePostal( adresse.getCodePostal() );
        adresse1.setVille( adresse.getVille() );
        adresse1.setPays( adresse.getPays() );

        processClientAndBienForAdresse( adresse1 );

        return adresse1;
    }

    @Override
    public FactureDto factureToFactureDto(Facture facture) {
        if ( facture == null ) {
            return null;
        }

        FactureDto factureDto = new FactureDto();

        factureDto.setId( facture.getId() );
        factureDto.setClient( clientToClientDto( facture.getClient() ) );
        factureDto.setTaxeSejour( facture.getTaxeSejour() );
        factureDto.setAdresseFacturation( adresseToAdresseDto( facture.getAdresseFacturation() ) );
        factureDto.setTva( facture.getTva() );
        factureDto.setRemise( facture.getRemise() );
        factureDto.setNumeroFacture( facture.getNumeroFacture() );
        factureDto.setDateCreation( facture.getDateCreation() );

        processReservationForFactureDto( factureDto );

        return factureDto;
    }

    @Override
    public Facture factureDtoToFacture(FactureDto facture) {
        if ( facture == null ) {
            return null;
        }

        Facture facture1 = new Facture();

        facture1.setId( facture.getId() );
        facture1.setClient( clientDtoToClient( facture.getClient() ) );
        facture1.setTaxeSejour( facture.getTaxeSejour() );
        facture1.setAdresseFacturation( adresseDtoToAdresse( facture.getAdresseFacturation() ) );
        facture1.setTva( facture.getTva() );
        facture1.setRemise( facture.getRemise() );
        facture1.setNumeroFacture( facture.getNumeroFacture() );
        facture1.setDateCreation( facture.getDateCreation() );

        processReservationForFacture( facture1 );

        return facture1;
    }

    @Override
    public BienDto bienToBienDto(Bien bien) {
        if ( bien == null ) {
            return null;
        }

        BienDto bienDto = new BienDto();

        bienDto.setId( bien.getId() );
        bienDto.setLibelle( bien.getLibelle() );
        bienDto.setAdresse( adresseToAdresseDto( bien.getAdresse() ) );

        processAppartementForBienDto( bienDto );

        return bienDto;
    }

    @Override
    public Bien bienDtoToBien(BienDto bien) {
        if ( bien == null ) {
            return null;
        }

        Bien bien1 = new Bien();

        bien1.setId( bien.getId() );
        bien1.setLibelle( bien.getLibelle() );
        bien1.setAdresse( adresseDtoToAdresse( bien.getAdresse() ) );

        processAppartementForBien( bien1 );

        return bien1;
    }

    @Override
    public AppartementDto appartementToAppartementDto(Appartement appartement) {
        if ( appartement == null ) {
            return null;
        }

        AppartementDto appartementDto = new AppartementDto();

        appartementDto.setId( appartement.getId() );
        appartementDto.setLibelle( appartement.getLibelle() );
        appartementDto.setType( appartement.getType() );
        appartementDto.setBien( bienToBienDto( appartement.getBien() ) );
        appartementDto.setPrix( appartement.getPrix() );
        appartementDto.setReservations( reservationListToReservationDtoList( appartement.getReservations() ) );

        processAnomalieForAppartDto( appartementDto );
        mapAppartIntoResaDto( appartementDto );

        return appartementDto;
    }

    @Override
    public Appartement appartementDtoToAppartement(AppartementDto appartementDto) {
        if ( appartementDto == null ) {
            return null;
        }

        Appartement appartement = new Appartement();

        appartement.setId( appartementDto.getId() );
        appartement.setLibelle( appartementDto.getLibelle() );
        appartement.setType( appartementDto.getType() );
        appartement.setBien( bienDtoToBien( appartementDto.getBien() ) );
        appartement.setPrix( appartementDto.getPrix() );
        appartement.setReservations( reservationDtoListToReservationList( appartementDto.getReservations() ) );

        processAnomalieForAppart( appartement );
        mapAppartIntoResa( appartement );

        return appartement;
    }

    @Override
    public AnomalieDto anomalieToAnomalieDto(Anomalie anomalie) {
        if ( anomalie == null ) {
            return null;
        }

        AnomalieDto anomalieDto = new AnomalieDto();

        anomalieDto.setId( anomalie.getId() );
        anomalieDto.setTitre( anomalie.getTitre() );
        anomalieDto.setDescription( anomalie.getDescription() );
        anomalieDto.setStatut( anomalie.getStatut() );
        anomalieDto.setDateOuverture( anomalie.getDateOuverture() );
        anomalieDto.setDateTraitement( anomalie.getDateTraitement() );
        anomalieDto.setAppartement( appartementToAppartementDto( anomalie.getAppartement() ) );
        anomalieDto.setCommentaire( anomalie.getCommentaire() );

        return anomalieDto;
    }

    @Override
    public Anomalie anomalieDtoToAnomalie(AnomalieDto anomalieDto) {
        if ( anomalieDto == null ) {
            return null;
        }

        Anomalie anomalie = new Anomalie();

        anomalie.setId( anomalieDto.getId() );
        anomalie.setTitre( anomalieDto.getTitre() );
        anomalie.setDescription( anomalieDto.getDescription() );
        anomalie.setStatut( anomalieDto.getStatut() );
        anomalie.setDateOuverture( anomalieDto.getDateOuverture() );
        anomalie.setDateTraitement( anomalieDto.getDateTraitement() );
        anomalie.setAppartement( appartementDtoToAppartement( anomalieDto.getAppartement() ) );
        anomalie.setCommentaire( anomalieDto.getCommentaire() );

        return anomalie;
    }

    @Override
    public ReservationDto reservationToReservationDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setId( reservation.getId() );
        reservationDto.setDateCheckin( reservation.getDateCheckin() );
        reservationDto.setDateCheckout( reservation.getDateCheckout() );
        reservationDto.setNote( reservation.getNote() );
        reservationDto.setPetitDej( reservation.getPetitDej() );
        reservationDto.setStatut( reservation.getStatut() );
        reservationDto.setPrix( reservation.getPrix() );
        reservationDto.setDateCreation( reservation.getDateCreation() );
        reservationDto.setDateAnnulation( reservation.getDateAnnulation() );
        reservationDto.setClient( clientToClientDto( reservation.getClient() ) );
        reservationDto.setFacture( factureToFactureDto( reservation.getFacture() ) );

        return reservationDto;
    }

    @Override
    public Reservation reservationDtoToReservation(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( reservationDto.getId() );
        reservation.setDateCheckin( reservationDto.getDateCheckin() );
        reservation.setDateCheckout( reservationDto.getDateCheckout() );
        reservation.setNote( reservationDto.getNote() );
        reservation.setPetitDej( reservationDto.getPetitDej() );
        reservation.setStatut( reservationDto.getStatut() );
        reservation.setPrix( reservationDto.getPrix() );
        reservation.setDateCreation( reservationDto.getDateCreation() );
        reservation.setDateAnnulation( reservationDto.getDateAnnulation() );
        reservation.setAppartements( appartementDtoListToAppartementList( reservationDto.getAppartements() ) );
        reservation.setClient( clientDtoToClient( reservationDto.getClient() ) );
        reservation.setFacture( factureDtoToFacture( reservationDto.getFacture() ) );

        processAppartementForReservation( reservation );

        return reservation;
    }

    protected List<ReservationDto> reservationListToReservationDtoList(List<Reservation> list) {
        if ( list == null ) {
            return null;
        }

        List<ReservationDto> list1 = new ArrayList<ReservationDto>( list.size() );
        for ( Reservation reservation : list ) {
            list1.add( reservationToReservationDto( reservation ) );
        }

        return list1;
    }

    protected List<Reservation> reservationDtoListToReservationList(List<ReservationDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Reservation> list1 = new ArrayList<Reservation>( list.size() );
        for ( ReservationDto reservationDto : list ) {
            list1.add( reservationDtoToReservation( reservationDto ) );
        }

        return list1;
    }

    protected List<Appartement> appartementDtoListToAppartementList(List<AppartementDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Appartement> list1 = new ArrayList<Appartement>( list.size() );
        for ( AppartementDto appartementDto : list ) {
            list1.add( appartementDtoToAppartement( appartementDto ) );
        }

        return list1;
    }
}
