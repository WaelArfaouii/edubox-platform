package com.arfaouiKallebi.JournalWeb.services.impl;

import com.arfaouiKallebi.JournalWeb.dto.*;
import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationRequest;
import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationResponse;
import com.arfaouiKallebi.JournalWeb.dto.auth.BearerToken;
import com.arfaouiKallebi.JournalWeb.model.*;
import com.arfaouiKallebi.JournalWeb.repository.*;
import com.arfaouiKallebi.JournalWeb.security.JwtUtilities;
import com.arfaouiKallebi.JournalWeb.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private EditorRepository editorRepository;
    @Autowired
    private ReviewerRepository reviewerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilities jwtUtilities;


    @Override
    public Role saveRole(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public User saverUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) {
        String email = registerDto.getEmail();
        String roleNameString = registerDto.getRole(); // Assuming getRole() returns a String

        // Convert the role name String to a RoleName enum
        RoleName roleName = RoleName.valueOf(roleNameString);

        if (userRepository.existsByEmail(email)) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.SEE_OTHER);
        }

        User user = new User();

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        user.setRoles(Collections.singletonList(new Role(roleName)));

        switch (roleName) {
            case AUTHOR:
                Author author = new Author();
                author.setEmail(email);
                author.setFirstName(registerDto.getFirstName());
                author.setLastName(registerDto.getLastName());
                author.setJobTitle(registerDto.getJobTitle());
                author.setAddress(registerDto.getAddress());
                author.setCountry(registerDto.getCountry());
                author.setPhoneNumber(registerDto.getPhoneNumber());
                author.setInstitution(registerDto.getInstitution());
                // Set other Author-specific fields here
                authorRepository.save(author);
                break;
            case REVIEWER:
                Reviewer reviewer = new Reviewer();
                reviewer.setEmail(email);
                reviewer.setFirstName(registerDto.getFirstName());
                reviewer.setLastName(registerDto.getLastName());
                reviewer.setJobTitle(registerDto.getJobTitle());
                reviewer.setAddress(registerDto.getAddress());
                reviewer.setCountry(registerDto.getCountry());
                reviewer.setPhoneNumber(registerDto.getPhoneNumber());
                reviewer.setInstitution(registerDto.getInstitution());
                // Set other Reviewer-specific fields here
                reviewerRepository.save(reviewer);
                break;
            case EDITOR:
                Editor editor = new Editor();
                editor.setEmail(email);
                editor.setFirstName(registerDto.getFirstName());
                editor.setLastName(registerDto.getLastName());
                editor.setJobTitle(registerDto.getJobTitle());
                editor.setAddress(registerDto.getAddress());
                editor.setCountry(registerDto.getCountry());
                editor.setPhoneNumber(registerDto.getPhoneNumber());
                editor.setInstitution(registerDto.getInstitution());
                // Set other Editor-specific fields here
                editorRepository.save(editor);
                break;
            default:
                throw new UsernameNotFoundException("Invalid role");
        }

        User savedUser = userRepository.save(user);

        String token = jwtUtilities.generateToken(savedUser.getId().toString(), email, Collections.singletonList(roleNameString));

        return new ResponseEntity<>(new BearerToken(token, "Bearer"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findByEmail(authentication.getName());
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(user.getId().toString(), user.getUsername(), rolesNames);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(token).build());


    }
}

