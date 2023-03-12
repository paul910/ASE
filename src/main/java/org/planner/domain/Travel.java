package org.planner.domain;

import org.planner.persistence.TravelRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Travel {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Long id;
    private final String createdBy;
    private final Date createdDate;
    private String city;
    private double budget;
    private Date startDate;
    private Date endDate;
    private Date lastModifiedDate;

    public Travel(User user, String city, double budget, Date startDate, Date endDate) {
        this.id = setId();
        this.createdBy = user.getUsername();
        this.city = city;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = new Date(System.currentTimeMillis());
        this.lastModifiedDate = new Date(System.currentTimeMillis());
    }

    public Travel(Long id, String createdBy, String city, double budget, Date startDate, Date endDate, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.createdBy = createdBy;
        this.city = city;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static String getColumns() {
        return "id,createdBy,city,budget,startDate,endDate,createdDate,lastModifiedDate";
    }

    @Override
    public String toString() {
        return this.id + "," + this.createdBy + "," + this.city + "," + this.budget + "," + DATE_FORMAT.format(this.startDate) + "," + DATE_FORMAT.format(this.endDate) + "," + DATE_TIME_FORMAT.format(this.createdDate) + "," + DATE_TIME_FORMAT.format(this.lastModifiedDate);
    }

    private Long setId() {
        return TravelRepository.getNewId();
    }

    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}