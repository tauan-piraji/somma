package com.tauan.somma.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tauan.somma.R;
import com.tauan.somma.database.model.ViagemModel;
import com.tauan.somma.util.Sharad;

import java.util.List;

public class ViagemAdapter extends BaseAdapter {

    private Activity activity;
    private List<ViagemModel> listaViagens;

    private Sharad sharad;

    public ViagemAdapter(final Activity activity, final List<ViagemModel> listaViagens) {
        this.activity = activity;
        this.listaViagens = listaViagens;
    }

    @Override
    public int getCount() {
        return listaViagens.size();
    }

    @Override
    public Object getItem(int position) {
        return listaViagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.item_lista_viagem, parent, false);
        }

        TextView destinoViagem = convertView.findViewById(R.id.destinoViagem);
        destinoViagem.setText("Destino: "+((ViagemModel)getItem(position)).getDestino());

        TextView notaViagem = convertView.findViewById(R.id.notaViagem);
        notaViagem.setText("Nota: "+((ViagemModel)getItem(position)).getNota());

        TextView gastosViage = convertView.findViewById(R.id.gastosViage);
        gastosViage.setText("Gastos: "+((ViagemModel)getItem(position)).getValorTotal());

        LinearLayout linearList = convertView.findViewById(R.id.linearList);
        linearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return convertView;
    }
}