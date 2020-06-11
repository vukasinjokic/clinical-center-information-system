package com.example.demo.dto;

import com.example.demo.model.PriceList;
import com.example.demo.model.PriceListItem;

import java.util.Collection;

public class PriceListDTO {
    private Integer id;
    private Collection<PriceListItem> items;
    private Integer clinicId;

    public PriceListDTO(){}

    public PriceListDTO(Collection<PriceListItem> items, Integer clinicId) {
        this.items = items;
        this.clinicId = clinicId;
    }

    public PriceListDTO(PriceList priceList){
        this.id = priceList.getId();
        this.items = priceList.getItems();
        this.clinicId = priceList.getClinic().getId();
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

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }
}
