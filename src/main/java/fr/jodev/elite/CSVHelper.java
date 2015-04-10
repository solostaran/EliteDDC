package fr.jodev.elite;

import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper
{
	public static char separator = ';'; // customizable
	public static int defaultInt = 0; // customizable
	public static float defaultFloat = 0.0f; // customizable
	public static boolean defaultBoolean = false; // customizable
	
    public static void writeLine(Writer w, List<String> values) 
        throws Exception
    {
        boolean firstVal = true;
        for (String val : values)  {
            if (!firstVal) {
                w.write(",");
            }
            w.write("\"");
            for (int i=0; i<val.length(); i++) {
                char ch = val.charAt(i);
                if (ch=='\"') {
                    w.write("\"");  //extra quote
                }
                w.write(ch);
            }
            w.write("\"");
            firstVal = false;
        }
        w.write("\n");
    }

    /** 
     * Returns a null when the input stream is empty
     */
    public static List<String> parseLine(Reader r) throws Exception {
        int ch = r.read();
        while (ch == '\r') {
            ch = r.read();
        }
        if (ch<0) {
            return null;
        }
        List<String> store = new ArrayList<String>();
        StringBuffer curVal = new StringBuffer();
        boolean inquotes = false;
        boolean started = false;
        while (ch>=0) {
            if (inquotes) {
                started=true;
                if (ch == '\"') {
                    inquotes = false;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            else {
                if (ch == '\"') {
                    inquotes = true;
                    if (started) {
                    	// if this is the second quote in a value, add a quote
                    	// this is for the double quote in the middle of a value
                        curVal.append('\"');
                    }
                }
                else if (ch == separator) {
                    store.add(curVal.toString());
                    curVal = new StringBuffer();
                    started = false;
                }
                else if (ch == '\r') {
                    //ignore LF characters
                }
                else if (ch == '\n') {
                    //end of a line, break out
                    break;
                }
                else {
                    curVal.append((char)ch);
                }
            }
            ch = r.read();
        }
        store.add(curVal.toString());
        return store;
    }
    
    public static int parseInt(String s) {
    	int i = defaultInt;
    	try {
    		i = Integer.parseInt(s);
    	} catch (NumberFormatException e) {}
    	return i;
    }
    
    public static long parseLong(String s) {
    	long l = defaultInt;
    	try {
    		l = Long.parseLong(s);
    	} catch (NumberFormatException e) {}
    	return l;
    }
    
    public static float parseFloat(String s) {
    	float f = defaultFloat;
    	try {
    		f = Float.parseFloat(s);
    	} catch (NumberFormatException e) {}
    	return f;
    }
    
    public static double parseDouble(String s) {
    	double d = defaultFloat;
    	try {
    		d = Double.parseDouble(s);
    	} catch (NumberFormatException e) {}
    	return d;
    }
    
    public static boolean parseBoolean(String s) {
    	boolean b = defaultBoolean;
    	try {
    		b = Boolean.parseBoolean(s);
    	} catch (NumberFormatException e) {}
    	return b;
    }
}
