package br.edu.uemg.progiv.appcadanimal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.uemg.progiv.appcadanimal.DAO.AnimaisDAO;
import br.edu.uemg.progiv.appcadanimal.Model.AnimaisModel;

public class Animais extends AppCompatActivity {
    EditText txtNomeDono;
    EditText txtNomeAnimal;
    EditText txtIdadeAnimal;
    Button btnModificar;
    AnimaisModel editarAnimal;
    AnimaisModel animais;
    AnimaisDAO animaisDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animais);

        animais = new AnimaisModel();
        animaisDAO = new AnimaisDAO(Animais.this);

        Intent intent = getIntent();
        //Criando um alias do Main para Animais
        editarAnimal = (AnimaisModel) intent.getSerializableExtra("selectAnimal");

        txtNomeDono = (EditText) findViewById(R.id.nomeDono);
        txtNomeAnimal = (EditText) findViewById(R.id.nomeAnimal);
        txtIdadeAnimal = (EditText) findViewById(R.id.idadeAnimal);
        btnModificar = (Button) findViewById(R.id.btnModificar);

        if (editarAnimal != null) {
            btnModificar.setText("Modificar");
            txtNomeDono.setText(editarAnimal.getNomeDono());
            txtNomeAnimal.setText(editarAnimal.getNomeAnimal());
            txtIdadeAnimal.setText(String.valueOf(editarAnimal.getIdadeAnimal()));
            animais.setId(editarAnimal.getId());
        } else {
            btnModificar.setText("Cadastrar");
        }

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animais.setNomeDono(txtNomeDono.getText().toString());
                animais.setNomeAnimal(txtNomeAnimal.getText().toString());
                animais.setIdadeAnimal(Integer.parseInt(txtIdadeAnimal.getText().toString()));
                if (btnModificar
                        .getText()
                        .toString()
                        .toUpperCase()
                        .equals("CADASTRAR")
                ) {
                    //insert
                    animaisDAO.salvarAnimal(animais);
                } else {
                    //update
                    animaisDAO.alterarAnimal(animais);
                }
                animaisDAO.close();
                finish();
            }
        });

    }
}
