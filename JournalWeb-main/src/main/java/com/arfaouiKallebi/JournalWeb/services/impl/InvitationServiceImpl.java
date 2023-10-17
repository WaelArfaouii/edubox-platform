package com.arfaouiKallebi.JournalWeb.services.impl;
import com.arfaouiKallebi.JournalWeb.dto.*;
import com.arfaouiKallebi.JournalWeb.exception.EntityNotFoundException;
import com.arfaouiKallebi.JournalWeb.exception.ErrorCodes;
import com.arfaouiKallebi.JournalWeb.exception.InvalidEntityException;
import com.arfaouiKallebi.JournalWeb.model.Author;
import com.arfaouiKallebi.JournalWeb.model.Invitation;
import com.arfaouiKallebi.JournalWeb.repository.EditorRepository;
import com.arfaouiKallebi.JournalWeb.repository.InvitationRepository;
import com.arfaouiKallebi.JournalWeb.repository.ManuscriptRepository;
import com.arfaouiKallebi.JournalWeb.repository.ReviewerRepository;
import com.arfaouiKallebi.JournalWeb.services.EditorService;
import com.arfaouiKallebi.JournalWeb.services.InvitationService;
import com.arfaouiKallebi.JournalWeb.services.ManuscriptService;
import com.arfaouiKallebi.JournalWeb.services.ReviewerService;
import com.arfaouiKallebi.JournalWeb.validator.InvitationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//Logger
@Slf4j
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    private InvitationRepository invitationRepository ;
    @Autowired
    private EditorService editorService ;
    @Autowired
    private ReviewerService reviewerService ;
    @Autowired
    private ManuscriptService manuscriptService ;

    @Override
    public List<InvitationDTO> findAllByReviewer(Long idRev) {
        return invitationRepository.getInvByReviewer(idRev).stream()
                .map(InvitationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvitationDTO> findAllByEditor(Long idEdit) {
        return invitationRepository.getInvByEditor(idEdit).stream()
                .map(InvitationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public InvitationDTO findById(Long id) {
        if (id == null) {
            log.error("Invitation ID is null");
            return null;
        }
        return invitationRepository.findById(id)
                .map(InvitationDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Invitation with id : = " + id + " exists",
                        ErrorCodes.MANUSCRIPT_NOT_FOUND)
                );
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {

        invitationRepository.deleteById(id);
        return new ResponseEntity<>("Invitation deleted" , HttpStatus.OK) ;
    }

    @Override
    public InvitationDTO sendInvitation(Long idedit, Long idrev, Long idman, InvitationDTO dto) {
        List<String> errors = InvitationValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Invitation is not valid {}", dto);
            throw new InvalidEntityException("Invitation is not valid ",
                    ErrorCodes.MANUSCRIPT_NOT_FOUND, errors);
        }

        Invitation inv = InvitationDTO.toEntity(dto) ;
        inv.setEditor(EditorDTO.toEntity(editorService.findById(idedit)));
        inv.setReviewer(ReviewerDTO.toEntity(reviewerService.findById(idrev)));
        inv.setManuscript(ManuscriptDTO.toEntity(manuscriptService.findById(idman)));
        Invitation in = invitationRepository.save(inv)  ;
        System.out.println(in.getId());
        return InvitationDTO.fromEntity(in) ;

    }

    @Override
    public List<InvitationDTO> getInvitationsReviewed(Long idrev) {
        return invitationRepository.getInvitationsReviewed(idrev).stream().map(
                InvitationDTO::fromEntity
        ).collect(Collectors.toList());
    }

    @Override
    public List<InvitationDTO> getPendingInvitations(Long idrev) {
        return invitationRepository.getPendingInvitations(idrev).stream().map(
                InvitationDTO::fromEntity
        ).collect(Collectors.toList());
    }
}
