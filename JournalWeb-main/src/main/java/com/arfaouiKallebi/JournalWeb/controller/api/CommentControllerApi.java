package com.arfaouiKallebi.JournalWeb.controller.api;

import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.dto.CommentDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("comments")
public interface CommentControllerApi {

    @GetMapping(value = "{idman}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentDTO> getComments(@PathVariable Long idman ) ;
    @DeleteMapping(value = "comments/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCommentById( @PathVariable Long id) ;
    @PostMapping(value = "{idman}/comments/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommentDTO saveComment(@PathVariable Long idman ,@RequestBody CommentDTO comment) ;

}
