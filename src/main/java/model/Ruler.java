package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "ruler")
public class Ruler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "year_of_birth")
    private Integer year_of_birth;

    @Column(name = "year_of_death")
    private Integer year_of_death;

    @Column(name = "testator")
    private Integer testator;

/*
    @Column(name = "fk_ruller_id", nullable = false, unique = true, insertable = false, updatable = false) //////////////////////////////
    private Integer rullerMainTitleId;
*/

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", referencedColumnName = "ruller_id") //////////////////////////////////////////////////
    private RullerMainTitleRel rullerMainTitleRel;

    public RullerMainTitleRel getRullerMainTitleRel() {
        return this.rullerMainTitleRel;
    }

    public void setRullerMainTitleRel(RullerMainTitleRel rullerMainTitleRel) {
        this.rullerMainTitleRel = rullerMainTitleRel;
    }


    //OneToMany Example
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ruler",fetch = FetchType.EAGER)///////////////////////////////////////
    @ElementCollection(targetClass=RulerCountryRel.class)/////////////////
    private Set<RulerCountryRel> rulerCountryRels = new HashSet<>();

    public Set<RulerCountryRel> getRulerCountryRels() {
        return rulerCountryRels;
    }

    public void setRulerCountryRels(Set<RulerCountryRel> rulerCountryRels) {
        this.rulerCountryRels = rulerCountryRels;
    }

    public void addRulerCountryRels(RulerCountryRel contact) {
        contact.setRuler(this);//////////////////////////////////////////////
        this.rulerCountryRels.add(contact);
    }


/*    public int getRullerMainTitleId() {
        return rullerMainTitleId;
    }

    public void setRullerMainTitleId(Integer rullerMainTitleId) {
        this.rullerMainTitleId = rullerMainTitleId;
    }*/

    /////////////////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /////////////////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /////////////////////////////////////////////////////////
    public Integer getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(Integer year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    /////////////////////////////////////////////////////////
    public Integer getYear_of_death() {
        return year_of_death;
    }

    public void setYear_of_death(Integer year_of_death) {
        this.year_of_death = year_of_death;
    }

    /////////////////////////////////////////////////////////
    public Integer getTestator() {
        return testator;
    }

    public void setTestator(Integer testator) {
        this.testator = testator;
    }

    public Ruler(){

    };

    @Override
    public String toString() {
        return "Ruler:\n" +
                "id: " + id +
                "\nName: " + name + "\n" +
                "year of birth: " + year_of_birth + "\n" +
                "year if death: " + year_of_death + "\n" +
                "testator: " + testator + "\n";
    }
}
