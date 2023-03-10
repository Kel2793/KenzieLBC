package com.kenzie.appserver.config;

import com.kenzie.appserver.service.model.Listing;
import net.andreinc.mockneat.MockNeat;
import java.text.DecimalFormat;
import static net.andreinc.mockneat.unit.types.Doubles.doubles;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class ListingGenerator {


    ArrayList<String> streetNames = new ArrayList<>();
    ArrayList<String> streetTypes = new ArrayList<>();
    ArrayList<String> listingStatus = new ArrayList<>();
    ArrayList<String> uuidGenArr = new ArrayList<>();
    Random random = new Random();
    MockNeat mock = MockNeat.threadLocal();
    StringBuilder sb = new StringBuilder();
    StringBuilder uuidGen = new StringBuilder();

    public ListingGenerator() {
        streetNames.add("Main");
        streetNames.add("1st");
        streetNames.add("2nd");
        streetNames.add("3rd");
        streetNames.add("Park");
        streetNames.add("Oak");
        streetNames.add("Maple");
        streetNames.add("Washington");
        streetNames.add("Cedar");
        streetNames.add("Walnut");
        streetNames.add("Sunset");
        streetNames.add("Church");
        streetNames.add("Lincoln");
        streetNames.add("Adams");
        streetNames.add("Cherry");
        streetNames.add("Marshall");
        streetNames.add("Airy");
        streetNames.add("Hill");
        streetNames.add("Forest");
        streetNames.add("Spruce");
        streetNames.add("Lafayette");
        streetNames.add("Ridge");
        streetNames.add("Markley");
        streetNames.add("Johnson");
        streetNames.add("Wilson");
        streetNames.add("Germantown");
        streetNames.add("Broad");
        streetNames.add("Jefferson");
        streetNames.add("Whitehall");
        streetNames.add("4th");
        streetNames.add("5th");
        streetNames.add("6th");
        streetNames.add("Markley");
        streetNames.add("West");
        streetNames.add("East");
        streetNames.add("Burnside");
        streetNames.add("Sharon");


        streetTypes.add("Street");
        streetTypes.add("Avenue");
        streetTypes.add("Boulevard");
        streetTypes.add("Circle");
        streetTypes.add("Lane");
        streetTypes.add("Drive");
        streetTypes.add("Way");

        listingStatus.add("For Sale");
        listingStatus.add("Sold");
        listingStatus.add("Withdrawn");
        listingStatus.add("Under Contract");

        uuidGenArr.add("A");
        uuidGenArr.add("B");
        uuidGenArr.add("C");
        uuidGenArr.add("D");
        uuidGenArr.add("E");
        uuidGenArr.add("F");
        uuidGenArr.add("G");
        uuidGenArr.add("H");
        uuidGenArr.add("I");
        uuidGenArr.add("J");
        uuidGenArr.add("K");
        uuidGenArr.add("L");
        uuidGenArr.add("M");
        uuidGenArr.add("N");
        uuidGenArr.add("O");
        uuidGenArr.add("P");
        uuidGenArr.add("Q");
        uuidGenArr.add("R");
        uuidGenArr.add("S");
        uuidGenArr.add("T");
        uuidGenArr.add("U");
        uuidGenArr.add("V");
        uuidGenArr.add("W");
        uuidGenArr.add("X");
        uuidGenArr.add("Y");
        uuidGenArr.add("Z");
        uuidGenArr.add("1");
        uuidGenArr.add("2");
        uuidGenArr.add("3");
        uuidGenArr.add("4");
        uuidGenArr.add("5");
        uuidGenArr.add("6");
        uuidGenArr.add("7");
        uuidGenArr.add("8");
        uuidGenArr.add("9");
        uuidGenArr.add("0");
    }

    public Listing generateListing() {

        String id = generateId();

        String houseNumberString = Integer.toString(random.nextInt(10000));
        String streetName = streetNames.get(random.nextInt(streetNames.size()));
        String streetType = streetTypes.get(random.nextInt(streetTypes.size()));

        String cityName = mock.cities().us().get();
        String stateName = mock.usStates().get();
        DecimalFormat format = new DecimalFormat("00000");
        int zipCode = random.nextInt(100000);
        String zipString = (format.format(zipCode));

        sb.append(houseNumberString).append(" ");
        sb.append(streetName).append(" ");
        sb.append(streetType).append(", ");
        sb.append(cityName).append(", ");
        sb.append(stateName).append(", ");
        sb.append(zipString);

        String finalAddress = sb.toString();
        sb.setLength(0);

        int squareFootage = random.nextInt((10000 - 500)) + 500;
        int price = random.nextInt(1500000);
        int numBedrooms = random.nextInt(7) + 1;
        double numBathrooms = doubles().from(new double[]{1.0, 1.5, 2.0, 2.5, 3.0, 3.5}).get();
        double lotSize = doubles().from(new double[]{0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0}).get();
        String status = listingStatus.get(random.nextInt(listingStatus.size()));

        return new Listing(id, finalAddress, squareFootage, price, numBedrooms, numBathrooms, lotSize, status);
    }

    public String generateId() {
        for(int i =0; i<8; i++) {
            uuidGen.append(uuidGenArr.get(random.nextInt(uuidGenArr.size())));
        }
        String returnedId = uuidGen.toString();
        uuidGen.setLength(0);
        return returnedId;
    }
}