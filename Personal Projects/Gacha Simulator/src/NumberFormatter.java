package src;

import java.text.DecimalFormat;

public class NumberFormatter {
	
    // Format numbers in an interger format (no decimals)
    public static <T extends Number> String formatInteger(T number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number.intValue());
    }
    
    // Format numbers in an double format (decimals)
	public static <T extends Number> String formatDecimal(T number) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.0");
	    return decimalFormat.format(number.doubleValue());
	}
 
 
}