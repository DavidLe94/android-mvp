package com.android.haule.mvpparttern.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.haule.mvpparttern.Model.MainPresenterImpl;
import com.android.haule.mvpparttern.Presenter.MainPresenter;
import com.android.haule.mvpparttern.R;
import com.android.haule.mvpparttern.View.MainView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {
    private Button btnLogin;
    private EditText edtEmail, edtPass;
    private ProgressBar progressBar;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setEvent();
        callApi();
    }

    private void callApi() {
        presenter.callApi(this);
    }

    private void setEvent() {
        btnLogin.setOnClickListener(this);
    }

    private void initView(){
        btnLogin = findViewById(R.id.login);
        edtEmail = findViewById(R.id.email);
        edtPass = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);

        presenter = new MainPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                presenter.validateCredentials(edtEmail.getText().toString(), edtPass.getText().toString());
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setUsernameError() {
        edtEmail.setError(getString(R.string.email_err));
    }

    @Override
    public void setPasswordError() {
        edtPass.setError(getString(R.string.password_err));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataResponse(String res) {
        Log.d("TAG", "dataResponse: " + res);
    }
}
