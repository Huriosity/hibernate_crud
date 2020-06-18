package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")///////////////////////////////////////
    @ElementCollection(targetClass=RulerCountryRel.class)/////////////////
    private Set<RulerCountryRel> rulerCountryRels = new HashSet<>();

    public Set<RulerCountryRel> getRulerCountryRels() {
        return rulerCountryRels;
    }

    public void setRulerCountryRels(Set<RulerCountryRel> rulerCountryRels) {
        this.rulerCountryRels = rulerCountryRels;
    }

    public void addRulerCountryRels(RulerCountryRel contact) {
        contact.setCountry(this);//////////////////////////////////////////////
        this.rulerCountryRels.add(contact);
    }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")///////////////////////////////////////
    @ElementCollection(targetClass=CountryCapitalTownRel.class)/////////////////
    private Set<CountryCapitalTownRel> countryCapitalTownRel = new HashSet<>();

    public Set<CountryCapitalTownRel> getCountryCapitalTownRel() {
        return countryCapitalTownRel;
    }

    public void setCountryCapitalTownRel(Set<CountryCapitalTownRel> countryCapitalTownRel) {
        this.countryCapitalTownRel = countryCapitalTownRel;
    }

    public void addCountryTownRel(CountryCapitalTownRel contact) {
        contact.setCountry(this);//////////////////////////////////////////////
        this.countryCapitalTownRel.add(contact);
    }

    ////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country:\n" +
                "id: " + id +
                "\nName: " + name + "\n";
    }
}
