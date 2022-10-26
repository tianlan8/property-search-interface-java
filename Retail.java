/**
 * @author Tian Lan
 * @version 1.0
 * This class models a child class (Retail Property) of Property class.
 */
public class Retail extends Property
{
    private final int     squareFootage;
    private final boolean hasCustomerParking;

    private static final int VALID_MIN_SQUARE_FOOTAGE = 0;
    private static final int VALID_MAX_SQUARE_FOOTAGE = 10000;

    /**
     * Constructor, valid all property information before initialize the object.
     * @param priceInUsd The price of the property, in USD, must be positive.
     * @param address The address of the property, cannot be null.
     * @param propertyType The property type of the property, must be one of the valid types.
     * @param propertyId The ID of the property, has length limit.
     * @param squareFootage The amount of floor space available, has value limit.
     * @param hasCustomerParking True if the property has a customer parking, false otherwise.
     * @throws IllegalArgumentException If the parameter values are not valid.
     */
    public Retail(final double priceInUsd,
                  final Address address,
                  final String propertyType,
                  final String propertyId,
                  final int squareFootage,
                  final boolean hasCustomerParking)
    {
        super(priceInUsd, address, propertyType, propertyId);

        if(squareFootage < VALID_MIN_SQUARE_FOOTAGE || squareFootage > VALID_MAX_SQUARE_FOOTAGE)
        {
            throw new IllegalArgumentException("Invalid amount of floor space: " + squareFootage);
        }

        this.squareFootage      = squareFootage;
        this.hasCustomerParking = hasCustomerParking;
    }

    /**
     * @return The amount of floor space available.
     */
    public int getSquareFootage()
    {
        return squareFootage;
    }

    /**
     * @return True if the property has a customer parking, false otherwise.
     */
    public boolean hasCustomerParking()
    {
        return hasCustomerParking;
    }

    /**
     * @return The Retail class is represented by a String,
     * displaying all attributes in Retail class and in the parent class Property.
     */
    @Override
    public String toString()
    {
        return "Retail [" +
                "squareFootage=" + squareFootage +
                ", customerParking=" + hasCustomerParking +
                ", toString=" + super.toString() +
                ']';
    }
}
