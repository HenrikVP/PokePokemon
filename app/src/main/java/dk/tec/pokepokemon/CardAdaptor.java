package dk.tec.pokepokemon;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardAdaptor extends ArrayAdapter<Pokemoncard> {

    Context context;

    public CardAdaptor(@NonNull Context context, @NonNull ArrayList<Pokemoncard> pokemoncards) {
        super(context, 0, pokemoncards);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null)
            item = LayoutInflater.from(getContext()).inflate(
                    R.layout.card_view, parent, false);

        Pokemoncard pokemoncard = getItem(position);

        if (pokemoncard.getImage() != null) {
            ImageView image = item.findViewById(R.id.img_cardimage);
            Picasso.get().load(pokemoncard.getImage()+"/low.jpg").into(image);
        }

        item.setOnClickListener(view -> {
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "https://api.tcgdex.net/v2/en/cards/" + pokemoncard.getId(),
                    response -> {
                        Intent myIntent = new Intent(context, CardActivity.class);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        myIntent.putExtra("card", response); //Optional parameters
                        context.startActivity(myIntent);
                    },
                    volleyError -> {
                        Log.e("Volley", volleyError.getMessage());
                    });
            MainActivity.queue.add(request);
        });

        return item;
        //return super.getView(position, convertView, parent);
    }
}
