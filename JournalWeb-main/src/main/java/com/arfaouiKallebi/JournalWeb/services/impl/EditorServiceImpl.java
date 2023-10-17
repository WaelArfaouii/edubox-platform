package com.arfaouiKallebi.JournalWeb.services.impl;

import com.arfaouiKallebi.JournalWeb.dto.EditorDTO;
import com.arfaouiKallebi.JournalWeb.exception.EntityNotFoundException;
import com.arfaouiKallebi.JournalWeb.exception.ErrorCodes;
import com.arfaouiKallebi.JournalWeb.exception.InvalidEntityException;
import com.arfaouiKallebi.JournalWeb.repository.EditorRepository;
import com.arfaouiKallebi.JournalWeb.services.EditorService;
import com.arfaouiKallebi.JournalWeb.validator.EditorValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//Logger
@Slf4j
public class EditorServiceImpl implements EditorService {
    @Autowired
    private EditorRepository editorRepository ;
    @Override
    public List<EditorDTO> findAll() {
        return editorRepository.findAll().stream()
                .map(EditorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EditorDTO findById(Long id) {
        if (id == null) {
            log.error("Editor ID is null");
            return null;
        }
        return editorRepository.findById(id)
                .map(EditorDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Editor with id : = " + id + " exists",
                        ErrorCodes.USER_NOT_FOUND)
                );
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {

        editorRepository.deleteById(id);
        return new ResponseEntity<>("Editor deleted" , HttpStatus.OK) ;
    }

    @Override
    public EditorDTO save(EditorDTO dto) {
        List<String> errors = EditorValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Editor is not valid {}", dto);
            throw new InvalidEntityException("Editor is not valid", ErrorCodes.EDITOR_CLIENT_NOT_VALID, errors);
        }
        return EditorDTO.fromEntity(
                editorRepository.save(
                        EditorDTO.toEntity(dto)
                )
        );
    }

    @Override
    public EditorDTO findByEmail(String email) {
        return EditorDTO.fromEntity(editorRepository.findByEmail(email));
    }
}
