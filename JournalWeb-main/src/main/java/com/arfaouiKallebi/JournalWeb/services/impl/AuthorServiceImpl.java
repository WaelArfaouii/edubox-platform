package com.arfaouiKallebi.JournalWeb.services.impl;


import com.arfaouiKallebi.JournalWeb.dto.AuthorDTO;
import com.arfaouiKallebi.JournalWeb.exception.EntityNotFoundException;
import com.arfaouiKallebi.JournalWeb.exception.ErrorCodes;
import com.arfaouiKallebi.JournalWeb.exception.InvalidEntityException;
import com.arfaouiKallebi.JournalWeb.model.Author;
import com.arfaouiKallebi.JournalWeb.model.Manuscript;
import com.arfaouiKallebi.JournalWeb.repository.AuthorRepository;
import com.arfaouiKallebi.JournalWeb.repository.ManuscriptRepository;
import com.arfaouiKallebi.JournalWeb.services.AuthorService;
import com.arfaouiKallebi.JournalWeb.validator.AuthorValidator;
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
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository ;
    @Autowired
    private ManuscriptRepository manuscriptRepository ;
    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream()
                .map(AuthorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findById(Long id) {
        if (id == null) {
            log.error("Author ID is null");
            return null;
        }
        return authorRepository.findById(id)
                .map(AuthorDTO::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "No Author with id : = " + id + " exists",
                        ErrorCodes.USER_NOT_FOUND)
                );
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<>("Author deleted" , HttpStatus.OK) ;
    }

    @Override
    public AuthorDTO save(AuthorDTO dto) {
        List<String> errors = AuthorValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Author is not valid {}", dto);
            throw new InvalidEntityException("Author is not valid", ErrorCodes.USER_NOT_VALID, errors);
        }
        return AuthorDTO.fromEntity(
                authorRepository.save(
                        AuthorDTO.toEntity(dto)
                )
        );
    }

    @Override
    public List<AuthorDTO> addAuthor(Long idman, Long author) {
        Author auth = authorRepository.getReferenceById(author);
        Manuscript man = manuscriptRepository.getReferenceById(idman);
        man.getAuthors().add(auth);
        auth.getManuscripts().add(man);
        manuscriptRepository.save(man);
        return man.getAuthors().stream()
                .map(AuthorDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTO findByEmail(String email) {
        return AuthorDTO.fromEntity(authorRepository.findByEmail(email));
    }
}
