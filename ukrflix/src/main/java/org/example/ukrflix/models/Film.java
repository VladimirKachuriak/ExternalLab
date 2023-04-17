package org.example.ukrflix.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.sql.Date;
import java.util.Set;


@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date release_date;
    private int price;
    private String img_src;
    private String yt_src;
    //@OneToMany(mappedBy = "film", cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private Set<Purchase> purchases;

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

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getYt_src() {
        return yt_src;
    }

    public void setYt_src(String yt_src) {
        this.yt_src = yt_src;
    }

    public Set<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Set<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", release_date='" + release_date + '\'' +
                ", price='" + price + '\'' +
                ", img_src='" + img_src + '\'' +
                ", yt_src='" + yt_src + '\'' +
                '}';
    }


}
