package com.arfaouiKallebi.JournalWeb.services;

import com.arfaouiKallebi.JournalWeb.dto.InvitationDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvitationService {
    List<InvitationDTO> findAllByReviewer(Long idRev) ;
    List<InvitationDTO> findAllByEditor(Long idEdit) ;
    InvitationDTO findById(Long id) ;
    ResponseEntity<?> deleteById(Long id) ;

    InvitationDTO sendInvitation(Long idedit, Long idrev, Long idman, InvitationDTO invitation);

    List<InvitationDTO> getInvitationsReviewed(Long idrev);

    List<InvitationDTO> getPendingInvitations(Long idrev);
}
