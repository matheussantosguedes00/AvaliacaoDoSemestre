package br.edu.uemg.progiv.appcadanimal;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.edu.uemg.progiv.appcadanimal.Adapters.AnimaisAdapter;
import br.edu.uemg.progiv.appcadanimal.DAO.AnimaisDAO;
import br.edu.uemg.progiv.appcadanimal.Model.AnimaisModel;


public class MainActivity extends AppCompatActivity {


    ListView listView;
    Button btnCadastrar;
    AnimaisDAO animaisDAO;
    ArrayList<AnimaisModel> listaAnimais;
    AnimaisAdapter adapter;
    AnimaisModel animais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listaAnimais);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Animais.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                animais = (AnimaisModel) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, Animais.class);
                intent.putExtra("selectAnimal", animais);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                animais = (AnimaisModel) parent.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuItem = menu.add("Deletar animal");
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                animaisDAO = new AnimaisDAO(MainActivity.this);
                animaisDAO.deletarAnimal(animais);
                animaisDAO.close();
                carregarListaAnimal();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaAnimal();
    }

    //Método para buscar os dados no BD:
    public void carregarListaAnimal() {
        animaisDAO = new AnimaisDAO(MainActivity.this);
        listaAnimais = animaisDAO.listaAnimais();
        animaisDAO.close();
        if (listaAnimais != null) {
            adapter = new AnimaisAdapter(MainActivity.this, listaAnimais);
            listView.setAdapter(adapter);
        }
    }

}