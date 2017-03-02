package by.gstu.hotelsystem.models;

import java.util.ArrayList;

/**
 * Created by Pavel on 13.01.2017.
 */
public class Hotel extends Regulation{
    private String name;
    private ArrayList<Apartment> apartments;

    public Hotel()
    {}
    public Hotel(String name,ArrayList<Apartment> apart,int id)
    {
        super(id);
        this.name=name;
        apartments=apart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Apartment apartment) {
        this.apartments.add(apartment);
    }
}
