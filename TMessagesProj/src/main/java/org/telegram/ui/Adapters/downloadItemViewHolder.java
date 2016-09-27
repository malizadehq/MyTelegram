package org.telegram.ui.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rey.material.widget.CheckBox;
import com.rey.material.widget.ProgressView;

import org.telegram.messenger.R;

public class downloadItemViewHolder extends RecyclerView.ViewHolder {
    public ProgressView f6351c;
    public View container;
    public CardView cv;
    public TextView downloadedSize;
    public TextView file_name;
    public CheckBox itemCheck;
    public ImageView item_delete;
    public ImageView item_play;
    public TextView percentage;
    public ProgressBar progressbar;
    public TextView totalSize;

    public downloadItemViewHolder(View view) {
        super(view);
        this.cv = (CardView) view.findViewById(R.id.cardView);
        this.file_name = (TextView) view.findViewById(R.id.text4);
        this.itemCheck = (CheckBox) view.findViewById(R.id.checkBox);
        this.progressbar = (ProgressBar) view.findViewById(R.id.pProgressBar);
        this.downloadedSize = (TextView) view.findViewById(R.id.text);
        this.totalSize = (TextView) view.findViewById(R.id.text2);
        this.percentage = (TextView) view.findViewById(R.id.text3);
        this.item_play = (ImageView) view.findViewById(R.id.img);
        this.item_delete = (ImageView) view.findViewById(R.id.img2);
        this.container = view;
    }
}
