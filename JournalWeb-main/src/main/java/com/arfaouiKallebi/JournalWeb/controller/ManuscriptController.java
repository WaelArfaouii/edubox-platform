package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.ManuscriptControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.dto.ItemDTO;
import com.arfaouiKallebi.JournalWeb.dto.ManuscriptDTO;
import com.arfaouiKallebi.JournalWeb.model.Attachment;
import com.arfaouiKallebi.JournalWeb.model.Item;
import com.arfaouiKallebi.JournalWeb.services.AuthorService;
import com.arfaouiKallebi.JournalWeb.services.ItemService;
import com.arfaouiKallebi.JournalWeb.services.ManuscriptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ManuscriptController implements ManuscriptControllerApi {


    @Autowired
    private ManuscriptService manuscriptService ;

    @Autowired
    private AuthorService authorService ;

    @Override
    public List<ManuscriptDTO> getManuscripts(@PathVariable Long idauth){
        return manuscriptService.findManuscripts(idauth) ;
    }

    @Override
    public ResponseEntity<?> deleteManuscriptById(@PathVariable Long id){
        return manuscriptService.deleteById(id);
    }

    @Override
    public ResponseEntity<?> RejectManuscriptById(Long id) {
        return manuscriptService.rejectManuscriptById(id);
    }

    @Override
    public ManuscriptDTO saveManuscript(@PathVariable Long idauth , @RequestBody ManuscriptDTO manuscript){
        return manuscriptService.saveManuscript(idauth , manuscript);
    }

    @Override
    public List<ManuscriptDTO> getProcessedManuscripts(@PathVariable Long idauth){
        return manuscriptService.getProcessedManuscripts(idauth);
    }

    @Override
    public List<ManuscriptDTO> getSentBackManuscripts(@PathVariable Long idauth){
        return manuscriptService.getSentBackManuscripts(idauth);
    }

    @Override
    public List<ManuscriptDTO> getIncompleteManuscripts(@PathVariable Long idauth){
        return manuscriptService.getIncompleteManuscripts(idauth);
    }

    @Override
    public List<ManuscriptDTO> getWaitingManuscripts(@PathVariable Long idauth){
        return manuscriptService.getWaitingManuscripts(idauth);
    }


    @Override
    public List<AuthorDTO> addAuthor(@PathVariable Long idman , @PathVariable Long author){
        return authorService.addAuthor(idman , author) ;
    }

}
