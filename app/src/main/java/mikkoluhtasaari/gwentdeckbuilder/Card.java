package mikkoluhtasaari.gwentdeckbuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Card class.
 *
 * @author Mikko Luhtasaari
 * @version 1.0, 15 Apr 2017
 * @since 1.0
 */
public class Card implements Serializable {

    /**
     * Contains faction information.
     */
    private FactionBean faction;

    /**
     * Contains flavor.
     */
    private String flavor;

    /**
     * Contains group.
     */
    private GroupBean group;

    /**
     * Contains link.
     */
    private String href;

    /**
     * Contains info.
     */
    private String info;

    /**
     * Contains name.
     */
    private String name;

    /**
     * Contains short part of url.
     */
    private String uuid;

    /**
     * Contains categories.
     */
    private List<CategoriesBean> categories;

    /**
     * Contains positions.
     */
    private List<String> positions;

    /**
     * Contains variations.
     */
    private List<VariationsBean> variations;

    public FactionBean getFaction() {
        return faction;
    }

    public void setFaction(FactionBean faction) {
        this.faction = faction;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<String> getPositions() {
        return positions;
    }

    public void setPositions(List<String> positions) {
        this.positions = positions;
    }

    public List<VariationsBean> getVariations() {
        return variations;
    }

    public void setVariations(List<VariationsBean> variations) {
        this.variations = variations;
    }

    public static class FactionBean implements Serializable {
        /**
         * href : https://api.gwentapi.com/v0/factions/C21SnrUdSSW7ttfGNkOzeA
         * name : Neutral
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

        @Override
        public String toString() {
            return name;
        }
    }

    public static class GroupBean implements Serializable {
        /**
         * href : https://api.gwentapi.com/v0/groups/dP0aF_XZQ_mbH3TK-I7Xzg
         * name : Bronze
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

        @Override
        public String toString() {
            return name;
        }
    }

    public static class CategoriesBean implements Serializable {
        /**
         * href : https://api.gwentapi.com/v0/categories/LejU5Ge4R3OJRKwfIYd7qQ
         * name : Special
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

        @Override
        public String toString() {
            return name;
        }
    }

    public static class VariationsBean implements Serializable {
        /**
         * availability : BaseSet
         * href : https://api.gwentapi.com/v0/cards/a0-QORHCQJeH17bRNdiCTA/variations/J0g7_sHISDCUUylBNjF0pQ
         * rarity : {"href":"https://api.gwentapi.com/v0/rarities/8uacaQa2S3iNKuWL65YgYw","name":"Common"}
         */

        private String availability;
        private String href;
        private RarityBean rarity;

        public String getAvailability() {
            return availability;
        }

        public void setAvailability(String availability) {
            this.availability = availability;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public RarityBean getRarity() {
            return rarity;
        }

        public void setRarity(RarityBean rarity) {
            this.rarity = rarity;
        }

        @Override
        public String toString() {
            return rarity.toString();
        }

        public static class RarityBean implements Serializable {
            /**
             * href : https://api.gwentapi.com/v0/rarities/8uacaQa2S3iNKuWL65YgYw
             * name : Common
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

            @Override
            public String toString() {
                return name;
            }
        }
    }

    @Override
    public String toString() {
        return "Card [flavor="+ flavor+", info="+info+", name="+name+", href="+href+"positions="+positions+"categories="+categories+"" +
                ",faction="+faction+", group="+group+"]";
    }
}
