package es.upm.miw.rayovallecano;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.upm.miw.rayovallecano.models.Futbolista;

public class FutbolistaAdapter extends ArrayAdapter<Futbolista> {

    Context _contexto;
    private ArrayList<Futbolista> _futbolistas;

    public FutbolistaAdapter(Context context, ArrayList<Futbolista> futbolistas) {
        super(context, R.layout.layout_listado_futbolista, futbolistas);
        this._futbolistas = futbolistas;
        this._contexto = context;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) _contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_listado_futbolista, null);
        }
        Futbolista futbolista = this._futbolistas.get(position);

        Log.i("ADAPTER", futbolista.toString());

        if (futbolista != null) {
            TextView tvId = (TextView) convertView.findViewById(R.id.tvFutbolistaId);
            tvId.setText(String.format("%d", futbolista.get_id()));

            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvFutbolistaNombre);
            tvNombre.setText(futbolista.get_nombre());

            TextView tvEquipo = (TextView) convertView.findViewById(R.id.tvFutbolistaEquipo);
            tvEquipo.setText(futbolista.get_equipo());

            ImageView ivLesionado = (ImageView) convertView.findViewById(R.id.ivFutbolistaLesionado);
            ivLesionado.setImageResource(futbolista.is_lesionado()
                ? android.R.drawable.button_onoff_indicator_on
                : android.R.drawable.button_onoff_indicator_off );
        }

        return convertView;
    }
}
