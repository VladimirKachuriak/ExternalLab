package org.example.ukrflix.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private int price;

    @Column(name = "img_src")

    private String imgSrc;

    @Column(name = "yt_src")
    private String ytSrc;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "film")
    private Set<Purchase> purchases;

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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