package org.telegram.ui.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import org.telegram.SQLite.ElementDownload;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC.TL_document;
import org.telegram.ui.StickersActivity.TouchHelperCallback;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<downloadItemViewHolder> {
    public List ED;
    Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnCheckedChangeListener mOnchClickListener;


    public RVAdapter(Context context, List list, OnItemClickListener onItemClickListener, OnCheckedChangeListener onCheckedChangeListener) {
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
        this.mOnchClickListener = onCheckedChangeListener;
        this.ED = list;
    }

    public void dataSetChanged() {
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.ED.size();
    }

    @Override
    public void onBindViewHolder(downloadItemViewHolder org_telegram_ui_Adapters_downloadItemViewHolder, final int i) {
        CharSequence formatFileSize;
        ElementDownload elementDownload = (ElementDownload) this.ED.get(i);
        TL_document tL_document = new TL_document();
        tL_document.id = Long.parseLong(elementDownload.getId());
        tL_document.access_hash = Long.parseLong(elementDownload.getAccess_hash());
        tL_document.date = Integer.parseInt(elementDownload.getDate());
        tL_document.mime_type = elementDownload.getMime_type();
        tL_document.size = elementDownload.getSize();
        tL_document.dc_id = elementDownload.getDc_id();
        tL_document.user_id = elementDownload.getUser_id();
        if (!elementDownload.state || elementDownload.getProg() == 1) {
            org_telegram_ui_Adapters_downloadItemViewHolder.item_play.setImageResource(R.drawable.pl_play);
        } else {
            org_telegram_ui_Adapters_downloadItemViewHolder.item_play.setImageResource(R.drawable.pl_pause);
        }
        String str;
        if (elementDownload.getProg() == TouchHelperCallback.ALPHA_FULL) {
            formatFileSize = AndroidUtilities.formatFileSize((long) tL_document.size);
            str = "";
        } else {
            formatFileSize = AndroidUtilities.formatFileSize((long) tL_document.size);
            str = "";
        }
        org_telegram_ui_Adapters_downloadItemViewHolder.file_name.setText(elementDownload.getType() == 3 ? FileLoader.getAttachFileName(tL_document) : elementDownload.file_name);
        org_telegram_ui_Adapters_downloadItemViewHolder.item_play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RVAdapter.this.mOnItemClickListener.onItemClick(view, i);
            }
        });
        org_telegram_ui_Adapters_downloadItemViewHolder.item_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RVAdapter.this.mOnItemClickListener.onItemClick(view, i);
            }
        });
        org_telegram_ui_Adapters_downloadItemViewHolder.cv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                RVAdapter.this.mOnItemClickListener.onItemClick(view, i);
            }
        });
        org_telegram_ui_Adapters_downloadItemViewHolder.itemCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                RVAdapter.this.mOnchClickListener.onItemCh(compoundButton, z, i);
            }
        });
        org_telegram_ui_Adapters_downloadItemViewHolder.itemCheck.setChecked(elementDownload.isCheck());
        org_telegram_ui_Adapters_downloadItemViewHolder.totalSize.setText(formatFileSize);
        org_telegram_ui_Adapters_downloadItemViewHolder.progressbar.setProgress((int) (elementDownload.getProg() * 100.0f));
        org_telegram_ui_Adapters_downloadItemViewHolder.downloadedSize.setText(AndroidUtilities.formatFileSize((long) ((((int) (elementDownload.getProg() * 100.0f)) * tL_document.size) / 100)));
        org_telegram_ui_Adapters_downloadItemViewHolder.percentage.setText(String.valueOf((int) (elementDownload.getProg() * 100.0f)) + "%");
    }

    public downloadItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new downloadItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_download, viewGroup, false));
    }

    public interface OnCheckedChangeListener {
        void onItemCh(CompoundButton compoundButton, boolean z, int i);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int i);
    }


}
