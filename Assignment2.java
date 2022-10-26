import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Tian Lan
 * @version 1.0
 * This class models a user interface to perform search.
 */
public class Assignment2
{
    private final Agency agency;
    public static final String ADDRESS_READER_FILE = "address_data.txt";
    public static final String PROPERTY_READER_FILE = "property_data.txt";

    public Assignment2()
    {
        agency = new Agency("Tian's Agency");
    }

    /**
     * This method gets the ArrayList from AddressReader and PropertyReader,
     * and uses them to create subtype Objects and adds them to the Agency.HashMap.
     * @throws FileNotFoundException if the file name does not exist.
     */
    public void init() throws FileNotFoundException
    {
        ArrayList<Address> addressesDataReadFromFile;
        ArrayList<String> propertiesDataReadFromFile;

        String[] onePropertyRawData;
        double priceInUsd;
        Address address;
        String propertyType;
        String propertyId;
        int numberOfBedrooms;
        int squareFootage;
        int addressIndex;
        boolean hasSwimmingPool;
        boolean isPartOfStrata;
        boolean hasCustomerParking;
        boolean hasLoadingDock;
        boolean hasHighwayAccess;
        Residence newResidence;
        Commercial newCommercial;
        Retail newRetail;

        File addressDataFile = new File(ADDRESS_READER_FILE);
        File propertyDataFile = new File(PROPERTY_READER_FILE);

        addressesDataReadFromFile = AddressReader.readAddressData(addressDataFile);
        propertiesDataReadFromFile = PropertyReader.readPropertyData(propertyDataFile);

        addressIndex = 0;
        for(String onePropertyData: propertiesDataReadFromFile)
        {
            onePropertyRawData = onePropertyData.split("\\|");

            if(onePropertyRawData[3].equalsIgnoreCase("residence"))
            {
                priceInUsd = Double.parseDouble(onePropertyRawData[0]);
                address = addressesDataReadFromFile.get(addressIndex);
                numberOfBedrooms = Integer.parseInt(onePropertyRawData[1]);
                hasSwimmingPool = Boolean.parseBoolean(onePropertyRawData[2]);
                propertyType = onePropertyRawData[3];
                propertyId = onePropertyRawData[4];
                isPartOfStrata = Boolean.parseBoolean(onePropertyRawData[5]);

                newResidence = new Residence(priceInUsd, address, numberOfBedrooms, hasSwimmingPool, propertyType, propertyId, isPartOfStrata);
                agency.addProperty(newResidence);
            }
            else if(onePropertyRawData[1].equalsIgnoreCase("commercial"))
            {
                priceInUsd = Double.parseDouble(onePropertyRawData[0]);
                address = addressesDataReadFromFile.get(addressIndex);
                propertyType = onePropertyRawData[1];
                propertyId = onePropertyRawData[2];
                hasLoadingDock = Boolean.parseBoolean(onePropertyRawData[3]);
                hasHighwayAccess = Boolean.parseBoolean(onePropertyRawData[4]);

                newCommercial = new Commercial(priceInUsd, address, propertyType, propertyId, hasLoadingDock, hasHighwayAccess);
                agency.addProperty(newCommercial);
            }
            else
            {
                priceInUsd = Double.parseDouble(onePropertyRawData[0]);
                address = addressesDataReadFromFile.get(addressIndex);
                propertyType = onePropertyRawData[1];
                propertyId = onePropertyRawData[2];
                squareFootage = Integer.parseInt(onePropertyRawData[3]);
                hasCustomerParking = Boolean.parseBoolean(onePropertyRawData[4]);

                newRetail = new Retail(priceInUsd, address, propertyType, propertyId, squareFootage, hasCustomerParking);
                agency.addProperty(newRetail);
            }
            addressIndex++;
        }
    }

    /**
     * This method provides the primary user interface through command prompts that will allow the user
     * to choose which search operations to perform.
     * Welcome to our Property search.
     * Choose one of the following options:
     * 1.General Queries – will present the General Queries menu
     * 2.Residence Queries – will present the Residence Queries menu
     * 3.Commercial Queries – will present the Commercial Queries menu
     * 4.Retail Queries – will present the Retail Queries menu
     * 5.Exit – will exit the program
     */
    public void doSearches()
    {
        String userInput;
        Scanner researchScanner;

        researchScanner = new Scanner(System.in);

        welcomeOptions();

        if(researchScanner.hasNext())
        {
            userInput = researchScanner.nextLine();

            if(userInput.equalsIgnoreCase("1"))
            {
                generalQueries();
                generalQueriesOptions();
            }
            else if(userInput.equalsIgnoreCase("2"))
            {
                residenceQueries();
                residenceQueriesOptions();
            }
            else if(userInput.equalsIgnoreCase("3"))
            {
                commercialQueries();
                commercialQueriesOptions();
            }
            else if(userInput.equalsIgnoreCase("4"))
            {
                retailQueries();
                retailQueriesOptions();
            }
            else if(userInput.equalsIgnoreCase("5"))
            {
                System.out.println("Goodbye for now!");
                researchScanner.close();
            }
        }
    }

