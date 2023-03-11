package org.planner.service;

import org.planner.domain.Travel;
import org.planner.domain.User;
import org.planner.persistence.TravelRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class TravelService {
    private final Logger logger;
    private final TravelRepository travelRepository;
    private final List<Travel> travels;
    private final User activeUser;

    public TravelService(User activeUser) {
        this.logger = Logger.getLogger(TravelService.class.getName());
        this.travelRepository = new TravelRepository();
        this.travels = new ArrayList<>();
        this.activeUser = activeUser;

        loadTravelsForActiveUser();
        // TODO: first load error missing travels in file
    }

    public static void main(String[] args) {
        TravelService travelService = new TravelService(new User("test", "test", "test"));
        Travel travel = new Travel(travelService.getActiveUser(), "Berlin", 200, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        travelService.addTravel(travel);

        travelService.printTravelList();
    }

    public List<Travel> getTravels() {
        return travels;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void loadTravelsForActiveUser() {
        this.travels.clear();
        this.travels.addAll(this.travelRepository.loadListForUser(this.activeUser));
    }

    public void addTravel(Travel travel) {
        this.travels.add(travel);
        this.travelRepository.persistList(this.travels);
    }

    public void removeTravel(Travel travel) {
        this.travels.clear();
        this.travelRepository.removeEntryById(travel.getId().toString());
        this.travels.addAll(this.travelRepository.loadListForUser(this.activeUser));
    }

    public void printTravelList() {
        if (this.travels.isEmpty()) {
            System.out.println("No travel planned yet.");
            return;
        }
        System.out.printf("%-5s%-20s%-20s%-10s%-20s%-20s%-20s%-20s%n", "ID", "Created By", "City", "Budget", "Start Date", "End Date", "Created Date", "Last Modified Date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Travel travel : this.travels) {
            System.out.printf("%-5d%-20s%-20s%-10.2f%-20s%-20s%-20s%-20s%n", travel.getId(), travel.getCreatedBy(), travel.getCity(), travel.getBudget(), dateFormat.format(travel.getStartDate()), dateFormat.format(travel.getEndDate()), dateFormat.format(travel.getCreatedDate()), dateFormat.format(travel.getLastModifiedDate()));
        }
    }

    public Travel getTravelById(String input) {
        for (Travel travel : this.travels) {
            if (travel.getId().toString().equals(input)) {
                return travel;
            }
        }
        logger.warning("No travel found for id " + input);
        return null;
    }
}
