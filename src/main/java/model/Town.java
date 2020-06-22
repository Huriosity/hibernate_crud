package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "town")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "town",fetch = FetchType.EAGER)///////////////////////////////////////
    @ElementCollection(targetClass=CountryCapitalTownRel.class)/////////////////
    private Set<CountryCapitalTownRel> countryCapitalTown = new HashSet<>();

    public Set<CountryCapitalTownRel> getCountryCapitalTown() {
        return countryCapitalTown;
    }

    public void setCountryCapitalTown(Set<CountryCapitalTownRel> countryCapitalTown) {
        this.countryCapitalTown = countryCapitalTown;
    }

    public void addCountryTownRel(CountryCapitalTownRel contact) {
        contact.setTown(this);//////////////////////////////////////////////
        this.countryCapitalTown.add(contact);
    }


    /////////////////////////////////////////
    /*public int getId() {
        return id;
    }*/
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Town:\n" +
                "id: " + id +
                "\nName: " + name + "\n";
    }
}
