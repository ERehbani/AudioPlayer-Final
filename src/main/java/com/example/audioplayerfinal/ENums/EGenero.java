package com.example.audioplayerfinal.ENums;

public enum EGenero {
    POP ("Pop"),
    ROCK("Rock"),
    RAP("Rap"),
    TRAP("Trap"),
    JAZZ("Jazz"),
    METAL("Metal"),
    TECNO("Tecno"),
    INDIE("Indie"),
    SALSA("Salsa"),
    TANGO("Tango"),
    REGGAETON("Reggaeton"),
    REGGAE("Reggae"),
    CUMBIA("Cumbia");

    private final String genero;

    EGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

}
