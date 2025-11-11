package Model;

import javafx.scene.paint.Color;

public class MoodEntry {
    private String tanggal;
    private String mood;
    private String warna;
    private String pemicu;

    public MoodEntry(String tanggal, String mood, String warna, String pemicu) {
        this.tanggal = tanggal;
        this.mood = mood;
        this.warna = warna;
        this.pemicu = pemicu;
    }

    // Getter & Setter
    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getPemicu() {
        return pemicu;
    }

    public void setPemicu(String pemicu) {
        this.pemicu = pemicu;
    }
}
