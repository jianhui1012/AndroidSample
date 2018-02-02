package com.golike.lookgank;

import java.util.List;

/**
 * Created by admin on 2018/2/2.
 */

public class SearchData {

     boolean error;

     List<GANK> results;

    private class GANK {

        String _id;

        String createdAt;

        String desc;

        List<String> images;

        String publishedAt;

        String source;

        String type;

        String who;
    }

}
