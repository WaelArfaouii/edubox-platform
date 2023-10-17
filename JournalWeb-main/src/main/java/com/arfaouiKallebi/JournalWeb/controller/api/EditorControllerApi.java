package com.arfaouiKallebi.JournalWeb.controller.api;
import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api("editors")
public interface EditorControllerApi {

    @GetMapping(value = "editors" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EditorDTO> getEditors();

    @GetMapping(value = "editors/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public EditorDTO getEditorById(@PathVariable Long id);

    @PostMapping(value = "editors/email" , produces = MediaType.APPLICATION_JSON_VALUE)
    public EditorDTO getEditorByEmail(@RequestBody Map<String, String> emailMap);

    @DeleteMapping(value = "editors/delete/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEditorById(@PathVariable Long id) ;

    @PostMapping(value = "editors/save" , produces = MediaType.APPLICATION_JSON_VALUE)
    public EditorDTO saveEditor(@RequestBody EditorDTO editor) ;
}
