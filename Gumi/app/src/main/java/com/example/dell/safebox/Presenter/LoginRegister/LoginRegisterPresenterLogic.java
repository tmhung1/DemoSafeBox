package com.example.dell.safebox.Presenter.LoginRegister;

import android.content.Context;

import com.example.dell.safebox.Model.LoginRegisterModel;
import com.example.dell.safebox.Object.Account;
import com.example.dell.safebox.View.LoginRegister.LoginRegisterViewImp;

public class LoginRegisterPresenterLogic implements LoginRegisterImp {
    LoginRegisterViewImp loginRegisterViewImp;
    LoginRegisterModel loginRegisterModel;

    public LoginRegisterPresenterLogic(LoginRegisterViewImp loginRegisterViewImp) {
        this.loginRegisterViewImp = loginRegisterViewImp;
        loginRegisterModel = new LoginRegisterModel();
    }

    @Override
    public void registerAccount(Account account, Context context) {
        loginRegisterModel.openConnectSQL(context);
        boolean result = loginRegisterModel.registerAccount(account);
        loginRegisterViewImp.receiveResult(result);
    }

    @Override
    public void checkImeiNumber(String imeiNumber, Context context) {
        loginRegisterModel.openConnectSQL(context);
        boolean result = loginRegisterModel.checkImeiNumber(imeiNumber);
        loginRegisterViewImp.receiveCheckImei(result);
    }

    @Override
    public void loginApp(String passCode, Context context) {
        loginRegisterModel.openConnectSQL(context);
        boolean result = loginRegisterModel.loginApp(passCode);
        loginRegisterViewImp.receiveLoginApp(result);
    }
}
