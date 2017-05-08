package mikkoluhtasaari.gwentdeckbuilder;

import android.view.View;

/**
 * Interface for clickListener
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 07 May 2017
 * @since 1.0
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
