package com.golike.customviews;

import android.content.Context;

import com.golike.customviews.plugin.image.AlbumBitmapCacheHelper;

/**
 * Created by admin on 2017/8/9.
 */

public class ChatViewManger {

    public static void init(Context context) {
        EditExtensionManager.init(context, "xxxxx");
        EditExtensionManager.getInstance().registerExtensionModule(new DefaultExtensionModule());
        AlbumBitmapCacheHelper.init(context);
    }
}
