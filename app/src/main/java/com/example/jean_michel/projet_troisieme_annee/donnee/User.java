package com.example.jean_michel.projet_troisieme_annee.donnee;

import android.content.Context;

import com.example.jean_michel.projet_troisieme_annee.DAO.TripDAO;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String lastName;
    private String firstName;

    private List<Trip> trips;

    public static User connectedUser;

    // BDD Recuperation
    public User(int id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        trips = new ArrayList<>();
    }

    // New one
    public User(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        trips = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<Trip> getTrips(Context context) {
        TripDAO tripDAO = new TripDAO(context);
        tripDAO.open();
        List<Trip> trips1 = tripDAO.findTripsByUserId(this);
        tripDAO.close();
        trips = trips1;
        return trips;
    }

    // Create trip in BDD
    public Trip addTrip(Trip trip, Context context) {
        TripDAO tripDAO = new TripDAO(context);
        tripDAO.open();
        Trip trip1 = tripDAO.createTrip(trip);
        tripDAO.close();
        this.trips.add(trip1);
        return trip1;
    }
}
