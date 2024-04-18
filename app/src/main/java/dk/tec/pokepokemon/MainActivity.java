package dk.tec.pokepokemon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pokemoncard> deck;
    public static RequestQueue queue;

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
        initGui();
    }


    void initGui(){
        findViewById(R.id.btn_api).setOnClickListener(view -> getAllCards());
        findViewById(R.id.btn_load).setOnClickListener(view -> loadCards());
    }
    void getAllCards(){
        String url = "https://api.tcgdex.net/v2/en/cards";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                response -> {
                    convertToList(response);
                    Toast.makeText(getApplicationContext(),
                            "Amount of cards: " + deck.size(),
                            Toast.LENGTH_LONG).show();
                    saveCards(response);
                    fillAdapter();
                },
                volleyError -> {
                    Log.e("Volley", volleyError.getMessage());
                });
        queue.add(request);
    }

    private void fillAdapter() {
        CardAdaptor adaptor = new CardAdaptor(getApplicationContext(), deck);
        GridView gridView = findViewById(R.id.gv_deck);
        gridView.setAdapter(adaptor);
    }

    void convertToList(String json){
        deck = new Gson().fromJson(json,
                new TypeToken<List<Pokemoncard>>(){}.getType());
    }

    void saveCards(String data){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("cards", data);
        editor.apply();
    }

    void loadCards(){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String data = sharedPref.getString("cards", null);
        if (data != null){
            convertToList(data);
            fillAdapter();
        }
        else
            Toast.makeText(getApplicationContext(),
                    "No cards loaded",
                    Toast.LENGTH_LONG).show();
    }

}