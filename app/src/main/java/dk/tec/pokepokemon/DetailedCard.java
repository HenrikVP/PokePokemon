package dk.tec.pokepokemon;

import java.util.ArrayList;

public class DetailedCard {

    public String category;
    public String id;
    public String illustrator;
    public String image;
    public String localId;
    public String name;
    public String rarity;
    public Set set;
    public Variants variants;
    public int hp;
    public ArrayList<String> types;
    public String evolveFrom;
    public String description;
    public String stage;
    public ArrayList<Attack> attacks;
    public ArrayList<Weakness> weaknesses;
    public int retreat;
    public String regulationMark;
    public Legal legal;

    public class Set{
        public CardCount cardCount;
        public String id;
        public String logo;
        public String name;
        public String symbol;
    }

    public class Variants{
        public boolean firstEdition;
        public boolean holo;
        public boolean normal;
        public boolean reverse;
        public boolean wPromo;
    }

    public class Weakness{
        public String type;
        public String value;
    }

    public class Attack{
        public ArrayList<String> cost;
        public String name;
        public String effect;
        public String damage;
    }

    public class CardCount{
        public int official;
        public int total;
    }

    public class Legal{
        public boolean standard;
        public boolean expanded;
    }
}


