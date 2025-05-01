package com.test_deploy.API_Service.Entities;

import java.util.Objects;

import com.test_deploy.API_Service.Utils.Usage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;




@Entity
public class Vehicle {
    private @Id
    String VIN;
    private String make;
    private String model;
    private String year;
    private Usage usage;




    public Vehicle() {}

    public Vehicle(String VIN, String make, String model, String year, Usage usage) {

        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.year = year;
        this.usage = usage;
    }

    public Vehicle(String VIN, String make, String model, String year, String usage) {

        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.year = year;
        this.usage = Usage.valueOf(usage);
    }

    public String getVIN() {
        return VIN;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public Usage getUsage() {
        return usage;
    }

    public String getUsageString() {
        return usage.toString();
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public void setUsage(String usage) {
        this.usage = Usage.valueOf(usage);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.VIN, this.make, this.model, this.year, this.usage);
    }

    @Override
    public String toString() {
        return "Vehicle{" + "VIN='" + this.VIN + '\'' + ", make='" + this.make + '\'' + ", model='" + this.model + '\'' + ", year='" + this.year + '\'' + ", usage='" + this.usage + '\'' + '}';
    }

}


/*import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main { 
  public static void main(String[] args) { 
    
// Custom string as input
		String strDate = "29/12/1996";
 
        // Creating an object of Date class with reference
        // to SimpleDateFormat class and
        // lately parsing the above string into it
        try {
        	Date date = new SimpleDateFormat("dd/mm/yyyy").parse(strDate);
            String output = new SimpleDateFormat("dd/mm/yyyy").format(date);
        	System.out.print(strDate + " " + date);
        }
        catch (Exception e) {
        	System.out.println(e.getMessage());
        }
 
        // Print and display the date corresponding
        // to above input string
        
  } 
}
 */