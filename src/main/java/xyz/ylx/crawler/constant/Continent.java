package xyz.ylx.crawler.constant;

public enum Continent {

    欧洲("Europe"),
    亚洲("Asia"),
    非洲("Africa"),
    北美洲("North America"),
    南美洲("South America"),
    大洋洲("Oceania"),
    南极洲("Antarctica");

    private final String continentName;

    Continent(String continentName) {
        this.continentName = continentName;
    }

    public static String convertEn(String cn) {
        Continent continent = Continent.valueOf(cn);
        return continent.continentName;
    }
}
