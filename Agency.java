import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Tian Lan
 * @version 2.0
 * This class models an agency.
 */
public class Agency
{
    private final String                name;
    private final Map<String, Property> Properties;
    public static final int VALID_MIN_NAME_LENGTH = 1;
    public static final int VALID_MAX_NAME_LENGTH = 30;
    public static final int VALID_MIN_RETURN_SIZE = 0;

    /**
     * Constructor, valid all agency information before initialize the object.
     * @param name The name of the agency, has length limit.
     * @throws IllegalArgumentException If the length of the name exceed the limit.
     */
    public Agency(final String name)
    {
        if (name.length() < VALID_MIN_NAME_LENGTH || name.length() > VALID_MAX_NAME_LENGTH)
        {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
        this.name  = name;

        Properties = new HashMap<>();
    }

    /**
     * This method adds the non-null property to the HashMap Properties.
     * @param property The property to be added.
     */
    public void addProperty(final Property property)
    {
        if (property != null)
        {
            Properties.put(property.getPropertyId(), property);
        }
    }

    /**
     * This method removes the property whose ID matches the parameter, from the HashMap Properties.
     * @param propertyId The property ID to be removed.
     */
    public void removeProperty(final String propertyId)
    {
        Properties.remove(propertyId);
    }

    /**
     * @param propertyId The property ID to be matched.
     * @return The property whose ID matches the parameter from the HashMap, or null if there is no match.
     */
    public Property getProperty(final String propertyId)
    {
        return Properties.get(propertyId);
    }

    /**
     * @return The total amount in USD of all Properties.
     */
    public double getTotalPropertyValues()
    {
        double totalPropertyValues;
        Set<String> propertyIds;

        totalPropertyValues = 0;
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            totalPropertyValues += Properties.get(propertyId).getPriceUsd();
        }
        return totalPropertyValues;
    }

    /**
     * @return An ArrayList of the Residence properties with the swimming pool, or null if there are none.
     */
    public ArrayList<Residence> getPropertiesWithPools()
    {
        ArrayList<Residence> propertiesWithPools;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithPools = new ArrayList<>();
        propertyIds = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if(oneProperty instanceof Residence)
            {
                Residence oneResidence = (Residence) oneProperty;
                if(oneResidence.hasSwimmingPool())
                {
                    propertiesWithPools.add(oneResidence);
                }
            }
        }

        if (propertiesWithPools.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithPools;
    }

    /**
     * @param minUsd The minimum price of the property, in USD.
     * @param maxUsd The maximum price of the property, in USD.
     * @return An array of properties whose price falls in the range specified by the parameters,
     * or null if there are none.
     */
    public Property[] getPropertiesBetween(final double minUsd, final double maxUsd)
    {
        Property[] propertiesBetween;
        Set<String> propertyIds;
        int i;
        int counter;

        i = 0;
        counter = 0;
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            double propertyPriceInUsd;
            propertyPriceInUsd = Properties.get(propertyId).getPriceUsd();

            if (propertyPriceInUsd >= minUsd && propertyPriceInUsd <= maxUsd)
            {
                counter++;
            }
        }

        propertiesBetween = new Property[counter];

        for (String propertyId : propertyIds)
        {
            double propertyPriceInUsd;
            propertyPriceInUsd = Properties.get(propertyId).getPriceUsd();

            if (propertyPriceInUsd >= minUsd && propertyPriceInUsd <= maxUsd)
            {
                propertiesBetween[i] = Properties.get(propertyId);
                i++;
            }
        }

        if (propertiesBetween.length == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesBetween;
    }

