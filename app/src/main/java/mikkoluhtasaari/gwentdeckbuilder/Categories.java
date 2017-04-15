package mikkoluhtasaari.gwentdeckbuilder;

/**
 * Created by M1k1tus on 15-Apr-17.
 */
public class Categories {
    private String href;
    private String name;

    public Categories(String href, String name) {
        setHref(href);
        setName(name);
    }

    public void setHref(String href) {
        this.href = href;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }
    public String getName() {
        return name;
    }
}
