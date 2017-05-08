package mikkoluhtasaari.gwentdeckbuilder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Makes it possible to use recyclerview to display cards.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 03 May 2017
 * @since 1.0
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    /**
     * Contains cards to be displayed.
     */
    private List<Card> cards;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        /**
         * Holds cards name
         */
        public TextView cardName;

        /**
         * Holds cards group
         */
        public TextView cardGroup;

        /**
         * Constructor
         *
         * @param view view to be populated
         */
        public MyViewHolder(View view) {
            super(view);
            cardName = (TextView) view.findViewById(R.id.cardName);
            cardGroup = (TextView) view.findViewById(R.id.cardGroup);
        }
    }


    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card card = cards.get(position);
        holder.cardName.setText(card.getName());
        holder.cardGroup.setText(card.getGroup().getName());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
