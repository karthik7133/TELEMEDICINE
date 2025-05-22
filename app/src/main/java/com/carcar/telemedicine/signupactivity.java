package com.carcar.telemedicine;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class signupactivity extends AppCompatActivity {

    TextView uname,pss,cnfpass;
    Button signup,login;
    LoginDao l2;

    ImageView pic;

    CheckBox showpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signupactivity);
        Window window=getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.neon_violate));




        uname=findViewById(R.id.Uname);
        pss=findViewById(R.id.Passwrod);
        cnfpass=findViewById(R.id.cnfpass);
        signup=findViewById(R.id.signupbtn);
        login=findViewById(R.id.btnlogin);
        showpassword=findViewById(R.id.checkbox);
        pic=findViewById(R.id.gif);

        String profileType = getIntent().getStringExtra("profileType1");


        if ("Doctor Profile".equals(profileType)) {
            pic.setImageResource(R.drawable.doctor);
        } else if ("Patient Profile".equals(profileType)) {
            pic.setImageResource(R.drawable.patient);
        }

        showpassword.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b){
                pss.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                cnfpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                pss.setTransformationMethod(PasswordTransformationMethod.getInstance());
                cnfpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        UserDatabase db=UserDatabase.getInstance(this);
        l2=db.loginDao();

        Intent tomain=new Intent(this,MainActivity.class);
        Intent tologin=new Intent(this, loginpage.class);

        login.setOnClickListener(View->{
            startActivity(tologin);
        });




        signup.setOnClickListener(v -> {
            String name = uname.getText().toString().trim();
            String pass = pss.getText().toString().trim();
            String cnfp = cnfpass.getText().toString().trim();



            if (name.isEmpty() || pass.isEmpty() || cnfp.isEmpty()) {
                Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show();
                return;  // Exit to prevent further execution
            }

            if (!pass.equals(cnfp)) {
                Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show();
                return;
            }

            if(name.length()<8|| pass.length()<8){
                Toast.makeText(this,"Minimum length must be 8",Toast.LENGTH_SHORT).show();
                return;

            }
            if(!containsCapitalAndNumber(pass)){
                Toast.makeText(this, "At least one capital and one numeric required", Toast.LENGTH_SHORT).show();
            }


            if (l2.checkusername(name) > 0) {
                Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
            } else {
                l2.insert(new LoginStruct(name, pass));
                Log.d("User credentials", "Name: " + name + " password: " + pass);
                Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();
                startActivity(tologin);
            }
        });
    }
    public static boolean containsCapitalAndNumber(String str) {
        return str.matches(".*[A-Z].*") && str.matches(".*\\d.*");
    }
}
