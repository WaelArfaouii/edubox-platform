package com.arfaouiKallebi.JournalWeb.controller.api;
import com.arfaouiKallebi.JournalWeb.dto.InvitationDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("invitations")
public interface InvitationControllerApi {

    @GetMapping(value = "{idRev}/invitations" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvitationDTO> getInvitationsByReviewer(Long idRev);

    @GetMapping(value = "{idEdit}/invitations" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvitationDTO> getInvitationsByEditor(Long idEdit);

    @GetMapping(value = "invitations/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public InvitationDTO getInvitationById(@PathVariable Long id);

    @DeleteMapping(value = "invitations/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteInvitationById(@PathVariable Long id) ;

    @PostMapping(value = "{idEdit}/invitations/save/{idRev}/{idMan}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public InvitationDTO sendInvitation(@PathVariable Long idEdit,@PathVariable Long idRev,@PathVariable Long idMan, @RequestBody InvitationDTO invitation) ;

    @GetMapping(value = "{idRev}/invitations/reviewed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvitationDTO> getinvitationsReviewed(@PathVariable Long idrev ) ;
    @GetMapping(value = "{idRev}/invitations/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InvitationDTO> getPendingInvitations(@PathVariable Long idrev ) ;
}
