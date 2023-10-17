package com.arfaouiKallebi.JournalWeb.services.impl;

import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import com.arfaouiKallebi.JournalWeb.exception.EntityNotFoundException;
import com.arfaouiKallebi.JournalWeb.exception.ErrorCodes;
import com.arfaouiKallebi.JournalWeb.exception.InvalidEntityException;
import com.arfaouiKallebi.JournalWeb.repository.ReviewerRepository;
import com.arfaouiKallebi.JournalWeb.repository.ReviewerRepository;
import com.arfaouiKallebi.JournalWeb.services.ReviewerService;
import com.arfaouiKallebi.JournalWeb.validator.ReviewerValidator;
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
public class ReviewerServiceImpl implements ReviewerService {
    @Autowired
    private ReviewerRepository reviewerRepository ;
    @Override
    public List<ReviewerDTO> findAll() {

        return reviewerRepository.findAll().stream().map(
                        ReviewerDTO::fromEntity)
                        .collect(Collectors.toList());
    }

    @Override
    public ReviewerDTO findById(Long id) {
        if (id == null) {
            log.error("Reviewer ID is null");
            return null;
        }
        return reviewerRepository.findById(id)
                .map(ReviewerDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Reviewer with id : = " + id + " exists",
                        ErrorCodes.REVIEWER_NOT_FOUND)
                );
    }
    @Override
    public ResponseEntity<?> deleteById(Long id) {

        reviewerRepository.deleteById(id);
        return new ResponseEntity<>("Reviewer deleted !" , HttpStatus.OK) ;

    }

    @Override
    public ReviewerDTO save(ReviewerDTO dto) {
        List<String> errors = ReviewerValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Reviewer is not valid {}", dto);
            throw new InvalidEntityException("Reviewer is not valid ", ErrorCodes.REVIEWER_NOT_VALID, errors);
        }
        return ReviewerDTO.fromEntity(
                reviewerRepository.save(
                        ReviewerDTO.toEntity(dto)
                )
        );
    }

    @Override
    public ReviewerDTO findByEmail(String email) {
        return ReviewerDTO.fromEntity(reviewerRepository.findByEmail(email));
    }
}
