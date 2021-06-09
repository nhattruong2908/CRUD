package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    private EditText editemaildangky, editpassworddangky;
    private Button buttondangky, btnquaylai;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth = FirebaseAuth.getInstance();
        editemaildangky = (EditText) findViewById(R.id.editemaildangky);
        editpassworddangky = (EditText) findViewById(R.id.editpassworddangky);
        buttondangky = (Button) findViewById(R.id.buttondangky);
        btnquaylai = (Button) findViewById(R.id.btnquaylai);


        buttondangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky();
            }
        });

        btnquaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void dangky() {
        String email = editemaildangky.getText().toString();
        String password = editpassworddangky.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            User user = new User(email);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity2.this, "dang ky thanh cong!!!!!!!",
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(MainActivity2.this, "Dang ky that bai!! thu lai",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }

                            });
                        } else {
                            Toast.makeText(MainActivity2.this, "Dang ky that bai",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}



