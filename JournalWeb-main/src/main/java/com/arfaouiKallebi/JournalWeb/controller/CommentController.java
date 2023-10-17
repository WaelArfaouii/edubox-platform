package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.CommentControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.CommentDTO;
import com.arfaouiKallebi.JournalWeb.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController implements CommentControllerApi {

    @Autowired
    private CommentService commentService ;

    @Override
    public List<CommentDTO> getComments(@PathVariable Long idman ){
        return commentService.getComments(idman) ;
    }
    @Override
    public ResponseEntity<?> deleteCommentById( @PathVariable Long id){
        return commentService.deleteById(id);
    }
    @Override
    public CommentDTO saveComment(@PathVariable Long idman ,@RequestBody CommentDTO comment){
        return commentService.saveComment(idman , comment) ;
    }
}
