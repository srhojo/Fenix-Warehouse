package com.gft.demos.warehouse.domain.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gft.demos.warehouse.domain.entities.enums.VehicleTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "warehouse_vehicles")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({@JsonSubTypes.Type(value = CarEntity.class, name = VehicleTypeEnum.Names.CAR), @JsonSubTypes.Type(value = MotorcyleEntity.class, name = VehicleTypeEnum.Names.MOTORCYCLE)})
public class VehicleEntity {

    @NotNull
    @Column(name = "type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private VehicleTypeEnum type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "[0-9]{4}[A-Z]{3}")
    private String licensePlate;

    @NotBlank
    private String branch;

    @NotBlank
    private String model;

    private String information;

    @NotBlank
    private String color;

    @NotBlank
    private String year;

    private byte[] image;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private List<VehicleInspection> inspections;

    public VehicleTypeEnum getType() {
        return type;
    }

    public void setType(VehicleTypeEnum type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<VehicleInspection> getInspections() {
        return inspections;
    }

    public void setInspections(List<VehicleInspection> inspections) {
        this.inspections = inspections;
    }
}
