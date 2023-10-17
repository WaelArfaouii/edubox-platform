package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.EditorControllerApi;
import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import com.arfaouiKallebi.JournalWeb.services.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EditorController implements EditorControllerApi {

    @Autowired
    private EditorService editorService ;

    @Override
    public List<EditorDTO> getEditors() {
        return editorService.findAll();
    }

    @Override
    public EditorDTO getEditorById(Long id) {
        return editorService.findById(id);
    }

    @Override
    public EditorDTO getEditorByEmail(@RequestBody Map<String, String> emailMap) {
        String email = emailMap.get("email") ;
        return editorService.findByEmail(email) ;
    }

    @Override
    public ResponseEntity<?> deleteEditorById(Long id) {
        return editorService.deleteById(id);
    }

    @Override
    public EditorDTO saveEditor(EditorDTO editor) {
        return editorService.save(editor);
    }
}
