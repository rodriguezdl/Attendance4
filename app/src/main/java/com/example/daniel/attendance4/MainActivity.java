package com.example.daniel.attendance4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button Login;
    private FirebaseAuth mAuth;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.register);

        Login = findViewById(R.id.login);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
                //startActivity(new Intent(getApplicationContext(), NextPage.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    private void signIn(){
       String em = email.getText().toString();
       String pass = password.getText().toString();
       final View view = this.getCurrentFocus();
       if(TextUtils.isEmpty(em)||TextUtils.isEmpty(pass)){
           Toast.makeText(getApplicationContext(), "The fields are empty", Toast.LENGTH_LONG).show();
       }else {

           mAuth.signInWithEmailAndPassword(em, pass)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       public static final String TAG = "";

                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Log.d(TAG, "signInWithEmail:success");
                               Toast.makeText(getApplicationContext(),"Sign In Successful", Toast.LENGTH_LONG).show();
                               FirebaseUser user = mAuth.getCurrentUser();
                               startActivity(new Intent(getApplicationContext(),Home.class));
                               //updateUI(user);
                           } else {
                               // If sign in fails, display a message to the user.
                               Log.w(TAG, "signInWithEmail:failure", task.getException());
                               Toast.makeText(getApplicationContext(), "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                               //updateUI(null);
                           }

                           // ...
                       }
                   });
       }}

       private void register(){
           String em = email.getText().toString();
           String pass = password.getText().toString();
           final View view = getCurrentFocus();
           mAuth.createUserWithEmailAndPassword(em, pass)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       public static final String TAG = "";

                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               Log.d(TAG, "createUserWithEmail:success");
                               FirebaseUser user = mAuth.getCurrentUser();
                               //updateUI(user);
                           } else {
                               // If sign in fails, display a message to the user.
                               Log.w(TAG, "createUserWithEmail:failure", task.getException());
                               Toast.makeText(getApplicationContext(), "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                               //updateUI(null);
                           }

                           // ...
                       }
                   });
       }
}
