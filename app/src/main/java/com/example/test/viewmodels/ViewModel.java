package com.example.test.viewmodels;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.test.views.MainActivity;

public class ViewModel {

    private MainActivity context;

    public ViewModel(MainActivity context){
        this.context = context;
    }

    /**
     * Use the native application's toast object to post notification on the screen.
     * @param message String
     */
    @JavascriptInterface
    public void makeToast(String message){
        Toast.makeText(context,message,(Toast.LENGTH_SHORT)).show();
    }


    @JavascriptInterface
    public void goToRegister() {
        context.goToRegister();
    }

    @JavascriptInterface
    public void goToLogin() {
        context.goToLogin();
    }

    @JavascriptInterface
    public void goToHome() {
        context.goToHome();
    }

    @JavascriptInterface
    public void cancelBtn() {
        context.goToHome();
    }

    @JavascriptInterface
    public void graphbtn() {
        context.goToGraph();
    }

    @JavascriptInterface
    public void goToSpend() {
        context.goToSpend();
    }

    @JavascriptInterface
    public void goToIncome() {
        context.goToIncome();
    }

    @JavascriptInterface
    public void saveId(int id) {
        context.saveId(id);
    }

    @JavascriptInterface
    public int getId() {
        return context.getId();
    }


}
