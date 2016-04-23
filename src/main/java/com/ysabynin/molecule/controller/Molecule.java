package com.ysabynin.molecule.controller;

/**
 * Created by Evgeni Developer on 23.04.2016.
 */
public enum Molecule {
    ANINOETHANOL("аминоэтанол","2-aminoethanol"),
    THIOETHANOL("тиоэтанол","2-thioethanol"),
    TRIFLUOROETHANOL("трифлюроэтанол","2_2_2-trifluoroethanol"),
    BUTANE("бутан","butane"),
    ETHANE("этан","ethane"),
    METHANE("метан","methane"),
    METHANOL("метанол","methanol"),
    PROPAN_1_OL("пропан-1","propan-1-ol"),
    PROPAN_2_OL("пропан-2","propan-2-ol"),
    PROPANE("пропан","propane"),
    THIOPHENE("тиофен","thiophene"),
    WATER("вода","water");

    private String text;
    private String fileName;

    Molecule(String text, String fileName) {
        this.text = text;
        this.fileName = fileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
