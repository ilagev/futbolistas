package es.upm.miw.rayovallecano;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import es.upm.miw.rayovallecano.models.Futbolista;
import es.upm.miw.rayovallecano.models.RepositorioFutbolistas;

public class ActividadPrincipal extends AppCompatActivity {

    ArrayList<Futbolista> futbolistas;
    ListView lvListadoFutbolistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RepositorioFutbolistas repositorio = new RepositorioFutbolistas(this);

        int num = (int) (100 * Math.random());
        Log.i("Num", String.format("%d", repositorio.add(
                new Futbolista(num, "Jugador " + String.format("%d", num), num, num % 2 == 0, "Primera", null))));

        repositorio.add(
                new Futbolista(++num, "Jugador " + String.format("%d", num), num, num% 2 == 0, "Primera",
                        "http://j4loxa.com/sna/TAEE/2011/img/upm.png")
        );

        futbolistas = repositorio.getAll();
        ArrayAdapter<Futbolista> adaptador = new FutbolistaAdapter(this, futbolistas);
        lvListadoFutbolistas = (ListView) findViewById(R.id.lvListadoFutbolistas);
        lvListadoFutbolistas.setAdapter(adaptador);

        lvListadoFutbolistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO editar futbolistas
                // Toast.makeText(contexto, futbolistas.get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActividadPrincipal.this, ActividadMostrarFutbolista.class);
                // TO DO Parcelable
                intent.putExtra("MOSTRAR_Futbolista", futbolistas.get(position));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // TODO Ajustes
                break;
            case R.id.accionVaciar:
                // TODO dialog(confirmar) -> vaciar  tabla
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
