package mikkoluhtasaari.gwentdeckbuilder;

/**
 * Created by M1k1tus on 15-Apr-17.
 */
public class Card {
    private String flavor;
    private String info;
    private String name;
    private String href;
    private String[] positions;
    private Categories categories;
    private Faction faction;
    private Group group;
    private Rarity rarity;

    public Card(String flavor, String info, String name, String href, String[] positions, Categories categories, Faction faction, Group group, Rarity rarity) {
        setFlavor(flavor);
        setInfo(info);
        setName(name);
        setHref(href);
        setPositions(positions);
        setCategories(categories);
        setFaction(faction);
        setGroup(group);
        setRarity(rarity);
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHref(String href) {
        this.href = href;
    }
    public void setPositions(String[] positions) {
        this.positions = positions;
    }
    public void setCategories(Categories categories) {
        this.categories = categories;
    }
    public void setFaction(Faction faction) {
        this.faction = faction;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getFlavor() {
        return flavor;
    }
    public String getInfo() {
        return info;
    }
    public String getName() {
        return name;
    }
    public String getHref() {
        return href;
    }
    public String[] getPositions() {
        return positions;
    }
    public Categories getCategories() {
        return categories;
    }
    public Faction getFaction() {
        return faction;
    }
    public Group getGroup() {
        return group;
    }
    public Rarity getRarity() {
        return rarity;
    }


    /*@Override
    public String toString() {
        return "Card [flavor="+ flavor+", info="+info+", name="+name+", href="+href+"positions="+positions+"categories="+categories+"" +
                ",faction="+faction+", group="+group+",rarity="+rarity+"]";
    }*/
}
