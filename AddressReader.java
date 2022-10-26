import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tian Lan
 * @version 1.0
 */
public class AddressReader
{
    private final List<Address> addressData;

    public static final int UNIT_NUMBER_POSITION = 0;
    public static final int STREET_NUMBER_POSITION = 1;
    public static final int STREET_NAME_POSITION = 2;
    public static final int POSTAL_CODE_POSITION = 3;
    public static final int CITY_NAME_POSITION = 4;

    public AddressReader()
    {
        addressData = new ArrayList<>();
    }

    /**
     * This method reads the provided file and adds Address objects to an ArrayList<Address> and returns it.
     * @param file The provides file's name.
     * @return An ArrayList<Address> that are provided in the file.
     * @throws FileNotFoundException if the file name does not exist.
     */
    public static ArrayList<Address> readAddressData(final File file) throws FileNotFoundException
    {
        String oneAddress;
        String[] oneAddressData;
        String unitNumber;
        int streetNumber;
        String streetName;
        String postalCode;
        String cityName;
        Address newAddress;
        boolean proceed;
        ArrayList<Address> addressData;

        Scanner myScanner = new Scanner(file);
        addressData = new ArrayList<>();
        proceed = true;

        do
        {
            if (myScanner.hasNext())
            {
                oneAddress = myScanner.nextLine();
                oneAddressData = oneAddress.split("\\|");
                unitNumber = oneAddressData[UNIT_NUMBER_POSITION];
                streetName = oneAddressData[STREET_NAME_POSITION];
                streetNumber = Integer.parseInt(oneAddressData[STREET_NUMBER_POSITION]);
                postalCode = oneAddressData[POSTAL_CODE_POSITION];
                cityName = oneAddressData[CITY_NAME_POSITION];

                newAddress = new Address(unitNumber, streetNumber, streetName, postalCode, cityName);

                if (newAddress != null)
                {
                    addressData.add(newAddress);
                }
                else
                {
                    throw new NullPointerException("Invalid address data!");
                }
            }
            else
            {
                proceed = false;
                myScanner.close();
            }
        }
        while(proceed);
        return addressData;
    }
}
