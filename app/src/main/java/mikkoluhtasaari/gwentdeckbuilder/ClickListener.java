package mikkoluhtasaari.gwentdeckbuilder;

import android.view.View;

/**
 * Created by M1k1tus on 07-May-17.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
