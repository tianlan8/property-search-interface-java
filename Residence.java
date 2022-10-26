/**
 * @author Tian Lan
 * @version 1.0
 * This class models a child class (Residence Property) of Property class.
 */
public class Residence extends Property
{
    private final int     numberOfBedrooms;
    private final boolean hasSwimmingPool;
    private final boolean isPartOfStrata;

    private static final int VALID_MIN_NUM_OF_BEDROOMS = 1;
    private static final int VALID_MAX_NUM_OF_BEDROOMS = 20;

    /**
     * Constructor, valid all property information before initialize the object.
     * @param priceInUsd The price of the property, in USD, must be positive.
     * @param address The address of the property, cannot be null.
     * @param propertyType The property type of the property, must be one of the valid types.
     * @param propertyId The ID of the property, has length limit.
     * @param numberOfBedrooms The number of bedrooms of the property, must not be less than the minimum value.
     * @param hasSwimmingPool True if the property has a swimming pool, false otherwise.
     * @param isPartOfStrata True if the property is part of a strata, false otherwise.
     * @throws IllegalArgumentException If the parameter values are not valid.
     */
    public Residence(final double priceInUsd,
                     final Address address,
                     final int numberOfBedrooms,
                     final boolean hasSwimmingPool,
                     final String propertyType,
                     final String propertyId,
                     final boolean isPartOfStrata)
    {
        super(priceInUsd, address, propertyType, propertyId);

        if(numberOfBedrooms < VALID_MIN_NUM_OF_BEDROOMS || numberOfBedrooms > VALID_MAX_NUM_OF_BEDROOMS)
        {
            throw new IllegalArgumentException("Invalid number of bedrooms: " + numberOfBedrooms);
        }

        this.numberOfBedrooms = numberOfBedrooms;
        this.hasSwimmingPool  = hasSwimmingPool;
        this.isPartOfStrata   = isPartOfStrata;
    }

    /**
     * @return The number of bedrooms of the property.
     */
    public int getNumberOfBedrooms()
    {
        return numberOfBedrooms;
    }

    /**
     * @return True if the property has a swimming pool, false otherwise.
     */
    public boolean hasSwimmingPool()
    {
        return hasSwimmingPool;
    }

    /**
     * @return True if the property is part of a strata, false otherwise.
     */
    public boolean isPartOfStrata()
    {
        return isPartOfStrata;
    }

    /**
     * @return The Residence class is represented by a String,
     * displaying all attributes in Residence class and in the parent class Property.
     */
    @Override
    public String toString()
    {
        return "Residence [" +
                "numberOfBedrooms=" + numberOfBedrooms +
                ", swimmingPool=" + hasSwimmingPool +
                ", strata=" + isPartOfStrata +
                ", toString()=" + super.toString() +
                ']';
    }
}
