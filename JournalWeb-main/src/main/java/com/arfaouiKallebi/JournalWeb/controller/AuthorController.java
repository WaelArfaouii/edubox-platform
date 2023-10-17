package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.AuthorControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.services.AuthorService;
import com.arfaouiKallebi.JournalWeb.services.ManuscriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthorController implements AuthorControllerApi {

    @Autowired
    private AuthorService authorService ;

    @Override
    public List<AuthorDTO> getAuthors() {
        return authorService.findAll();
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        return authorService.findById(id);
    }

    @Override
    public AuthorDTO getAuthorByEmail(@RequestBody Map<String, String> emailMap){
        String email = emailMap.get("email") ;
        return authorService.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> deleteAuthorById(Long id) {
        return authorService.deleteById(id);
    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO author) {
        return authorService.save(author);
    }
}
