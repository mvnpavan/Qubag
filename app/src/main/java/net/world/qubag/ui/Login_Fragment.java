package net.world.qubag.ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
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


public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login_layout, container, false);
		initViews();
		setListeners();
		return view;
	}

	private void initViews() {
		fragmentManager = getActivity().getSupportFragmentManager();

		emailid = (EditText) view.findViewById(R.id.login_emailid);
		password = (EditText) view.findViewById(R.id.login_password);
		loginButton = (Button) view.findViewById(R.id.loginBtn);
		forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
		signUp = (TextView) view.findViewById(R.id.createAccount);
		show_hide_password = (CheckBox) view
				.findViewById(R.id.show_hide_password);
		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

		XmlResourceParser xrp = getResources().getXml(R.xml.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			forgotPassword.setTextColor(csl);
			show_hide_password.setTextColor(csl);
			signUp.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);

		show_hide_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
												 boolean isChecked) {
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);

							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());
						} else {
							show_hide_password.setText(R.string.show_pwd);

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());

						}

					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.loginBtn:
				checkValidation();
				break;

			case R.id.forgot_password:
				Snackbar.make(view, R.string.comingsoon, Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();

				break;
			case R.id.createAccount:
				fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
						.replace(R.id.frameContainer, new SignUp_Fragment(),
								Utils.SignUp_Fragment).commit();
				break;
		}

	}

	private void checkValidation() {
		String getEmailId = emailid.getText().toString();
		String getPassword = password.getText().toString();

		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			Snackbar.make(view, R.string.enterbothdetails, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();

		} else if (!m.find()) {
			Snackbar.make(view, R.string.invalidmail, Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
		} else {

			Preferences preferences = new Preferences(getActivity());

			User user = preferences.getUserDetails();

			if (emailid.getText().toString().equals(user.getUsername()) && password.getText().toString().equals(user.getPassword())){
				Intent intent = new Intent(getActivity() , MainActivity.class);
				startActivity(intent);
				getActivity().finish();
			}else {
				Snackbar.make(view, R.string.detailsinvalid, Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		}

	}
}
