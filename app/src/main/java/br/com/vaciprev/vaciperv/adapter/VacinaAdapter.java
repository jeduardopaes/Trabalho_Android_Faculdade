package br.com.vaciprev.vaciperv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.vaciprev.vaciperv.R;
import br.com.vaciprev.vaciperv.modelos.DateHelper;
import br.com.vaciprev.vaciperv.modelos.Vacina;


public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaHolder> {

    private Context context;
    private ArrayList<Vacina> vacinas;

    public VacinaAdapter(Context context, ArrayList<Vacina> vacinas) {
        this.context = context;
        this.vacinas = vacinas;
    }

    @Override
    public VacinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacina_card,null);

        VacinaHolder vacinaHolder = new VacinaHolder(view);

        return vacinaHolder;
    }

    @Override
    public void onBindViewHolder(VacinaHolder holder, int position) {

        Vacina vacina = vacinas.get(position);

        holder.vacinaTitulo.setText(vacina.getNome());
        holder.vacinaData.setText(DateHelper.getDataFormated(vacina.getDataDeAplicacao()));

        if(vacina.hasSegundaDose()){
            holder.vacinaSegundaData.setText(DateHelper.getDataFormated(vacina.getDataDaSegundaDose()));
        }



    }

    @Override
    public int getItemCount() {
        return vacinas.size();
    }

    public static class VacinaHolder extends RecyclerView.ViewHolder{

        TextView vacinaTitulo;
        TextView vacinaData;
        TextView vacinaSegundaData;

        public VacinaHolder(View itemView) {
            super(itemView);

            vacinaTitulo = (TextView) itemView.findViewById(R.id.vacina_title);
            vacinaData = (TextView) itemView.findViewById(R.id.vacina_data);
            vacinaSegundaData = (TextView) itemView.findViewById(R.id.vacina_data_segunda_dose);

        }
    }
}