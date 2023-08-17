package ru.meryakubov;

public enum Locale {
    DE("Deutsch"),
    EN("English");

    private final String language;

    Locale(String title) {
        this.language = title;
    }

    public String getLanguage() {
        return language;
    }
}