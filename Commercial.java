/**
 * @author Tian Lan
 * @version 1.0
 * This class models a child class (Commercial Property) of Property class.
 */
public class Commercial extends Property
{
    private final boolean hasLoadingDock;
    private final boolean hasHighwayAccess;

    /**
     * Constructor, valid all address information before initialize the object.
     * @param priceInUsd The price of the property, in USD, must be positive.
     * @param address The address of the property, cannot be null.
     * @param propertyType The property type of the property, must be one of the valid types.
     * @param propertyId The ID of the property, has length limit.
     * @param hasLoadingDock True if the property has a loading dock, false otherwise.
     * @param hasHighwayAccess True if the property has a highway access, false otherwise.
     */
    public Commercial(final double priceInUsd,
                      final Address address,
                      final String propertyType,
                      final String propertyId,
                      final boolean hasLoadingDock,
                      final boolean hasHighwayAccess)
    {
        super(priceInUsd, address, propertyType, propertyId);
        this.hasLoadingDock   = hasLoadingDock;
        this.hasHighwayAccess = hasHighwayAccess;
    }

    /**
     * @return True if the property has a loading dock, false otherwise.
     */
    public boolean hasLoadingDock()
    {
        return hasLoadingDock;
    }

    /**
     * @return True if the property has a highway access, false otherwise.
     */
    public boolean hasHighwayAccess()
    {
        return hasHighwayAccess;
    }

    /**
     * @return The Commercial class is represented by a String,
     * displaying all attributes in Commercial class and in the parent class Property.
     */
    @Override
    public String toString()
    {
        return "Commercial [" +
                "loadingDock=" + hasLoadingDock +
                ", highwayAccess=" + hasHighwayAccess +
                ", toString=" + super.toString() +
                ']';
    }
}
