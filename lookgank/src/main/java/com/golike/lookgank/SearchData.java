package com.golike.lookgank;

import java.util.List;

/**
 * Created by admin on 2018/2/2.
 */

public class SearchData {

    boolean error;

   private List<GANK> results;

    public List<GANK> getResults() {
        return results;
    }

    public void setResults(List<GANK> results) {
        this.results = results;
    }

     class GANK {

        String _id;

        String createdAt;

        String desc;

        List<String> images;

        String publishedAt;

        private String source;

        private String type;

        private String who;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }

}
