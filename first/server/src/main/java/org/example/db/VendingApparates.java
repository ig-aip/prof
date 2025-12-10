package org.example.db;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.example.Enums.VendingStatus;
import org.example.Enums.VendingType;


import java.time.Duration;
import java.time.LocalDate;

@Entity
@Table(name = "VendingApparates")
public class VendingApparates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "geoLocation", nullable = false)
    String geoLocation;

    @Column(name = "model", nullable = false)
    String model;

    @Column(name = "summaryEarn")
    Long summaryEarn;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    VendingType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    VendingStatus status;

    @Column(name = "serialNumber", nullable = false, unique = true, length = 100)
    String serialNumber;

    @Column(name = "invertNumber", nullable = false, unique = true, length = 100)
    String invertNumber;

    @Column(name = "CompanyCreator", nullable = false)
    String CompanyCreator;

    @Column(name = "dateCreated", nullable = false)
    LocalDate dateCreated;

    @Column(name = "dateExploited")
    LocalDate dateExploited;

    @Column(name = "dateLastCheck")
    LocalDate dateLastCheck;

    @Column(name = "checkIntervalMonth")
    Integer checkIntervalMonth;

    @Column(name = "resourceHours", nullable = false)
    @Min(value = 1)
    Integer resourceHours;

    @Column(name = "dateNextCheck")
    LocalDate dateNextCheck;

    @Column(name = "dateInvertarization", nullable = false)
    LocalDate dateInvertarization;


    @Column(name = "serviceInterval", columnDefinition = "NUMERIC", nullable = false)
    Duration serviceInterval;

    @Column(name = "lastCheckWorkerId")
    Long lastCheckWorkerId;

    public boolean isDateLastCheckValid(){
        if (dateCreated.isAfter(dateLastCheck) || dateLastCheck.isAfter(LocalDate.now())) { return false; }
        return  true;
    }

    public boolean isDateExplotedValid(){
        if (dateCreated.isAfter(dateExploited) || dateInvertarization.isBefore(dateExploited)) { return false; }
        return  true;
    }

    public boolean isDateInvertarizationValid(){
        if(dateCreated.isAfter(dateInvertarization) || dateInvertarization.isAfter(LocalDate.now())) { return false; }
        return true;
    }

    @PrePersist @PreUpdate
    public void setCheckIntervalDate(){
        if(dateNextCheck == null){
            dateNextCheck = dateLastCheck.plusMonths(checkIntervalMonth);
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getSummaryEarn() {
        return summaryEarn;
    }

    public void setSummaryEarn(Long summaryEarn) {
        this.summaryEarn = summaryEarn;
    }

    public VendingType getType() {
        return type;
    }

    public void setType(VendingType type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInvertNumber() {
        return invertNumber;
    }

    public void setInvertNumber(String invertNumber) {
        this.invertNumber = invertNumber;
    }

    public String getCompanyCreater() {
        return CompanyCreator;
    }

    public void setCompanyCreater(String companyCreater) {
        this.CompanyCreator = companyCreater;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateExploited() {
        return dateExploited;
    }

    public void setDateExploited(LocalDate dateExploited) {
        this.dateExploited = dateExploited;
    }

    public LocalDate getDateLastCheck() {
        return dateLastCheck;
    }

    public void setDateLastCheck(LocalDate dateLastCheck) {
        this.dateLastCheck = dateLastCheck;
    }

    public Integer getCheckIntervalMonth() {
        return checkIntervalMonth;
    }

    public void setCheckIntervalMonth(Integer checkIntervalMonth) {
        this.checkIntervalMonth = checkIntervalMonth;
    }

    public Integer getResourceHours() {
        return resourceHours;
    }

    public void setResourceHours(Integer resourceHours) {
        this.resourceHours = resourceHours;
    }

    public LocalDate getDateNextCheck() {
        return dateNextCheck;
    }

    public void setDateNextCheck(LocalDate dateNextCheck) {
        this.dateNextCheck = dateNextCheck;
    }

    public LocalDate getDateInvertarization() {
        return dateInvertarization;
    }

    public void setDateInvertarization(LocalDate dateInvertarization) {
        this.dateInvertarization = dateInvertarization;
    }

    public Duration getServiceInterval() {
        return serviceInterval;
    }

    public void setServiceInterval(Duration serviceInterval) {
        this.serviceInterval = serviceInterval;
    }

    public Long getLastCheckWorkerId() {
        return lastCheckWorkerId;
    }

    public void setLastCheckWorkerId(Long lastCheckWorkerId) {
        this.lastCheckWorkerId = lastCheckWorkerId;
    }

    public VendingStatus getStatus() {
        return status;
    }

    public void setStatus(VendingStatus status) {
        this.status = status;
    }


}
