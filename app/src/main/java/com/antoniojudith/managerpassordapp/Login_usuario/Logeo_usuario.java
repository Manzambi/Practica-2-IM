package com.antoniojudith.managerpassordapp.Login_usuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.antoniojudith.managerpassordapp.MainActivity;
import com.antoniojudith.managerpassordapp.R;

public class Logeo_usuario extends AppCompatActivity {

    EditText EtPasswordU;
    Button BtnIngresar, BtnInicioSesionBiometrico;
    SharedPreferences sharedPreferences;
    ImageButton Ib_Aviso;

    private static final String SHARED_PREF = "mi_pref";
    private static final String KEY_PASSWORD = "password";

    //private BiometricPrompt biometricPrompt;
   // private BiometricPrompt.PromptInfo promptInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logeo_usuario);
        InicializarVariables();
        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_password = EtPasswordU.getText().toString().trim();
                //Obtener la contraseña maestra almacenada en SP
                String password_SP = sharedPreferences.getString(KEY_PASSWORD, null);

                if (S_password.equals("")){
                    Toast.makeText(Logeo_usuario.this, "Campo es obligatorio", Toast.LENGTH_SHORT).show();
                }
                else if (!S_password.equals(password_SP)){
                    Toast.makeText(Logeo_usuario.this, "La contraseña no es la correcta", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Logeo_usuario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void InicializarVariables(){
        EtPasswordU = findViewById(R.id.EtPasswordU);
        BtnIngresar = findViewById(R.id.BtnIngresar);
        //BtnInicioSesionBiometrico = findViewById(R.id.BtnInicioSesionBiometrico);
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        //Ib_Aviso = findViewById(R.id.Ib_Aviso);
    }
}