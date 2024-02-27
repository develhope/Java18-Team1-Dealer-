package com.develhope.spring.Vehicle.Entities;

import com.develhope.spring.Vehicle.Entities.Enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Van extends Vehicle{
    @Column(nullable = false)
    private TransmissionType transmissionType;
    @Column(nullable = false)
    private Double cubiCapacity;
    @Column(nullable = false)
    private TractionType tractionType;
    @Column(nullable = false)
    private Boolean optionFullTraction;
    @Column(nullable = false)
    private Integer doors;
    @Column(nullable = false)
    private Boolean centralizedClosing;
    @Column(nullable = false)
    private Boolean airConditioning;
    @Column(nullable = false)
    private Boolean bluetooth;
    @Column(nullable = false)
    private Boolean satNav;
    @Column(nullable = false)
    private Boolean spareTire;
    @Column(nullable = false)
    private Boolean antiTheft;
    @Column(nullable = false)
    private Boolean windowedBackDoor;
    @Column(nullable = false)
    private Boolean slideSideDoor;
    @Column(nullable = false)
    private Boolean reversingCam;
    @Column(nullable = false)
    private Boolean antiCollisionSystem;
}
