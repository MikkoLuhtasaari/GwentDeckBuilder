package mikkoluhtasaari.gwentdeckbuilder;

import java.util.List;

/**
 * ResultSet class.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 15 Apr 2017
 * @since 1.0
 */

public class ResultSet {

    /**
     * Knows how many results there were.
     */
    private int count;

    /**
     * Contains list of results.
     */
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
