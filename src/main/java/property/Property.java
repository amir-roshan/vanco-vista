package main.java.property;

import main.java.address.Address;

/**
 * Represents a real estate property.
 * Each property has a price, address, number of bedrooms, swimming pool availability,
 * type and a unique property ID.
 *
 * @author Amir Roshan
 * @version 1.0
 */
public class Property
{
    private static final int MIN_PRICE_IN_USD = 0;
    private static final int MIN_PROPERTY_ID_LENGTH = 1;
    private static final int MAX_PROPERTY_ID_LENGTH = 6;

    private final Address address;
    private final String type;
    private final String propertyId;
    private double priceUsd;

    /**
     * Constructs a new Property object with the specified parameters.
     *
     * @param priceInUsd the price of the property in USD
     * @param address the address of the property
     * @param type the type of the property (residence, commercial, retail)
     * @param propertyId the unique ID of the property
     * @throws NullPointerException if address, type, or propertyId is null
     */
    public Property(final double priceInUsd,
                    final Address address,
                    final String type,
                    final String propertyId)
    {
        // Validate price
        if(priceInUsd < MIN_PRICE_IN_USD)
        {
            throw new IllegalArgumentException("Invalid price: " + priceInUsd);
        }

        // Validate price
        if(address == null)
        {
            throw new NullPointerException("Invalid address: null");
        }

        // Validate type
        if(type == null)
        {
            throw new NullPointerException("Invalid property type: null");
        }
        else if(!(type.equalsIgnoreCase("residence") ||
                  type.equalsIgnoreCase("commercial") ||
                  type.equalsIgnoreCase("retail")))
        {
            throw new IllegalArgumentException("Invalid property type: " + type);
        }

        if(propertyId == null)
        {
            throw new NullPointerException("Invalid property id: null");
        }
        else if(propertyId.length() < MIN_PROPERTY_ID_LENGTH || propertyId.length() > MAX_PROPERTY_ID_LENGTH)
        {
            throw new IllegalArgumentException("Invalid property id: " + propertyId);
        }
        this.priceUsd = priceInUsd;
        this.address = address;
        this.type = type;
        this.propertyId = propertyId;
    }

    /**
     * Returns the price of the property.
     *
     * @return the price of the property in USD
     */
    public double getPriceUsd()
    {
        return priceUsd;
    }

    /**
     * Sets the price of the property.
     *
     * @param priceUsd the price of the property in USD
     */
    public void setPriceUsd(double priceUsd)
    {
        if(priceUsd < MIN_PRICE_IN_USD)
        {
            throw new IllegalArgumentException("Price is not accepted.");
        }
        this.priceUsd = priceUsd;
    }

    /**
     * Returns the price of the property.
     *
     * @return the price of the property in USD
     */
    public Address getAddress()
    {
        return address;
    }

    /**
     * Returns the type of the property.
     *
     * @return the type of the property (residence, commercial, retail)
     */
    public String getType()
    {
        return type;
    }

    /**
     * Returns the unique ID of the property.
     *
     * @return the property ID
     */
    public String getPropertyId()
    {
        return propertyId;
    }

    /**
     * Provides a string representation of this Property instance.
     *
     * @return A string describing this Property.
     */
    @Override
    public String toString()
    {
        return "\n" + "PROPERTY" + "\n" +
               "priceUsd: " + priceUsd + "\n" +
               "address: " + address + "\n" +
               "type: '" + type + '\'' + "\n" +
               "propertyId: '" + propertyId + '\'' + "\n";
    }
}
