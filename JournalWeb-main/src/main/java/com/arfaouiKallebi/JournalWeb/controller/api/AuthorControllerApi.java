package com.arfaouiKallebi.JournalWeb.controller.api;


import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("authors")
public interface AuthorControllerApi {

    @GetMapping(value = "authors" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> getAuthors();

    @GetMapping(value = "authors/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO getAuthorById(@PathVariable Long id);


    @PostMapping(value = "authors/email" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO getAuthorByEmail(@RequestBody Map<String, String> emailMap);

    @DeleteMapping(value = "authors/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteAuthorById(@PathVariable Long id) ;

    @PostMapping(value = "authors/save" , produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO author) ;

}
