package mikkoluhtasaari.gwentdeckbuilder;

/**
 * Created by M1k1tus on 03-May-17.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private List<Card> cards;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cardName, cardGroup;

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
