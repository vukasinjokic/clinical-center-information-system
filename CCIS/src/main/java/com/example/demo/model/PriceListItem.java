package com.example.demo.model;


import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="price_list_item")
public class PriceListItem {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "price", unique = false, nullable = true)
    private float price;

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "examination_type_id")
    private ExaminationType examinationType;

    public PriceListItem() {
    }

    public Integer getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ExaminationType getExaminationType() {
        return examinationType;
    }

    public void setExaminationType(ExaminationType examinationType) {
        this.examinationType = examinationType;
    }
}
