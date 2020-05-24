package com.example.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "price_list")
public class PriceList {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @OneToMany(cascade = {ALL}, fetch = EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<PriceListItem> items;

    public PriceList() {
    }

    public Integer getId() {
        return id;
    }

    public Collection<PriceListItem> getItems() {
        return items;
    }

    public void setItems(Collection<PriceListItem> items) {
        this.items = items;
    }
}
