package com.arfaouiKallebi.JournalWeb.services.impl;
import com.arfaouiKallebi.JournalWeb.model.User;
import com.arfaouiKallebi.JournalWeb.model.Editor;
import com.arfaouiKallebi.JournalWeb.model.Reviewer;
import com.arfaouiKallebi.JournalWeb.repository.UserRepository;
import com.arfaouiKallebi.JournalWeb.repository.EditorRepository;
import com.arfaouiKallebi.JournalWeb.repository.ReviewerRepository;
import com.arfaouiKallebi.JournalWeb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository ;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email);
            return  user ;
        }
        
        else throw  new UsernameNotFoundException("User not found !") ;


    }


}
