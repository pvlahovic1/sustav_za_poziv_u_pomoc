package hr.foi.airprojekt.web.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "opis_nesrece", schema = "air_projekt")
public class OpisNesrece {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "koeficijent")
    private double koeficijent;

}
