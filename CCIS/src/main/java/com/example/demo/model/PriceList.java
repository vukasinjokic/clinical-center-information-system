package com.example.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

import java.util.ArrayList;
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

    @OneToOne(fetch = LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;


    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public PriceList(Integer id,Clinic clinic, Collection<PriceListItem> items){
        this.id = id;
        this.clinic = clinic;
        this.items = items;
    }

    public PriceList() {
        this.items = new ArrayList<PriceListItem>();
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
