package com.investor.view;

public interface LoginContractor {

    interface view
    {
        void loginSuccess();
        void loginFailed();
    }


    interface presenter
    {

        void checkLogin(String userName,String password);
    }



}
