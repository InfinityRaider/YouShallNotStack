package com.InfinityRaider.YouShallNotStack;

public class IOHelper {
    //turns the raw data string into an array (each array element is a line from the string)
    public static String[] getLinesArrayFromData(String input) {
        int count = 0;
        for(int i=0;i<input.length();i++) {
            if(input.charAt(i)=='\n') {
                count++;
            }
            else if (input.charAt(i)=='#') {
                count--;
            }
        }
        count = count>=0?count+1:count;  //for the last line
        count = count<0?0:count;        //make sure it can't be negative
        String[] data = new String[count];
        if(input.length()>0) {
            int start = 0;
            int stop;
            for (int i = 0; i < data.length; i++) {
                stop = input.indexOf('\n', start);
                while (start<input.length() && input.charAt(start) == '#' && start<input.length()) {
                    start = stop + 1;
                    stop = input.indexOf('\n', start);
                }
                data[i] = i == data.length - 1 ? input.substring(start) : input.substring(start, stop);
                start = stop + 1;
            }
        }
        return data;
    }

    public static String[] getData(String input) {
        String[] output = new String[2];
        int commaIndex = input.indexOf(',');
        if(commaIndex>0) {
            output[0] = input.substring(0,commaIndex);
            output[1] = input.substring(commaIndex+1);
        }
        return output;
    }

    public static String getInstructions() {
        return instructions;
    }

    private static final String instructions =
            "#Put a list of stacks to define a stacksize for, in the following fashion: <itemName>,<stacksize>\n" +
            "#The itemName should be the name NEI gives you, the stacksize is the maximum stacksize\n" +
            "#Only define one entry line, meta is optional. Example: minecraft:iron_ingot,10";
}
