package br.com.vaciprev.vaciperv.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import br.com.vaciprev.vaciperv.MainActivity;
import br.com.vaciprev.vaciperv.R;
import br.com.vaciprev.vaciperv.modelos.DateHelper;
import br.com.vaciprev.vaciperv.modelos.Vacina;
import br.com.vaciprev.vaciperv.modelos.VacinaDAO;


public class VacinaAdapter extends RecyclerView.Adapter<VacinaAdapter.VacinaHolder> {

    private Context context;
    private ArrayList<Vacina> vacinas;

    public VacinaAdapter(Context context, ArrayList<Vacina> vacinas) {
        this.context = context;
        this.vacinas = vacinas;
    }

    public VacinaAdapter(Context context) {
        this.context = context;
        this.vacinas = new ArrayList<Vacina>();
    }

    @Override
    public VacinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vacina_card, null);

        VacinaHolder vacinaHolder = new VacinaHolder(view);

        return vacinaHolder;
    }

    @Override
    public void onBindViewHolder(VacinaHolder holder, int position) {

        Vacina vacina = vacinas.get(position);

        holder.vacinaTitulo.setText(vacina.getNome());
        holder.vacinaData.setText(DateHelper.getDataFormated(new Date(vacina.getDataDeAplicacao())));

        if (vacina.hasSegundaDose()) {
            holder.vacinaSegundaData.setText(DateHelper.getDataFormated(new Date(vacina.getDataDaSegundaDose())));
        }

    }

    public void addVacina(Vacina v) {
        this.vacinas.add(v);
        notifyDataSetChanged();
    }

    private void remove(Vacina vacina) {
        this.vacinas.remove(vacina);
        notifyDataSetChanged();
    }

    public void update() {
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return vacinas.size();
    }

    public void clearList() {
        this.vacinas.clear();
    }

    public class VacinaHolder extends RecyclerView.ViewHolder {

        TextView vacinaTitulo;
        TextView vacinaData;
        TextView vacinaSegundaData;

        public VacinaHolder(final View itemView) {
            super(itemView);

            vacinaTitulo = (TextView) itemView.findViewById(R.id.vacina_title);
            vacinaData = (TextView) itemView.findViewById(R.id.vacina_data);
            vacinaSegundaData = (TextView) itemView.findViewById(R.id.vacina_data_segunda_dose);

            itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Log.d("RemoveFromDB", "DBVACINADEVERIASERREMOVIDA");

                    int vacinaPosition = getLayoutPosition();

                    VacinaDAO.remove(vacinas.get(vacinaPosition));
                    notifyItemRemoved(vacinaPosition);


                    //remove(vacinas.get(getLayoutPosition()));
                    return true;
                }
            });

        }
    }


}
