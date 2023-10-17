package com.arfaouiKallebi.JournalWeb.controller.api;
import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.dto.ItemDTO;
import com.arfaouiKallebi.JournalWeb.dto.ManuscriptDTO;
import com.arfaouiKallebi.JournalWeb.model.Attachment;
import com.arfaouiKallebi.JournalWeb.model.Item;
import io.swagger.annotations.Api;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("manuscripts")
public interface ManuscriptControllerApi {

    @GetMapping(value = "{idauth}/manuscripts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManuscriptDTO> getManuscripts(@PathVariable Long idauth) ;

    @DeleteMapping(value = "manuscripts/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteManuscriptById(@PathVariable Long id) ;

    @PostMapping(value = "manuscripts/reject/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> RejectManuscriptById(@PathVariable Long id) ;
    @PostMapping(value = "{idauth}/manuscripts/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ManuscriptDTO saveManuscript(@PathVariable Long idauth , @RequestBody ManuscriptDTO manuscript) ;

    @GetMapping(value = "manuscripts/{idman}/authors/add/{author}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> addAuthor(@PathVariable Long idman , @PathVariable Long author) ;
    @GetMapping(value = "{idauth}/manuscripts/processed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManuscriptDTO> getProcessedManuscripts(@PathVariable Long idauth) ;

    @GetMapping(value = "{idauth}/manuscripts/sentBack", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManuscriptDTO> getSentBackManuscripts(@PathVariable Long idauth) ;
    @GetMapping(value = "{idauth}/manuscripts/incomplete", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManuscriptDTO> getIncompleteManuscripts(@PathVariable Long idauth) ;

    @GetMapping(value = "{idauth}/manuscripts/waiting", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManuscriptDTO> getWaitingManuscripts(@PathVariable Long idauth) ;



}
