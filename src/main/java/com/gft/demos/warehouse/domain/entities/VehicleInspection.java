package com.gft.demos.warehouse.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_vehicle_inspections")
public class VehicleInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "vehicle_id")
    private Long vehicleId;

    @NotNull
    @Column(name = "kilometers", nullable = false)
    private Long kilometers;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Float price = 0f;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
