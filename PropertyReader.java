import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Tian Lan
 * @version 1.0
 */
public class PropertyReader
{
    private final List<String> propertyData;

    public PropertyReader()
    {
        propertyData = new ArrayList<>();
    }

    /**
     * This method reads the provided file and adds Strings (for each line) to an ArrayList<String> and returns it.
     * @param file The provides file's name.
     * @return An ArrayList<String> for each line that are provided in the file.
     * @throws FileNotFoundException if the file name does not exist.
     */
    public static ArrayList<String> readPropertyData(final File file) throws FileNotFoundException
    {
        String oneProperty;
        ArrayList<String> propertyData;

        Scanner myScanner = new Scanner(file);
        propertyData = new ArrayList<>();

        while(myScanner.hasNext())
        {
            oneProperty = myScanner.nextLine();
            propertyData.add(oneProperty);
        }
        myScanner.close();
        return propertyData;
    }
}
