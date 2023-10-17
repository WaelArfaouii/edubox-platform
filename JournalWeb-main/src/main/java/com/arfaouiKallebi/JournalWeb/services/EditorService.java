package com.arfaouiKallebi.JournalWeb.services;

import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EditorService {
    List<EditorDTO> findAll() ;
    EditorDTO findById(Long id) ;
    ResponseEntity<?> deleteById(Long id) ;

    EditorDTO save(EditorDTO dto);

    EditorDTO findByEmail(String email);
}
