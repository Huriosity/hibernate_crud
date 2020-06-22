package model;

import javax.persistence.*;

@Entity
@Table(name = "ruller_main_title_rel")
public class RullerMainTitleRel {


    @Id
    @Column(name = "ruller_id",nullable = false,unique = true)
    private int ruller_id;

    @Column(name = "title_id",nullable = false,unique = false)
    private int title_id;

    @Basic
    @Column(name = "got_the_title_in")
    private Integer got_the_title_in;

    @Basic
    @Column(name = "lost_the_title_in")
    private Integer lost_the_title_in;

    @OneToOne(mappedBy = "rullerMainTitleRel")
    private Ruler ruler;

    public Ruler getRuler() {
        return ruler;
    }

    public void setRuler(Ruler ruler) {
        ruler.setRullerMainTitleRel(this);
        this.ruler = ruler;
    }


    //OneToMany Example
    // @Access(AccessType.PROPERTY)
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id",insertable = false, updatable = false)
    private Title title;

    public Title getTitle() {
        return this.title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }


    ///////////////////////////////////////
    public int getRuller_id() {
        return ruller_id;
    }

    public void setRuller_id(int ruller_id) {
        System.out.println("=================================R   " + ruller_id + " ======================================");
        this.ruller_id = ruller_id;
        System.out.println("=================================R2   " + this.ruller_id + " ======================================");
    }

    ///////////////////////////////////////
    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        System.out.println("=================================   " + title_id + " ======================================");
        this.title_id = title_id;
        System.out.println("=================================t2   " + this.title_id + " ======================================");
    }

    ///////////////////////////////////////
    public Integer getGot_the_title_in() {
        return got_the_title_in;
    }

    public void setGot_the_title_in(Integer got_the_title_in) {
        this.got_the_title_in = got_the_title_in;
    }

    ///////////////////////////////////////
    public Integer getLost_the_title_in() {
        return lost_the_title_in;
    }

    public void setLost_the_title_in(Integer lost_the_title_in) {
        this.lost_the_title_in = lost_the_title_in;
    }

    public RullerMainTitleRel() { }

    @Override
    public String toString() {
        return "RullerMainTitleRel:\n" +
                "ruler id: " + ruller_id +
                "\ntitle id : " + title_id + "\n" +
                "got the title in: " + got_the_title_in + "\n" +
                "lost the title in: " + lost_the_title_in + "\n";
    }

}
