package com.arfaouiKallebi.JournalWeb.services.impl;
import com.arfaouiKallebi.JournalWeb.dto.CommentDTO;
import com.arfaouiKallebi.JournalWeb.dto.ManuscriptDTO;
import com.arfaouiKallebi.JournalWeb.exception.EntityNotFoundException;
import com.arfaouiKallebi.JournalWeb.exception.ErrorCodes;
import com.arfaouiKallebi.JournalWeb.exception.InvalidEntityException;
import com.arfaouiKallebi.JournalWeb.model.Comment;
import com.arfaouiKallebi.JournalWeb.repository.CommentRepository;
import com.arfaouiKallebi.JournalWeb.services.CommentService;
import com.arfaouiKallebi.JournalWeb.services.ManuscriptService;
import com.arfaouiKallebi.JournalWeb.validator.CommentValidator;
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
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository ;
    @Autowired
    private ManuscriptService manuscriptService ;

    @Override
    public List<CommentDTO> getComments(Long idman) {
        return commentRepository.getComments(idman).stream()
                .map(CommentDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public CommentDTO saveComment(Long idman, CommentDTO dto) {
        List<String> errors = CommentValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Comment is not valid {}", dto);
            throw new InvalidEntityException("Comment is not valid ",
                    ErrorCodes.COMMENT_NOT_VALID, errors);
        }
        Comment com = CommentDTO.toEntity(dto) ;
        com.setManuscript(ManuscriptDTO.toEntity(manuscriptService.findById(idman)));
        return CommentDTO.fromEntity(
                commentRepository.save(
                        com
                )
        );
    }

    @Override
    public CommentDTO findById(Long id) {
        if (id == null) {
            log.error("Comment ID is null");
            return null;
        }
        return commentRepository.findById(id)
                .map(CommentDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Comment with id : = " + id + " exists",
                        ErrorCodes.USER_NOT_FOUND)
                );
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        commentRepository.deleteById(id);
        return new ResponseEntity<>("Comment deleted" , HttpStatus.OK) ;
    }
}
