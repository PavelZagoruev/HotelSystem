package by.gstu.hotelsystem.models;

import by.gstu.hotelsystem.enumeration.TripStatusEnum;

import java.time.ZonedDateTime;

/**
 * Created by Pavel on 13.01.2017.
 */
public class Order extends Regulation{

    private ZonedDateTime orderEntry;
    private ZonedDateTime orderExit;
    private Account orderAccount;
    private Apartment orderApartment;
    private TripStatusEnum statusEnum;

    public Order(){
    }
    public Order(int id,ZonedDateTime start,ZonedDateTime end,Account ac,Apartment apart,TripStatusEnum tripStatusEnum){
        super(id);
        orderExit=end;
        orderEntry=start;
        orderAccount=ac;
        orderApartment=apart;
        statusEnum=tripStatusEnum;
    }
    public TripStatusEnum getStatus()
    { return statusEnum;}
    public void setStatus(TripStatusEnum tripStatusEnum)
    {statusEnum=tripStatusEnum;}
    public ZonedDateTime getOrderEntry() {
        return orderEntry;
    }

    public void setOrderEntry(ZonedDateTime startDate) {
        this.orderEntry = startDate;
    }

    public ZonedDateTime getOrderExit() {
        return orderExit;
    }

    public void setOrderExit(ZonedDateTime endDate) {
        this.orderExit = endDate;
    }

    public int getAccountId() {
        return orderAccount.getId();
    }

    public void setOrderAccount(Account orderAccount) {
        this.orderAccount = orderAccount;
    }

    public int getApartmentId() {
        return orderApartment.getId();
    }

    public void setOrderApartment(Apartment orderApartment) {
        this.orderApartment = orderApartment;
    }
}
