package main.java.property;

import main.java.address.Address;

/**
 * Represents a commercial property.
 * Each commercial property has a price, address, loading dock availability,
 * highway access availability, type and a unique property ID.
 *
 * @author Amir Roshan
 * @version 1.0
 */
public class Commercial extends Property
{
    private final boolean loadingDock;
    private final boolean highwayAccess;

    /**
     * Constructs a new Commercial object with the specified parameters.
     *
     * @param priceInUsd the price of the commercial property in USD
     * @param address the address of the commercial property
     * @param loadingDock true if the commercial property has a loading dock, false otherwise
     * @param highwayAccess true if the commercial property has highway access, false otherwise
     * @param propertyId the unique ID of the commercial property
     * @throws NullPointerException if address or propertyId is null
     */
    public Commercial(final double priceInUsd,
                      final Address address,
                      final String  type,
                      final String propertyId,
                      final boolean loadingDock,
                      final boolean highwayAccess)
    {
        super(priceInUsd, address, type, propertyId);

        this.loadingDock = loadingDock;
        this.highwayAccess = highwayAccess;
    }

    /**
     * Returns true if the commercial property has a loading dock, false otherwise.
     *
     * @return true if the commercial property has a loading dock, false otherwise
     */
    public boolean hasLoadingDock()
    {
        return loadingDock;
    }

    /**
     * Returns true if the commercial property has highway access, false otherwise.
     *
     * @return true if the commercial property has highway access, false otherwise
     */
    public boolean hasHighwayAccess()
    {
        return highwayAccess;
    }

    /**
     * Returns a string representation of the commercial property.
     *
     * @return a string representation of the commercial property
     */
    @Override
    public String toString()
    {
        return super.toString() + "Loading Dock: " + loadingDock + "\n" +
               "Highway Access: " + highwayAccess;
    }
}
