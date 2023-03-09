package org.planner.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class TravelPlanner {
    private List<Travel> travels;
    private User user;
    private TravelStatus status;

    public TravelPlanner(User user) {
        this.user = user;
        this.status = TravelStatus.IN_PROGRESS;
        this.travels = new ArrayList<>();
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TravelStatus getStatus() {
        return status;
    }

    public void setStatus(TravelStatus status) {
        this.status = status;
    }

    public void addTravel(Date startDate, Date endDate, double budget, Destination destination) {
        Travel travel = new Travel(startDate, endDate, budget, destination);
        travels.add(travel);
    }

    public void deleteTravel(Travel travel) {
        travels.remove(travel);
    }

    public List<Travel> searchTravel(Destination destination) {
        // TODO implement here
        return null;
    }
}