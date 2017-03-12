package by.gstu.hotelsystem.models;

import by.gstu.hotelsystem.enumeration.ClientTypeEnum;
import by.gstu.hotelsystem.enumeration.OrderStatusEnum;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Created by Pavel on 13.01.2017.
 */
public class Order extends Regulation{

    private LocalDate orderEntry;
    private LocalDate orderExit;
    private Account orderAccount;
    private Apartment orderApartment;
    private OrderStatusEnum statusEnum;
    private String orderApartmentClass;
    private int orderApartmentBed;
    private String nameOrderAccount;

    public Order(){
    }
    public Order(LocalDate start,LocalDate end,Account ac,Apartment apart,OrderStatusEnum orderStatusEnum){
        orderExit=end;
        orderEntry=start;
        orderAccount=ac;
        orderApartment=apart;
        orderApartmentClass=orderApartment.getClassOfApartment();
        orderApartmentBed=orderApartment.getNumberOfBed();
        statusEnum= orderStatusEnum;
        nameOrderAccount=orderAccount.getLogin();
    }
    public String getNameOrderAccount()
    {return nameOrderAccount;}
    public void setNameOrderAccount(String name)
    {nameOrderAccount=name;}
    public void setStatusEnum(String orderStatusEnum)
    {this.statusEnum = statusEnum.valueOf(orderStatusEnum);}

    public OrderStatusEnum getStatusEnum()
    {return statusEnum;}

    public LocalDate getOrderEntry() {
        return orderEntry;
    }

    public void setOrderEntry(LocalDate startDate) {
        this.orderEntry = startDate;
    }

    public LocalDate getOrderExit() {
        return orderExit;
    }

    public void setOrderExit(LocalDate endDate) {
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
    public String getOrderApartmentClass ()
    {return orderApartmentClass;}
    public  void setOrderApartmentClass(String orderApartmentClass)
    {
        this.orderApartmentClass=orderApartmentClass;
    }
    public int getOrderApartmentBed()
    {return orderApartmentBed;}
    public  void setOrderApartmentBed(int orderApartmentBed)
    {
        this.orderApartmentBed=orderApartmentBed;
    }

}
