package net.world.qubag.ui;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import net.world.qubag.R;
import net.world.qubag.models.User;
import net.world.qubag.utils.Preferences;
import net.world.qubag.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mvnpavan on 04/03/17.
 */

public class SignUp_Fragment extends Fragment implements OnClickListener {
	private static View view;
	private static EditText fullName, emailId, mobileNumber,
			password, confirmPassword;
	private static TextView login;
	private static Button signUpButton;
	private static CheckBox terms_conditions;

	public SignUp_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.signup_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	private void initViews() {
		fullName = (EditText) view.findViewById(R.id.fullName);
		emailId = (EditText) view.findViewById(R.id.userEmailId);
		mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
		password = (EditText) view.findViewById(R.id.password);
		confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
		signUpButton = (Button) view.findViewById(R.id.signUpBtn);
		login = (TextView) view.findViewById(R.id.already_user);
		terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);

		XmlResourceParser xrp = getResources().getXml(R.xml.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			login.setTextColor(csl);
			terms_conditions.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	private void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.signUpBtn:

				checkValidation();
				break;

			case R.id.already_user:

				new Login_Holder().replaceLoginFragment();
				break;
		}

	}

	private void checkValidation() {

		String getFullName = fullName.getText().toString();
		String getEmailId = emailId.getText().toString();
		String getMobileNumber = mobileNumber.getText().toString();
		String getPassword = password.getText().toString();
		String getConfirmPassword = confirmPassword.getText().toString();

		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);

		if (getFullName.equals("") || getFullName.length() == 0
				|| getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0)

			Snackbar.make(view, R.string.ieldsrequired, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		else if (!m.find())

			Snackbar.make(view, R.string.invalidmail, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		else if (!getConfirmPassword.equals(getPassword))

			Snackbar.make(view, R.string.pswrdnotmatch, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		else if (!terms_conditions.isChecked())

			Snackbar.make(view, R.string.termsandconditions, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		else {

			Preferences preferences = new Preferences(getActivity());

			User user = new User();

			user.setUsername(emailId.getText().toString());
			user.setPassword(password.getText().toString());

			preferences.storeUserDetails(user);

			new Login_Holder().replaceLoginFragment();

		}

	}
}
