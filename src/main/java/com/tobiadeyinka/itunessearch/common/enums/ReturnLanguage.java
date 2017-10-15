package com.tobiadeyinka.itunessearch.common.enums;

/**
 * Enumeration of supported return value languages.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum ReturnLanguage {
    ENGLISH("en_us"),
    JAPANESE("ja_jp");

    private String codeName;

    ReturnLanguage(String codeName) {
        this.codeName = codeName;
    }

    /**
     *
     * @return the language codename
     */
    public String getCodeName() {
        return codeName;
    }

}
