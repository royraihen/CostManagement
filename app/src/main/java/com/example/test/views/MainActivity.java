package com.example.test.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.webkit.WebView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.test.viewmodels.ViewModel;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        webView = new WebView(this);
        ViewModel vm = new ViewModel(this);
        webView.loadUrl("file:///android_asset/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(vm,"vm");

        setContentView(webView);
    }


    public void makeToast(String message){
        Toast.makeText(this,message,(Toast.LENGTH_SHORT)).show();
    }

    public void goToRegister(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/register.html");
            }
        });
    }

    public void goToLogin(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/index.html");
            }
        });
    }

    public void cancelBtn(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/home.html");
            }
        });
    }

    public void goToHome(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/home.html");
            }
        });
    }

    public void goToGraph(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/graph.html");
            }
        });
    }

    public void goToSpend(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/add_spend.html");
            }
        });
    }

    public void goToIncome(){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("file:///android_asset/add_incoming.html");
            }
        });
    }

    public void saveId (int arrived_id){
        id = arrived_id;
    }


    public int getId(){
        return id;
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.getSettings().setJavaScriptEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.getSettings().setJavaScriptEnabled(true);
    }

}
