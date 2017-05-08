package mikkoluhtasaari.gwentdeckbuilder;

import java.util.List;

/**
 * Created by M1k1tus on 18-Apr-17.
 */

public class ResultSet {

    private int count;
    private List<ResultsBean> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * href : https://api.gwentapi.com/v0/cards/a0-QORHCQJeH17bRNdiCTA
         * name : Adrenaline Rush
         */

        private String href;
        private String name;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
