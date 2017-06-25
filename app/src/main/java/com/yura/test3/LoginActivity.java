package com.yura.test3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.yura.test3.databinding.ActivityLoginBinding;
import com.yura.test3.mvp.presenter.AuthorizationPresenter;
import com.yura.test3.mvp.view.AuthorizationView;
import com.yura.test3.utils.MyTextWatcher;

/**
 * Created by yura on 25.06.17.
 */

public class LoginActivity extends MvpAppCompatActivity implements MyTextWatcher.TextWatcherDelegate, AuthorizationView{

    @InjectPresenter
    AuthorizationPresenter authorizationPresenter;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.inputEmail.addTextChangedListener(new MyTextWatcher(binding.inputEmail, this));
        binding.inputPassword.addTextChangedListener(new MyTextWatcher(binding.inputPassword, this));

        authorizationPresenter.getWeather();
    }

    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail() {
        String email = binding.inputEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            binding.inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(binding.inputEmail);
            return false;
        } else {
            binding.inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            binding.inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(binding.inputPassword);
            return false;
        } else {
            binding.inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    @Override
    public void validate(int id) {
        switch (id) {
            case R.id.input_email:
                validateEmail();
                break;
            case R.id.input_password:
                validatePassword();
                break;
        }
    }

    public void showWeather(String weather){
        Snackbar.make(binding.main,
                weather, Snackbar.LENGTH_SHORT)
                .show();
    }
}
