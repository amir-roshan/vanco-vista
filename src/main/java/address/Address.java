package main.java.address;

/**
 * Represents an address with various properties
 *
 * @author Amir Roshan
 * @version 1.0
 */
public record Address(String unitNumber, int streetNumber, String streetName, String postalCode, String city)
{
    private static final int STREET_NAME_MAX_LENGTH = 20;
    private static final int MIN_STREET_NUMBER = 0;
    private static final int MAX_STREET_NUMBER = 999999;

    private static final int CITY_LENGTH = 30;

    private static final int MIN_POSTAL_CODE_LENGTH = 5;
    private static final int MAX_POSTAL_CODE_LENGTH = 6;

    private static final int MIN_UNIT_NUMBER_LENGTH = 1;
    private static final int MAX_UNIT_NUMBER_LENGTH = 4;

    private static final String DEFAULT_UNIT_NUMBER = "0000";

    /**
     * Constructs an Address object with the given parameters.
     *
     * @param unitNumber the unit number
     * @param streetNumber the street number
     * @param streetName the street name
     * @param postalCode the postal code
     * @param city the city
     * @throws NullPointerException if any of the parameters are null
     */
    public Address
    {
        // Validate street number
        if(streetNumber < MIN_STREET_NUMBER || streetNumber > MAX_STREET_NUMBER)
        {
            throw new IllegalArgumentException("Invalid street number: " + streetNumber);
        }

        // Validate street name
        if(streetName == null)
        {
            throw new NullPointerException("Invalid street name: null");
        }
        else if(streetName.length() > STREET_NAME_MAX_LENGTH || streetName.isBlank())
        {
            throw new IllegalArgumentException("Invalid street name: " + streetName);
        }

        // Validate city
        if(city == null)
        {
            throw new NullPointerException("Invalid city: null");
        }
        else if(city.isBlank() || city.length() > CITY_LENGTH)
        {
            throw new IllegalArgumentException("Invalid city: " + city);
        }

        // Validate postal code
        if(postalCode == null)
        {
            throw new NullPointerException("Invalid postal code: null");
        }
        else if(postalCode.length() < MIN_POSTAL_CODE_LENGTH || postalCode.length() > MAX_POSTAL_CODE_LENGTH)
        {
            throw new IllegalArgumentException("Invalid postal code: " + postalCode);
        }

        // Validate unit number
        if(unitNumber == null)
        {
            throw new NullPointerException("Invalid unit number: null");
        }
        else if(unitNumber.length() < MIN_UNIT_NUMBER_LENGTH || unitNumber.length() > MAX_UNIT_NUMBER_LENGTH)
        {
            throw new IllegalArgumentException("Invalid unit number: " + unitNumber);
        }

    }

    /**
     * Returns the unit number of the address.
     *
     * @return the unit number
     */
    @Override
    public String unitNumber()
    {
        return unitNumber;
    }

    /**
     * Returns the street number of the address.
     *
     * @return the street number
     */
    @Override
    public int streetNumber()
    {
        return streetNumber;
    }

    /**
     * Returns the postal code of the address.
     *
     * @return the postal code
     */
    @Override
    public String postalCode()
    {
        return postalCode;
    }

    /**
     * Returns the city of the address.
     *
     * @return the city
     */
    @Override
    public String city()
    {
        return city;
    }

    /**
     * Returns the street name of the address.
     *
     * @return the street name
     */
    @Override
    public String streetName()
    {
        return streetName;
    }

    /**
     * Provides a string representation of this Address instance in a comma-separated key-value format.
     *
     * @return A string describing this Address, including the unit number, street number, street name, postal code, and city.
     */
    @Override
    public String toString()
    {
        return "Address [unitNumber: " + (unitNumber.isBlank()? DEFAULT_UNIT_NUMBER: unitNumber) +
               ", streetNumber: " + streetNumber + ", streetName: " + streetName + ", postalCode: " + postalCode +
               ", city: " + city + "]";
    }
}
