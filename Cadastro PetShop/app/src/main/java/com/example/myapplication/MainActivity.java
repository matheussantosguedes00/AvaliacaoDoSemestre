package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.DAO.DAO;
import com.example.myapplication.objetos.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    EditText editTextNome;
    EditText editTextNomeAnimal;
    EditText editTextIdade;
    Switch switchSexo;
    Button botaoSalvar;
    ListView listView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        editTextNome=findViewById(R.id.editTextNome);
        editTextNomeAnimal=findViewById(R.id.editTextNomeAnimal);
        editTextIdade=findViewById(R.id.editTextIdade);
        switchSexo=findViewById(R.id.switchSexo);
        botaoSalvar=findViewById(R.id.botaosalvar);
        listView=findViewById(R.id.listView);
        
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
       
                if(!(editTextNome.getText().toString().equals("")||editTextNomeAnimal.getText().toString().equals("")||editTextIdade.getText().toString().equals(""))){

                    String sexo;

                    if(switchSexo.isChecked()){
                        sexo="F";
                    }else{
                        sexo="M";
                    }

                    DAO dao =new DAO(getApplicationContext());
                    Pessoa pessoa =new Pessoa();
                    pessoa.setNome(editTextNome.getText().toString());
                    pessoa.setNomeanimal(editTextNomeAnimal.getText().toString());
                    pessoa.setSexo(sexo);
                    pessoa.setIdade(Integer.valueOf(editTextIdade.getText().toString()));
                    dao.inserePessoa(pessoa);
                    dao.close();

                    Limparformulario();

                    DAO dao2=new DAO(getApplicationContext());
                    List<Pessoa>pessoas = dao2.buscaPessoa();
                    List<String>nomes=new ArrayList<String>();



                    for(Pessoa nomeBuscado: pessoas){
                        nomes.add(nomeBuscado.getNome()+"  "+nomeBuscado.getNomeanimal() +"  "+nomeBuscado.getSexo()+"  "+String.valueOf(nomeBuscado.getIdade()));


                    }



                    ArrayAdapter<String> adapter =new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,nomes);
                    listView.setAdapter(adapter);


                }else{
                    Toast.makeText(getApplicationContext(),"Por favor preencha todos campos",Toast.LENGTH_SHORT).show();
                    
                }
               
            }
        });
        
        
        
        
    }

    private void Limparformulario() {
        editTextNome.setText("");
        editTextNome.requestFocus();
        editTextNomeAnimal.setText("");
        editTextIdade.setText("");
        switchSexo.setChecked(false);
    }
}