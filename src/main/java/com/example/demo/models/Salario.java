package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "salario")
public class Salario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private float quantidade;
    private Date data;

    @JsonIgnore
    @OneToMany(mappedBy = "salario", cascade = CascadeType.ALL)
    private List<Pessoa> pessoas;
}
