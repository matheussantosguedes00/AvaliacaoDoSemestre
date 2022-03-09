package br.edu.uemg.progiv.appcadanimal.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.edu.uemg.progiv.appcadanimal.Model.AnimaisModel;
import br.edu.uemg.progiv.appcadanimal.R;


public class AnimaisAdapter extends BaseAdapter {

    Context context;
    List<AnimaisModel> animaisModelList;

    public AnimaisAdapter(Context context, List<AnimaisModel> animaisModelList) {
        this.context = context;
        this.animaisModelList = animaisModelList;
    }

    @Override
    public int getCount() {
        return animaisModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return animaisModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.item_animais,null);
        TextView txtNomeDono = (TextView) v.findViewById(R.id.txtNomeDono);
        TextView txtNomeAnimal = (TextView) v.findViewById(R.id.txtNomeAnimais);
        TextView txtIdadeAnimal = (TextView) v.findViewById(R.id.txtIdadeAnimal);

        txtNomeDono.setText(this.animaisModelList.get(position).getNomeDono());
        txtNomeAnimal.setText(this.animaisModelList.get(position).getNomeAnimal());
        txtIdadeAnimal.setText(String.valueOf(this.animaisModelList.get(position).getIdadeAnimal()));

        return v;
    }
}
