package org.planner.service;

import org.planner.domain.Travel;
import org.planner.domain.User;
import org.planner.persistence.TravelRepository;

import java.util.ArrayList;
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
    }

    //GETTER
    public List<Travel> getTravels() {
        return travels;
    }

    public User getActiveUser() {
        return activeUser;
    }

    //PERISTENCE

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