    /**
     * This method displays the options in "Welcome" interface.
     */
    public void welcomeOptions()
    {
        System.out.println("Welcome to our Property search.");
        System.out.println("Choose one of the following options:");
        System.out.println("\t1. General Queries");
        System.out.println("\t2. Residence Queries");
        System.out.println("\t3. Commercial Queries");
        System.out.println("\t4. Retail Queries");
        System.out.println("\t5. Exit");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method displays the options in "General Query" interface.
     */
    public void generalQueries()
    {
        System.out.println("General Queries");
        System.out.println("\t1.By Property ID");
        System.out.println("\t2.By Price");
        System.out.println("\t3.By Street");
        System.out.println("\t4.By Type");
        System.out.println("\t5.Back");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method displays the options in "Residence Query" interface.
     */
    public void residenceQueries()
    {
        System.out.println("Residence Queries");
        System.out.println("\t1.By Bedroom");
        System.out.println("\t2.By Pool");
        System.out.println("\t3.By Strata");
        System.out.println("\t4.Back");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method displays the options in "Commercial Query" interface.
     */
    public void commercialQueries()
    {
        System.out.println("Commercial Queries");
        System.out.println("\t1.By Loading Dock");
        System.out.println("\t2.By Highway Access");
        System.out.println("\t3.Back");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method displays the options in "Retail Query" interface.
     */
    public void retailQueries()
    {
        System.out.println("Retail Queries");
        System.out.println("\t1.By Square Footage");
        System.out.println("\t2.By Customer Parking");
        System.out.println("\t3.Back");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method directs the user back to the "Welcome" interface and re-run the searching function.
     */
    public void backToMainMenu()
    {
        doSearches();
    }

    /**
     * This method displays results to the console for each option the user choose.
     * General Queries
     * 1.By Property ID – will further prompt to ender Property ID, then call Agency.getProperty(propertyId)
     * and display the result to the console
     * 2.By Price – will further prompt to enter both min and max ranges, call Agency.getPropertiesBetween(minUsd, maxUsd)
     * and display results to the console
     * 3.By Street –will further prompt to enter the Street name, call Agency.getPropertiesOn(streetName)
     * and display results to the console
     * 4.By Type – will further prompt to enter the property type (residence, commercial, retail),
     * call Agency.getPropertiesOfType(propertyType) and display results to the console
     * 5.Back – will take you back to the main menu
     */
    public void generalQueriesOptions()
    {
        String userChoice;
        String propertyId;
        String streetName;
        String propertyType;
        double minPriceUsd;
        double maxPriceUsd;
        Property[] properties;
        ArrayList<Property> propertiesOfType;
        ArrayList<Address> propertiesOn;

        minPriceUsd = 0.0;
        maxPriceUsd = 0.0;
        propertiesOfType = new ArrayList<>();
        propertiesOn = new ArrayList<>();

        Scanner generalScanner = new Scanner(System.in);
        userChoice = generalScanner.nextLine();

        if(userChoice.equalsIgnoreCase("1"))
        {
            System.out.println("Enter Property ID:");
            if(generalScanner.hasNext())
            {
                propertyId = generalScanner.nextLine();
                System.out.println(agency.getProperty(propertyId));
            }

            generalQueries();
            generalQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("2"))
        {
            System.out.println("Enter minimum selling price:");

            if(generalScanner.hasNext())
            {
                minPriceUsd = generalScanner.nextDouble();
            }
            System.out.println("Enter maximum selling price:");

            if(generalScanner.hasNext())
            {
                maxPriceUsd = generalScanner.nextDouble();
            }

            properties = agency.getPropertiesBetween(minPriceUsd, maxPriceUsd);

            for(Property p : properties)
            {
                System.out.println(p);
            }
            generalQueries();
            generalQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("3"))
        {
            System.out.println("Enter the street name:");
            if(generalScanner.hasNext())
            {
                streetName = generalScanner.nextLine();
                propertiesOn = agency.getPropertiesOn(streetName);
            }

            for(Address a : propertiesOn)
            {
                System.out.println(a);
            }
            generalQueries();
            generalQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("4"))
        {
            System.out.println("Enter the property type:");
            if(generalScanner.hasNext())
            {
                propertyType = generalScanner.nextLine();
                propertiesOfType = agency.getPropertiesOfType(propertyType);
            }

            for(Property p : propertiesOfType)
            {
                System.out.println(p);
            }
            generalQueries();
            generalQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("5"))
        {
            backToMainMenu();
        }
    }

    /**
     * This method displays results to the console for each option the user choose.
     * Residence Queries
     * 1.By Bedroom – will further prompt to enter the min and max ranges,
     * call Agency.getPropertiesWithBedrooms(minBedrooms, maxBedrooms)
     * 2.By Pool – will call Agency.getPropertiesWithPools() and display only the residences that have swimming pools
     * 3.By Strata – will call Agency.getPropertiesWithStrata() and display the results to the console
     * 4.Back – will take you back to the main menu
     */
    public void residenceQueriesOptions()
    {
        String userChoice;
        int minNumOfBedrooms = 0;
        int maxNumOfBedrooms = 0;
        HashMap<String, Residence> propertiesBedrooms;
        ArrayList<Residence> propertiesWithPool;
        ArrayList<Residence> propertiesWithStrata;

        Scanner residenceScanner = new Scanner(System.in);
        userChoice = residenceScanner.nextLine();

        if(userChoice.equalsIgnoreCase("1"))
        {
            System.out.println("Enter minimum number of bedroom:");
            if(residenceScanner.hasNext())
            {
                minNumOfBedrooms = residenceScanner.nextInt();
            }
            System.out.println("Enter maximum number of bedroom:");
            if(residenceScanner.hasNext())
            {
                maxNumOfBedrooms = residenceScanner.nextInt();
            }
            propertiesBedrooms = agency.getPropertiesWithBedrooms(minNumOfBedrooms, maxNumOfBedrooms);

            for(Residence r : propertiesBedrooms.values())
            {
                System.out.println(r);
            }
            residenceQueries();
            residenceQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("2"))
        {
            propertiesWithPool = agency.getPropertiesWithPools();
            for(Residence r : propertiesWithPool)
            {
                System.out.println(r);
            }
            residenceQueries();
            residenceQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("3"))
        {
            propertiesWithStrata = agency.getPropertiesWithStrata();
            for(Residence r : propertiesWithStrata)
            {
                System.out.println(r);
            }
            residenceQueries();
            residenceQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("4"))
        {
            backToMainMenu();
        }
    }

    /**
     * This method displays results to the console for each option the user choose.
     * Commercial Queries
     * 1.By Loading Dock – will call Agency.getPropertiesWithLoadingDock() and display results to the console
     * 2.By Highway Access – will call Agency.getPropertiesWithHighwayAccess() and display the results to the console
     * 3.Back – will take you back to the main menu
     */
    public void commercialQueriesOptions()
    {
        String userChoice;
        ArrayList<Commercial> propertiesLoadingDock;
        ArrayList<Commercial> propertiesHighwayAccess;

        Scanner commercialScanner = new Scanner(System.in);
        userChoice = commercialScanner.nextLine();

        if(userChoice.equalsIgnoreCase("1"))
        {
            propertiesLoadingDock = agency.getPropertiesWithLoadingDocks();
            for(Commercial c : propertiesLoadingDock)
            {
                System.out.println(c);
            }
            commercialQueries();
            commercialQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("2"))
        {
            propertiesHighwayAccess = agency.getPropertiesWithHighwayAccess();
            for(Commercial c : propertiesHighwayAccess)
            {
                System.out.println(c);
            }
            commercialQueries();
            commercialQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("3"))
        {
            backToMainMenu();
        }
    }

    /**
     * This method displays results to the console for each option the user choose.
     * Retail Queries
     * 1.By Square Footage – will further prompt for min square footage value,
     * call Agency.getProperties WithSquareFootage(int squareFootage) and display results to the console
     * 2.By Customer Parking – will call Agency.getPropertiesWithCustomerParking()
     * and display only Retail properties where customer parking is available
     * 3.Back – will take you back to the main menu
     */
    public void retailQueriesOptions()
    {
        String userChoice;
        int minSquareFootage;
        ArrayList<Retail> propertiesSquareFootage;
        ArrayList<Retail> propertiesCustomerParking;

        Scanner retailScanner = new Scanner(System.in);
        userChoice = retailScanner.nextLine();

        if(userChoice.equalsIgnoreCase("1"))
        {
            System.out.println("Enter the minimum square footage:");
            minSquareFootage = retailScanner.nextInt();
            propertiesSquareFootage = agency.getPropertiesSquareFootage(minSquareFootage);
            for(Retail rt : propertiesSquareFootage)
            {
                System.out.println(rt);
            }
            retailQueries();
            retailQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("2"))
        {
            propertiesCustomerParking = agency.getPropertiesWithCustomerParking();
            for(Retail rt : propertiesCustomerParking)
            {
                System.out.println(rt);
            }
            retailQueries();
            retailQueriesOptions();
        }
        else if(userChoice.equalsIgnoreCase("3"))
        {
            backToMainMenu();
        }
    }

    public static void main(final String[] args) throws FileNotFoundException
    {
        Assignment2 a2 = new Assignment2();
        a2.init();
        a2.doSearches();
    }
}
