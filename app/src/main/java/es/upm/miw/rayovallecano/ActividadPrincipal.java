package es.upm.miw.rayovallecano;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        futbolistas = repositorio.getAll();
        ArrayAdapter<Futbolista> adaptador = new FutbolistaAdapter(this, futbolistas);
        lvListadoFutbolistas = (ListView) findViewById(R.id.lvListadoFutbolistas);
        lvListadoFutbolistas.setAdapter(adaptador);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
