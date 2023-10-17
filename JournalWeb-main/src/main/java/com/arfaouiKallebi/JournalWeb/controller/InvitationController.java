package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.InvitationControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.InvitationDTO;
import com.arfaouiKallebi.JournalWeb.services.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvitationController implements InvitationControllerApi {

    @Autowired
    private InvitationService invitationService ;

    @Override
    public List<InvitationDTO> getInvitationsByReviewer(Long idRev) {
        return invitationService.findAllByReviewer(idRev);
    }

    @Override
    public List<InvitationDTO> getInvitationsByEditor(Long idEdit) {
        return invitationService.findAllByEditor(idEdit);
    }

    @Override
    public InvitationDTO getInvitationById(Long id) {
        return invitationService.findById(id);
    }

    @Override
    public ResponseEntity<?> deleteInvitationById(Long id) {
        return invitationService.deleteById(id);
    }

    @Override
    public InvitationDTO sendInvitation(@PathVariable Long idEdit,@PathVariable Long idRev,@PathVariable Long idMan, @RequestBody InvitationDTO invitation){
        return invitationService.sendInvitation(idEdit, idRev,idMan,invitation);
    }


    @Override
    public List<InvitationDTO> getinvitationsReviewed(@PathVariable Long idrev ){
        return invitationService.getInvitationsReviewed(idrev) ;
    }

    @Override
    public List<InvitationDTO> getPendingInvitations(@PathVariable Long idrev ){
        return invitationService.getPendingInvitations(idrev) ;
    }
}
