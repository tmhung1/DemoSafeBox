package com.example.dell.safebox.View.LoginRegister;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.safebox.Object.Account;
import com.example.dell.safebox.Presenter.LoginRegister.LoginRegisterPresenterLogic;
import com.example.dell.safebox.R;
import com.example.dell.safebox.View.HomePage.HomePageActivity;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener, LoginRegisterViewImp {
    EditText edtPassCode;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bOK, bDelete;
    String imeiNumber = "";
    private final int MY_PERMISSIONS_REQUEST_CODE = 1;
    LoginRegisterPresenterLogic loginRegisterPresenterLogic;
    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addConTrol();
        addEvent();

        if (checkPermissions()) {
            startApplication();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Permissions");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setPermissions();
                }
            });
            builder.show();
        }
    }

    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSIONS_REQUEST_CODE) {
            return;
        }
        boolean isGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }

        if (isGranted) {
            startApplication();
        } else {
            Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();

        }
    }

    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_PHONE_STATE}, MY_PERMISSIONS_REQUEST_CODE);
    }

    public String getIMEINumber() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager telephonyMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                imeiNumber = telephonyMgr.getImei();
            } else {
                imeiNumber = telephonyMgr.getDeviceId();
            }
        }
        return imeiNumber;
    }

    @SuppressLint("ServiceCast")
    public void startApplication() {
        telephonyManager = (TelephonyManager) getSystemService(this.TELEPHONY_SERVICE);
        imeiNumber = getIMEINumber();
    }

    private TextWatcher displayButton = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String passCode = edtPassCode.getText().toString().trim();
            if (!passCode.isEmpty()) {
                bDelete.setVisibility(View.VISIBLE);
                if (passCode.length() >= 6) {
                    bOK.setVisibility(View.VISIBLE);
                } else {
                    bOK.setVisibility(View.INVISIBLE);
                }
            } else {
                bDelete.setVisibility(View.INVISIBLE);
                bOK.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void addEvent() {
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);
        bOK.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        edtPassCode.addTextChangedListener(displayButton);
    }

    private void addConTrol() {
        edtPassCode = findViewById(R.id.edtPassCode);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);
        b4 = findViewById(R.id.btn4);
        b5 = findViewById(R.id.btn5);
        b6 = findViewById(R.id.btn6);
        b7 = findViewById(R.id.btn7);
        b8 = findViewById(R.id.btn8);
        b9 = findViewById(R.id.btn9);
        b0 = findViewById(R.id.btn0);
        bOK = findViewById(R.id.btnOK);
        bDelete = findViewById(R.id.btnDelete);
        loginRegisterPresenterLogic = new LoginRegisterPresenterLogic(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn1:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "1"));
                break;
            case R.id.btn2:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "2"));
                break;
            case R.id.btn3:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "3"));
                break;
            case R.id.btn4:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "4"));
                break;
            case R.id.btn5:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "5"));
                break;
            case R.id.btn6:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "6"));
                break;
            case R.id.btn7:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "7"));
                break;
            case R.id.btn8:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "8"));
                break;
            case R.id.btn9:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "9"));
                break;
            case R.id.btn0:
                edtPassCode.setText(edtPassCode.getText().insert(edtPassCode.getText().length(), "0"));
                break;
            case R.id.btnOK:
                //check imei number have in db?
                chekImeiNumber(imeiNumber);
                break;
            case R.id.btnDelete:
                edtPassCode.setText(edtPassCode.getText().delete(edtPassCode.getText().length() - 1, edtPassCode.getText().length()));
                break;

        }
    }

    private void chekImeiNumber(String imeiNumber) {
        loginRegisterPresenterLogic.checkImeiNumber(imeiNumber, this);
    }

    @Override
    public void receiveResult(Boolean result) {
        if (result.booleanValue() == true) {
            Intent iHomePage = new Intent(LoginRegisterActivity.this, HomePageActivity.class);
            startActivity(iHomePage);
        }
    }

    @Override
    public void receiveCheckImei(Boolean result) {
        if (result.booleanValue() == true) {
            //have -->login
            loginRegisterPresenterLogic.loginApp(edtPassCode.getText().toString(), this);
        } else {
            //don't -->register
            Account account = new Account();
            account.setImeiNumber(imeiNumber);
            account.setPassCode(edtPassCode.getText().toString());
            loginRegisterPresenterLogic.registerAccount(account, this);
        }
    }

    @Override
    public void receiveLoginApp(Boolean result) {
        if (result.booleanValue() == true) {
            Intent iHomePage = new Intent(LoginRegisterActivity.this, HomePageActivity.class);
            startActivity(iHomePage);
        } else {
            Toast.makeText(this, "PassCode incorect", Toast.LENGTH_SHORT).show();
        }
    }
}
