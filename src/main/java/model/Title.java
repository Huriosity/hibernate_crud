package model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "title")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    //OneToMany Example
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "title")///////////////////////////////////////
    @ElementCollection(targetClass=RullerMainTitleRel.class)/////////////////
    private Set<RullerMainTitleRel> rullerMainTitleRels = new HashSet<>();
    public Set<RullerMainTitleRel> getRullerMainTitleRels() {
        return this.rullerMainTitleRels;
    }

    public void setRullerMainTitleRels(Set<RullerMainTitleRel> rullerMainTitleRels) {
        this.rullerMainTitleRels = rullerMainTitleRels;
    }

    public void addRullerMainTitleRels(RullerMainTitleRel contact) {
        contact.setTitle(this);//////////////////////////////////////////////
        this.rullerMainTitleRels.add(contact);
    }

    //////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //////////////////////////////////////////////
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Title:\n" +
                "id: " + id +
                "\nName: " + name + "\n";
    }
}
