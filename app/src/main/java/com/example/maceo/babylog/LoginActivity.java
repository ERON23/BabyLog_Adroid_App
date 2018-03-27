package com.example.maceo.babylog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin, btnSignup, btnReset;
    private EditText editEmail;
    private EditText editPassword;
    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonLogin);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        progressBar = (ProgressBar) findViewById(R.id.LoginProgressBar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();
        buttonLogin.setOnClickListener(this);

    }

    private void Login(){
        String email = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, "E-mail address required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this, "Password required", Toast.LENGTH_SHORT).show();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            if (password.length() < 6){
                                editPassword.setError(getString(R.string.minimum_password));
                            }else {
                                Toast.makeText(LoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View view){
        if(view == buttonLogin){
            Login();
        }
    }
}
