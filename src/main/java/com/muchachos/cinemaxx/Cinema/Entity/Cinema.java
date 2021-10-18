package com.muchachos.cinemaxx.Cinema.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muchachos.cinemaxx.Theater.Entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(length = 50,nullable = false)
    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cinema_id")
    @JsonIgnoreProperties("cinema")
    private List<Theater> theaters = new ArrayList<>();

    public Cinema(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
