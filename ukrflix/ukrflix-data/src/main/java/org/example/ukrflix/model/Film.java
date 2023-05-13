package org.example.ukrflix.model;



import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String name;
    @NotNull
    @Column(name = "release_date")
    private Date releaseDate;
    private int price;
    @Column(name = "img_src")
    private String imgSrc;
    @Column(name = "yt_src")
    private String ytSrc;
    private String description;
    //@OneToMany(mappedBy = "film", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @OneToMany(mappedBy = "film")
    private Set<Purchase> purchases;
    /*@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "actorassociation",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actors;*/
    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<ActorAssociation> actorAssociations;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date release_date) {
        this.releaseDate = release_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String img_src) {
        this.imgSrc = img_src;
    }

    public String getYtSrc() {
        return ytSrc;
    }

    public void setYtSrc(String yt_src) {
        this.ytSrc = yt_src;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ActorAssociation> getActorAssociations() {
        return actorAssociations;
    }

    public void setActorAssociations(List<ActorAssociation> actorAssociations) {
        this.actorAssociations = actorAssociations;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date=" + releaseDate +
                ", price=" + price +
                ", actorAssociations=" + actorAssociations +
                '}';
    }
}
