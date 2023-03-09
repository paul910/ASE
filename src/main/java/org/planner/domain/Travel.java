package org.planner.domain;

import java.util.Date;

public class Travel {
    private Date startDate;
    private Date endDate;
    private double budget;
    private Destination destination;

    public Travel(Date startDate, Date endDate, double budget, Destination destination) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.destination = destination;
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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}