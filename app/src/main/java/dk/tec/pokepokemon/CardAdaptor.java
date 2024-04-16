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

import java.util.List;

public class CardAdaptor extends ArrayAdapter<Pokemoncard> {

    public CardAdaptor(@NonNull Context context, int resource, @NonNull List<Pokemoncard> pokemoncards) {
        super(context, resource, pokemoncards);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null)
            item = LayoutInflater.from(getContext()).inflate(R.layout.card_view, parent);

        Pokemoncard pokemoncard = getItem(position);
        ImageView image = item.findViewById(R.id.img_cardimage);
        Picasso.get().load(pokemoncard.getImage()).into(image);
        return super.getView(position, convertView, parent);
    }
}
