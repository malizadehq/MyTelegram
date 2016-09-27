/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2016.
 */

package org.telegram.ui.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildConfig;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.ui.Cells.DividerCell;
import org.telegram.ui.Cells.DrawerActionCell;
import org.telegram.ui.Cells.DrawerProfileCell;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Components.LayoutHelper;

public class DrawerLayoutAdapter extends BaseAdapter {

    private Context mContext;


    public DrawerLayoutAdapter(Context context) {
        mContext = context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return !(i == 0 || i == 1);
    }

    @Override
    public int getCount() {
        int count = 0;
        if (BuildConfig.IsDownloadManagerEnable) {
            count = 19;
        } else {
            count = 17;
        }
        return UserConfig.isClientActivated() ? count : 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        SharedPreferences themePrefs = ApplicationLoader.applicationContext.getSharedPreferences(AndroidUtilities.THEME_PREFS, AndroidUtilities.THEME_PREFS_MODE);

        if (type == 0) {
            if (view == null) {
                view = new DrawerProfileCell(mContext);
            }
            ((DrawerProfileCell) view).setUser(MessagesController.getInstance().getUser(UserConfig.getClientUserId()));
            //Teleh
            ((DrawerProfileCell) view).refreshAvatar(themePrefs.getInt("drawerAvatarSize", 64), themePrefs.getInt("drawerAvatarRadius", 32));
        } else if (type == 1) {
            if (view == null) {
                view = new EmptyCell(mContext, AndroidUtilities.dp(8));
            }
        } else if (type == 2) {
            if (view == null) {
                view = new DividerCell(mContext);
            }
        } else if (type == 3) {
            int color = themePrefs.getInt("drawerIconColor", 0xff737373);
            if (view == null) {
                view = new DrawerActionCell(mContext);
            }
            DrawerActionCell actionCell = (DrawerActionCell) view;
    /*        if (i == 2) {
                Drawable addmember = mContext.getResources().getDrawable(R.drawable.addmember);
                addmember.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("MemberGir", R.string.MemberGir), addmember);
            }*/
            if (i == 3) {
                Drawable menu_newgroup = mContext.getResources().getDrawable(R.drawable.menu_newgroup);
                menu_newgroup.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("NewGroup", R.string.NewGroup), menu_newgroup);
            } else if (i == 4) {
                Drawable menu_secret = mContext.getResources().getDrawable(R.drawable.menu_secret);
                menu_secret.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("NewSecretChat", R.string.NewSecretChat), menu_secret);
            } else if (i == 5) {
                Drawable menu_broadcast = mContext.getResources().getDrawable(R.drawable.menu_broadcast);
                menu_broadcast.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("NewChannel", R.string.NewChannel), menu_broadcast);
            } else if (i == 7) {
                Drawable ic_menu_me = mContext.getResources().getDrawable(R.drawable.ic_menu_me);
                ic_menu_me.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("MyProfile", R.string.MyProfile), ic_menu_me);
            } else if (i == 8) {
                Drawable ic_menu_finder = mContext.getResources().getDrawable(R.drawable.ic_menu_finder);
                ic_menu_finder.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("FindID", R.string.FindID), ic_menu_finder);
            } else if (i == 9) {
                Drawable ic_menu_online = mContext.getResources().getDrawable(R.drawable.ic_menu_online);
                ic_menu_online.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("OnlineContacts", R.string.OnlineContacts), ic_menu_online);
            } else if (i == 10) {
                Drawable menu_contacts = mContext.getResources().getDrawable(R.drawable.menu_contacts);
                menu_contacts.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("Contacts", R.string.Contacts), menu_contacts);
            } else if (i == 11) {
                Drawable menu_invite = mContext.getResources().getDrawable(R.drawable.menu_invite);
                menu_invite.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("InviteFriends", R.string.InviteFriends), menu_invite);
            } else if (i == 12) {
                Drawable menu_coins = mContext.getResources().getDrawable(R.drawable.menu_coins);
                menu_coins.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("ContactsChanges", R.string.ContactsChanges), menu_coins);
            } else if (i == 13) {
                Drawable ic_menu_cleaner = mContext.getResources().getDrawable(R.drawable.ic_menu_cleaner);
                ic_menu_cleaner.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("Cleaner", R.string.Cleaner), ic_menu_cleaner);
            } else if (i == 14) {
                Drawable menu_settings = mContext.getResources().getDrawable(R.drawable.menu_settings);
                menu_settings.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("Settings", R.string.Settings), menu_settings);
            } else if (i == 2) {
                //     Drawable ic_menu_telehsettings = mContext.getResources().getDrawable(R.drawable.ic_menu_telehsettings);
                //     ic_menu_telehsettings.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("MyTelegramSettings", R.string.MyTelegramSettings), R.drawable.ic_menu_telehsettings);
            } else if (i == 15) {
                Drawable ic_menu_filter = mContext.getResources().getDrawable(R.drawable.ic_menu_filter);
                ic_menu_filter.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("TelehTheme", R.string.TelehTheme), ic_menu_filter);
            } else if (i == 16) {
                if (BuildConfig.IsDownloadManagerEnable) {
                    Drawable menu_anti_report = mContext.getResources().getDrawable(R.drawable.menu_anti_report);
                    menu_anti_report.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    actionCell.setTextAndIcon(LocaleController.getString("AntiReport", R.string.AntiReport), menu_anti_report);
                } else {
                    Drawable menu_help = mContext.getResources().getDrawable(R.drawable.menu_help);
                    menu_help.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    actionCell.setTextAndIcon(LocaleController.getString("MyTelegramChanel", R.string.MyTelegramChanel), menu_help);
                }
            } else if (i == 17) {
                if (BuildConfig.IsDownloadManagerEnable) {
                    Drawable ic_menu_dl = mContext.getResources().getDrawable(R.drawable.ic_menu_dl);
                    ic_menu_dl.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    actionCell.setTextAndIcon(LocaleController.getString("DownloadManager", R.string.DownloadManager), ic_menu_dl);
                } else {
                    actionCell.setVisibility(View.GONE);
                    actionCell.setLayoutParams(LayoutHelper.createFrame(0, 0));

                }
            } else if (i == 18) {
                Drawable menu_help = mContext.getResources().getDrawable(R.drawable.menu_help);
                menu_help.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                actionCell.setTextAndIcon(LocaleController.getString("MyTelegramChanel", R.string.MyTelegramChanel), menu_help);
            }// else if (i == 17) {
            //   actionCell.setTextAndIcon(LocaleController.getString("UserChange", R.string.UserChange), R.drawable.menu_help);
            //   }
            // else if (i == 16) {
            //     actionCell.setTextAndIcon(LocaleController.getString("UserManage", R.string.UserManage), R.drawable.menu_help);
            //  }
        }

        return view;
    }

    @Override
    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        } else if (i == 1) {
            return 1;
        } else if (!BuildConfig.IsIAPenable && i == 2) {
            return 1;
        } else if (i == 6) {
            return 2;
        }
        return 3;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public boolean isEmpty() {
        return !UserConfig.isClientActivated();
    }
}
