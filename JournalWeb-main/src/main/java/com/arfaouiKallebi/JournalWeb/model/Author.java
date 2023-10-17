package com.arfaouiKallebi.JournalWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author extends AbstractEntity {


    @Column(name = "firstName")
    private String firstName ;

    @Column(name = "lastName")
    private String lastName ;

    @Column(name = "email")
    private String email ;

    @Column(name = "jobTitle")
    private String jobTitle ;

    @Column(name = "address")
    private String address ;

    @Column(name = "country")
    private String country ;

    @Column(name = "phoneNumber")
    private String phoneNumber ;

    @Column(name = "institution")
    private String institution ;

    @ManyToMany(fetch = FetchType.EAGER  , cascade = CascadeType.PERSIST)
    @Column(name = "manuscripts")
    private List<Manuscript> manuscripts = new ArrayList<>();


}
