package dk.tec.pokepokemon;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pokemoncard> deck;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        queue = Volley.newRequestQueue(getApplicationContext());
        getAllCards();
    }

    void getAllCards(){
        String url = "https://api.tcgdex.net/v2/en/cards";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    deck = new Gson().fromJson(response,
                            new TypeToken<List<Pokemoncard>>(){}.getType());
                    Toast.makeText(getApplicationContext(),
                            "Amount of cards: " + deck.size(),
                            Toast.LENGTH_LONG).show();
                },
                volleyError -> {
                    Log.e("Volley", volleyError.getMessage());
                });
        queue.add(request);
    }
}