package com.tauan.somma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.database.dao.UserDAO;
import com.tauan.somma.database.model.UserModel;
import com.tauan.somma.util.Sharad;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsuario, edtSenha;
    private TextView linearCriar;
    private Button btnLogin;
    private UserDAO userDAO;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userDAO = new UserDAO(LoginActivity.this);

        sharad = new Sharad(LoginActivity.this);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);

        //KEY_USER diferente de null, carrega usuario na tela login
        if(!(sharad.getString(Sharad.KEY_USER).isEmpty())) {
            edtUsuario.setText(sharad.getString(Sharad.KEY_USER));
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtUsuario.getText().toString().isEmpty()){
                    edtUsuario.setError("Digite seu usuário");
                }else if(edtSenha.getText().toString().isEmpty()){
                    edtSenha.setError("Digite sua senha");
                }else {
                    UserModel model = userDAO.Select(edtUsuario.getText().toString(), edtSenha.getText().toString());

                    if (model != null) {
                        Toast.makeText(LoginActivity.this, "Usuário logado.", Toast.LENGTH_LONG).show();
                        sharad.put(Sharad.KEY_ID_USER, model.getId());
                        sharad.put(Sharad.KEY_USER, edtUsuario.getText().toString());
                        sharad.put(Sharad.KEY_SENHA, edtSenha.getText().toString());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário Não Encontrado!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        linearCriar = findViewById(R.id.linearCriar);
        linearCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

    }
}