    /**
     * @param streetName The street name to be matched.
     * @return An ArrayList of addresses which are on the specified street name, or null if there are none.
     */
    public ArrayList<Address> getPropertiesOn(final String streetName)
    {
        ArrayList<Address> propertiesOnStreet;
        Set<String> propertyIds;
        String propertyStreetName;
        propertiesOnStreet = new ArrayList<>();
        propertyIds = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            propertyStreetName = Properties.get(propertyId).getAddress().getStreetName();
            if (propertyStreetName.equalsIgnoreCase(streetName))
            {
                propertiesOnStreet.add(Properties.get(propertyId).getAddress());
            }
        }
        return propertiesOnStreet;
    }

    /**
     * @param minBedrooms The minimum number of bedrooms in the range.
     * @param maxBedrooms The maximum number of bedrooms in the range.
     * @return A HashMap of Residence properties (key is property id, value is the Property)
     * whose number of bedrooms falls in the range specified by the parameters, or null if there are none.
     */
    public HashMap<String, Residence> getPropertiesWithBedrooms(final int minBedrooms, final int maxBedrooms)
    {
        HashMap<String, Residence> propertiesWithBedrooms;
        Set<String> propertyIds;
        int numOfBedrooms;
        Property oneProperty;

        propertiesWithBedrooms = new HashMap<>();
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if (oneProperty instanceof Residence)
            {
                Residence oneResidence = (Residence) oneProperty;
                numOfBedrooms = oneResidence.getNumberOfBedrooms();
                if (numOfBedrooms >= minBedrooms && numOfBedrooms <= maxBedrooms)
                {
                    propertiesWithBedrooms.put(propertyId, oneResidence);
                }
            }
        }

        if (propertiesWithBedrooms.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithBedrooms;
    }

    /**
     * @param propertyType The property type to be matched.
     * @return An ArrayList of Property, with all of the information about every subtype property
     * that matches the specified property type (case-insensitive), with certain format.
     */
    public ArrayList<Property> getPropertiesOfType(final String propertyType)
    {
        ArrayList<Property> propertiesOfType;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesOfType = new ArrayList<>();
        propertyIds = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if(oneProperty.getType().equalsIgnoreCase(propertyType))
            {
                propertiesOfType.add(oneProperty);
            }
        }
        return propertiesOfType;
    }

    /**
     * @return An ArrayList<Commercial> that holds only Commercial properties that have a loading dock available.
     */
    public ArrayList<Commercial> getPropertiesWithLoadingDocks()
    {
        ArrayList<Commercial> propertiesWithLoadingDock;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithLoadingDock = new ArrayList<>();
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if (oneProperty instanceof Commercial)
            {
                Commercial oneCommercial = (Commercial) oneProperty;
                if (oneCommercial.hasLoadingDock())
                {
                    propertiesWithLoadingDock.add(oneCommercial);
                }
            }
        }

        if (propertiesWithLoadingDock.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithLoadingDock;
    }

    /**
     * @return An ArrayList<Commercial> that holds only Commercial properties that have highway access.
     */
    public ArrayList<Commercial> getPropertiesWithHighwayAccess()
    {
        ArrayList<Commercial> propertiesWithHighwayAccess;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithHighwayAccess = new ArrayList<>();
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if (oneProperty instanceof Commercial)
            {
                Commercial oneCommercial = (Commercial) oneProperty;
                if (oneCommercial.hasHighwayAccess())
                {
                    propertiesWithHighwayAccess.add(oneCommercial);
                }
            }
        }

        if (propertiesWithHighwayAccess.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithHighwayAccess;
    }

    /**
     * @param squareFootage The minimum square footage.
     * @return An ArrayList<Retail> that holds properties where square footage is at least the parameter value.
     */
    public ArrayList<Retail> getPropertiesSquareFootage(final int squareFootage)
    {
        ArrayList<Retail> propertiesWithSquareFootage;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithSquareFootage = new ArrayList<>();
        propertyIds = Properties.keySet();

        for(String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);
            if (oneProperty instanceof Retail)
            {
                Retail oneRetail = (Retail) oneProperty;
                if (oneRetail.getSquareFootage() >= squareFootage)
                {
                    propertiesWithSquareFootage.add(oneRetail);
                }
            }
        }

        if (propertiesWithSquareFootage.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithSquareFootage;
    }

    /**
     * @return An ArrayList<Retail> that holds properties where customer parking is available.
     */
    public ArrayList<Retail> getPropertiesWithCustomerParking()
    {
        ArrayList<Retail> propertiesWithCustomerParking;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithCustomerParking = new ArrayList<>();
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if (oneProperty instanceof Retail)
            {
                Retail oneRetail = (Retail) oneProperty;
                if (oneRetail.hasCustomerParking())
                {
                    propertiesWithCustomerParking.add(oneRetail);
                }
            }
        }

        if (propertiesWithCustomerParking.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithCustomerParking;
    }

    /**
     * @return An ArrayList<Residence> that hold only the Residences that are in a strata.
     */
    public ArrayList<Residence> getPropertiesWithStrata()
    {
        ArrayList<Residence> propertiesWithStrata;
        Set<String> propertyIds;
        Property oneProperty;

        propertiesWithStrata = new ArrayList<>();
        propertyIds = Properties.keySet();

        for (String propertyId : propertyIds)
        {
            oneProperty = Properties.get(propertyId);

            if (oneProperty instanceof Residence)
            {
                Residence oneResidence = (Residence) oneProperty;
                if (oneResidence.isPartOfStrata())
                {
                    propertiesWithStrata.add(oneResidence);
                }
            }
        }

        if (propertiesWithStrata.size() == VALID_MIN_RETURN_SIZE)
        {
            return null;
        }
        return propertiesWithStrata;
    }
}
