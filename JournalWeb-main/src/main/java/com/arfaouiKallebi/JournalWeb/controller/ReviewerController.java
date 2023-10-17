package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.ReviewerControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import com.arfaouiKallebi.JournalWeb.services.ManuscriptService;
import com.arfaouiKallebi.JournalWeb.services.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReviewerController implements ReviewerControllerApi {

    @Autowired
    private ReviewerService reviewerService ;

    @Override
    public List<ReviewerDTO> getReviewers() {
        return reviewerService.findAll();
    }

    @Override
    public ReviewerDTO getReviewerById(Long id) {
        return reviewerService.findById(id);
    }

    @Override
    public ReviewerDTO getReviewerByEmail(@RequestBody Map<String, String> emailMap) {
        String email = emailMap.get("email") ;
        return reviewerService.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> deleteReviewerById(Long id) {
        return reviewerService.deleteById(id);
    }

    @Override
    public ReviewerDTO saveReviewer(ReviewerDTO reviewer) {
        return reviewerService.save(reviewer);
    }
}
