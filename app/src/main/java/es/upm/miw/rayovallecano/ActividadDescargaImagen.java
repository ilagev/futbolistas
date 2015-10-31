package es.upm.miw.rayovallecano;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActividadDescargaImagen extends AppCompatActivity {

    final static String URL_OBJETIVO = "https://avatars1.githubusercontent.com/u/5365410";

    ImageView ivContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);

        ivContenido = (ImageView) findViewById(R.id.ivContenido);

        // android-23: acceso contenido desde actividad principal -> NetworkOnMainThreadException
        // URL url = new URL(URL_OBJETIVO);
        // HttpURLConnection http = (HttpURLConnection) url.openConnection();

        // Mostrar contenido
        TareaCargarImagen tarea = new TareaCargarImagen();
        ivContenido.setTag(URL_OBJETIVO);
        tarea.execute(ivContenido);
    }

    private class TareaCargarImagen extends AsyncTask<ImageView, Void, Bitmap> {

        ImageView imageView = null;

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            Bitmap bmp = null;
            try {
                URL ulrn = new URL(URL_OBJETIVO);   // imageView.getTag()
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    } // TareaCargarImagen

}