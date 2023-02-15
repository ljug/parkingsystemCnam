package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }


//        int inHour = ticket.getInTime().getHours();
//        int outHour = ticket.getOutTime().getHours();

        long inMinutes = ticket.getInTime().getTime() / 1000 / 60;
        long outMinutes = ticket.getOutTime().getTime() / 1000 / 60;

        //TODO: Some tests are failing here. Need to check if this logic is correct
//        int duration = outHour - inHour;
        double duration = (outMinutes - inMinutes);
        duration /= 60;


        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
                ticket.setPrice(rate * duration * Fare.CAR_RATE_PER_HOUR);
                break;
            }
            case BIKE: {
                ticket.setPrice(rate * duration * Fare.BIKE_RATE_PER_HOUR);
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }
}