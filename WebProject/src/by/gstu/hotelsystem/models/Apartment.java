package by.gstu.hotelsystem.models;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by Pavel on 13.01.2017.
 */
public class Apartment extends Regulation {
    private int numberOfBed;
    private String classOfApartment;
    private String hotelName;
    private Hotel hotelOfApartment;

    public Apartment(){
    }
    public Apartment(int id,int numBed,String classApart,Hotel hotName){
        super(id);
        numberOfBed=numBed;
        classOfApartment=classApart;
        hotelName=hotName.getName();
        hotelOfApartment=hotName;

    }
    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void setNumberOfBed(int numberOfBed) {
        this.numberOfBed = numberOfBed;
    }

    public String getClassOfApartment() {
        return classOfApartment;
    }

    public void setClassOfApartment(String classOfApartment) {
        this.classOfApartment = classOfApartment;
    }

    public void setHotelName(String hotelName1){this.hotelName=hotelName1;}

    public String getHotelName(){return hotelName;}
}