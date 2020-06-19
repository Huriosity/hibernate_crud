package model;

import javax.persistence.*;

@Entity
@Table(name = "ruler_country_rel")
public class RulerCountryRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    //  @Id
    @Column(name = "ruler_id",nullable = false)
    private int ruler_id;

    @Column(name = "country_id",nullable = false)
    private int country_id;

    @Column(name = "year_of_reign")
    private Integer year_of_reign;

    @Column(name = "year_end_of_reign")
    private Integer year_end_of_reign;

    @ManyToOne
    @JoinColumn(name = "ruler_id", referencedColumnName = "id",insertable = false, updatable = false) //
    private Ruler ruler;

    public Ruler getRuler() {
        return ruler;
    }

    public void setRuler(Ruler ruler) {
        this.ruler = ruler;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    //////////////////////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //////////////////////////////////////////////////////////////
    public int getRuler_id() {
        return ruler_id;
    }

    public void setRuler_id(int ruler_id) {
        this.ruler_id = ruler_id;
    }

    //////////////////////////////////////////////////////////////
    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    //////////////////////////////////////////////////////////////
    public Integer getYear_of_reign() {
        return year_of_reign;
    }

    public void setYear_of_reign(Integer year_of_reign) {
        this.year_of_reign = year_of_reign;
    }

    //////////////////////////////////////////////////////////////
    public Integer getYear_end_of_reign() {
        return year_end_of_reign;
    }

    public void setYear_end_of_reign(Integer year_end_of_reign) {
        this.year_end_of_reign = year_end_of_reign;
    }

    @Override
    public String toString() {
        return "RulerCountryRel:\n" +
                "id: " + id +
                "\nruler_id  : " + ruler_id + "" +
                "\ncountry_id  : " + country_id + "" +
                "\nyear_of_reign : " + year_of_reign +
                "\nyear_end_of_reign : " + year_end_of_reign;// +
    }
}
