import java.io.CharConversionException;
import java.util.ArrayList;

public class FindDuplicates 
	{

    public static void main(String[] args) 
	{
        String inputString = "My name is Ankuri Sen!";

        printDuplicates(inputString);
    	}

    public static void printDuplicates(String inputString) 
	{
        
        int count = 0;
        ArrayList<Character> charList = new ArrayList<>();

        for (int i = 0; i < inputString.length(); i++) 
		Ś

            char ch = inputString.charAt(i);
            for (int j = 0; j < inputString.length(); j++) 
		{
                if (inputString.charAt(j) != ch) 
			{
                    continue;
                	}
                count++;

            	}
            if (!charList.contains(ch)) 
		{
                if (count > 1 && ch != ' ') 
			{
                    System.out.println("Char: " + ch + ", Count: " + count + " times.");
                    charList.add(ch);
                	}
            	}
            count = 0;
        }
    }

}