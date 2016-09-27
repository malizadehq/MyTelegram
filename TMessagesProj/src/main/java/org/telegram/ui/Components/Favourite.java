package org.telegram.ui.Components;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;

public class Favourite {
    long chat_id;
    long id;

    public Favourite(long id, long chat_id) {
        this.id = id;
        this.chat_id = chat_id;
    }

    public Favourite(long chat_id) {
        this.chat_id = chat_id;
    }

    public static void addFavourite(Long id) {
        ApplicationLoader.databaseHandler.addFavourite(new Favourite(id.longValue()));
    }

    public static void deleteFavourite(Long id) {
        ApplicationLoader.databaseHandler.deleteFavourite(id);
    }

    public static boolean isFavourite(Long id) {
        try {
            return ApplicationLoader.databaseHandler.getFavouriteByChatId(id.longValue()) != null;
        } catch (Throwable e) {
            FileLog.e("tmessages", e);
            return false;
        }
    }

    public long getChatID() {
        return this.chat_id;
    }

    public void setChatID(long chat_id) {
        this.chat_id = chat_id;
    }

    public long getID() {
        return this.id;
    }

    public void setID(long id) {
        this.id = id;
    }
}
