package dk.tec.pokepokemon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CardAdaptor extends ArrayAdapter<Pokemoncard> {

    public CardAdaptor(@NonNull Context context, @NonNull ArrayList<Pokemoncard> pokemoncards) {
        super(context, 0, pokemoncards);
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
        return item;
        //return super.getView(position, convertView, parent);
    }
}
