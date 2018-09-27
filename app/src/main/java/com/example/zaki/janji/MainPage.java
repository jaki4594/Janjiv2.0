package com.example.zaki.janji;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.net.URL;
import java.util.HashMap;

public class MainPage extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient googleApiClient;
    private static final String TAG = "MainPage";
    public static final String EXTRA_MESSAGE = "com.example.zaki.janji";
    HashMap<String, String> params2 = new HashMap<String, String>();

    SignInButton btnSignIn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btnSignIn=(SignInButton)findViewById(R.id.sign_in_button);


        btnSignIn.setOnClickListener(this);


        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient=new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
    }
    private void signIn(){
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignResult(result);
        }
    }
    private void handleSignResult(GoogleSignInResult result){
        Log.v("@@@WWe",TAG+" "+result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount account=result.getSignInAccount();
            Log.v("@@@WWe",TAG+ "Name : "+account.getDisplayName());
            String name = account.getDisplayName();
            String email = account.getEmail();
            String image;
            try {
               image = account.getPhotoUrl().toString();
            }catch (NullPointerException e){
               image = " ";
            }

            Intent intent = new Intent(this, Home_page.class);
            intent.putExtra("Email", email);
            intent.putExtra("Username", name);
            intent.putExtra("UrlPhoto", image);

            params2.put("nama",name);
            params2.put("email",email);
            params2.put("photo",image);

            Singleton.getInstance().mailtosupport(params2, this,"InsertUsr.php");


            startActivity(intent);
            //finish();

            Toast.makeText(getApplicationContext(), "Welcome "+name,
                    Toast.LENGTH_SHORT).show();
            //updateUi(true);
        }else{
            Toast.makeText(getApplicationContext(), result.getStatus().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("@@@WWE",TAG+" Connection Failed  "+connectionResult);
    }
}
