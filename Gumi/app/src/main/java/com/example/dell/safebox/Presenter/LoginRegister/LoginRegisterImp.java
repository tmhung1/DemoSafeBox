package com.example.dell.safebox.Presenter.LoginRegister;

import android.content.Context;

import com.example.dell.safebox.Object.Account;

public interface LoginRegisterImp {
    void registerAccount(Account account, Context context);

    void checkImeiNumber(String imeiNumber, Context context);

    void loginApp(String passCode, Context context);
}
