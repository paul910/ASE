package org.planner.service;

import org.planner.Debug;
import org.planner.domain.Activity;
import org.planner.domain.Travel;
import org.planner.domain.User;
import org.planner.persistence.ActivityRepository;
import org.planner.persistence.TravelActivitiesRepository;
import org.planner.persistence.TravelRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class TravelService {
    private final Logger logger;
    private final TravelRepository travelRepository;
    private final ActivityRepository activityRepository;
    private final TravelActivitiesRepository travelActivitiesRepository;
    private final List<Travel> travels;
    private Map<Long, List<Long>> travelActivitiesMap;
    private final User activeUser;

    public TravelService(User activeUser) {
        this.logger = Logger.getLogger(TravelService.class.getName());
        logger.setLevel(Debug.logLevel);

        this.travelRepository = new TravelRepository();
        this.travelActivitiesRepository = new TravelActivitiesRepository();
        this.activityRepository = new ActivityRepository();

        this.travels = new ArrayList<>();
        this.travelActivitiesMap = new HashMap<>();
        this.activeUser = activeUser;

        loadTravelsForActiveUser();
        loadActivities();
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
        this.travels.addAll(this.travelRepository.loadTravelListByUser(this.activeUser));
    }

    public void loadActivities() {
        this.travels.forEach(travel -> {
            List<Long> activityIds = new CopyOnWriteArrayList<>();
            this.travelActivitiesRepository.getTravelActivitiesByTravel(travel.getId()).forEach(activity -> {
                activityIds.add(activity.getId());
            });
            this.travelActivitiesMap.put(travel.getId(), activityIds);
        });
    }

    public void addTravel(Travel travel) {
        this.travels.add(travel);
        this.travelRepository.persistTravelList(this.travels);
    }

    public void removeTravel(Travel travel) {
        this.travels.clear();
        this.travelRepository.removeTravelByTravelId(travel.getId());
        this.travels.addAll(this.travelRepository.loadTravelListByUser(this.activeUser));
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

    public void addActivityToTravel(Long travelId, Long activityId) {
        if (this.travelActivitiesMap.containsKey(travelId)) {
            this.travelActivitiesMap.get(travelId).add(activityId);
        } else {
            List<Long> activities = new ArrayList<>();
            activities.add(activityId);
            this.travelActivitiesMap.put(travelId, activities);
        }
        this.travelActivitiesRepository.persistTravelActivityMap(this.travelActivitiesMap);
    }

    public void removeActivityFromTravel(Long travelId, Long activityId) {
        if (this.travelActivitiesMap.containsKey(travelId)) {
            this.travelActivitiesMap.get(travelId).remove(activityId);
            this.travelActivitiesRepository.removeTravelActivity(travelId, activityId);
        }
    }

    public List<Activity> getActivitiesForTravelId(Long travelId) {
        if (this.travelActivitiesMap.containsKey(travelId)) {
            List<Activity> activities = new CopyOnWriteArrayList<>();
            this.travelActivitiesMap.get(travelId).forEach(activityId -> {
                activities.add(this.activityRepository.getActivityById(activityId));
            });
            return activities;
        }
        return new ArrayList<>();
    }
}
