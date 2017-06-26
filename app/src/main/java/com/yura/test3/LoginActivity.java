package com.yura.test3;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.yura.test3.databinding.ActivityLoginBinding;
import com.yura.test3.mvp.presenter.AuthorizationPresenter;
import com.yura.test3.mvp.view.AuthorizationView;
import com.yura.test3.utils.MyTextWatcher;
import com.yura.test3.utils.Utils;

public class LoginActivity extends MvpAppCompatActivity implements MyTextWatcher.TextWatcherDelegate, AuthorizationView{

    @InjectPresenter
    AuthorizationPresenter authorizationPresenter;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.toolbar.tvMenu.setText(R.string.authorization);

        binding.inputEmail.addTextChangedListener(new MyTextWatcher(binding.inputEmail, this));
        binding.inputPassword.addTextChangedListener(new MyTextWatcher(binding.inputPassword, this));


        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(submitForm()) {
                    authorizationPresenter.getWeather();
                }
            }
        });


        binding.toolbar.ivArrowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * Validating form
     */
    private boolean submitForm() {
        if (validateEmail() && validatePassword()) {
            return true;
        }

        return false;
    }

    private boolean validateEmail() {
        String email = binding.inputEmail.getText().toString().trim();

        if (email.isEmpty() || !Utils.isValidEmail(email)) {
            binding.inputLayoutEmail.setErrorEnabled(true);
            binding.inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(binding.inputEmail);
            return false;
        } else {
            binding.inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (!Utils.isValidPassword(binding.inputPassword.getText().toString())) {
            binding.inputLayoutPassword.setErrorEnabled(true);
            binding.inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(binding.inputPassword);
            return false;
        } else {
            binding.inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
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
                getResources().getString(R.string.cur_temp_in_Moscow) + weather, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.some_error), Toast.LENGTH_SHORT)
        .show();
    }
}
