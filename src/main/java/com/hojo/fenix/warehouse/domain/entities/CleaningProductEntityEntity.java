package com.hojo.fenix.warehouse.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="warehouse_cleaning_products")
public class CleaningProductEntityEntity extends ProductEntity {

    @Column(name="room")
    private String room;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
