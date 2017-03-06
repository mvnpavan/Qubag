package net.world.qubag.utils;

/**
 * Created by mvnpavan on 04/03/17.
 */

public class Utils {
	
	//Email Validation pattern
	public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	//Fragments Tags
	public static final String Login_Fragment = "Login_Fragment";
	public static final String SignUp_Fragment = "SignUp_Fragment"
			;
	public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";

	public static final String SAMPLE_ITEMS = "{\n" +
			
			"    \"Items\":[ {\"name\":\"Apple\",\"category\":\"Fruits\",\"price\":\"50\"},{\"name\":\"Mango\",\"category\":\"Fruits\",\"price\":\"120\"},{\"name\":\"Cabbage\",\"category\":\"vegetables\",\"price\":\"18\"},{\"name\":\"Banana Raw\",\"category\":\"vegetables\",\"price\":\"7\"},{\"name\":\"cake\",\"category\":\"Snacks\",\"price\":\"22\"},{\"name\":\"Gulab Jamun\",\"category\":\"Snacks\",\"price\":\"180\"}\n" +
			"            ]\n" +
			"}";

	public static final String SAMPLE_CATEGORIES = "{\n" +
			"    \"Categories\": [ \"vegetables\",\"Fruits\",\"Baby Food\",\"Snacks\"       \n" +
			"                  ]\n" +
			"}";
}
