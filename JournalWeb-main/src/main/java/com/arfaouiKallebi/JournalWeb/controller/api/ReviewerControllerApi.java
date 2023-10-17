package com.arfaouiKallebi.JournalWeb.controller.api;
import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import com.arfaouiKallebi.JournalWeb.dto.ReviewerDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("reviewers")
public interface ReviewerControllerApi {
    
    @GetMapping(value = "reviewers" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReviewerDTO> getReviewers();
    @GetMapping(value = "reviewers/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewerDTO getReviewerById(@PathVariable Long id);
    @PostMapping(value = "reviewers/email" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewerDTO getReviewerByEmail(@RequestBody Map<String, String> emailMap);
    @DeleteMapping(value = "reviewers/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteReviewerById(@PathVariable Long id) ;
    @PostMapping(value = "reviewers/save" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ReviewerDTO saveReviewer(@RequestBody ReviewerDTO reviewer) ;

}
