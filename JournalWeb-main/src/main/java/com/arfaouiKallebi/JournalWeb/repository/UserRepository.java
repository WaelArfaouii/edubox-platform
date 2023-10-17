package com.arfaouiKallebi.JournalWeb.repository;

import com.arfaouiKallebi.JournalWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long > {
    Boolean existsByEmail(String email);
    @Query(value = "select a  from User a where a.email =:name")
    User findByEmail(String name);
}
