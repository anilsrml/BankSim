package org.example;

import java.util.ArrayList;

class BankaPersonel extends Kisi {

    private int personelID;
    private ArrayList<Musteri> musteriler = new ArrayList<Musteri>();
    private int telefonNumarasi;

    public BankaPersonel(int tcKimlikNo, String ad, String soyad, String email, int telefonNumarasi) {
        super(ad, soyad, email, tcKimlikNo);
        this.telefonNumarasi = telefonNumarasi;
    }

    //değişkenlerin getter setter metosları
    public int getPersonelID() {
        return personelID;
    }

    public void setPersonelID(int personelID) {
        this.personelID = personelID;
    }

    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    public void setMusteriler(ArrayList<Musteri> musteriler) {
        this.musteriler = musteriler;
    }

    @Override
    public int getTelefonNumarasi() {
        return telefonNumarasi;
    }

    @Override
    public void setTelefonNumarasi(int telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public String toString() {
        return "BankaPersonel -> personelID = " + personelID + ", musteriler = " + musteriler
                + ", telefonNumarasi = " + telefonNumarasi + ", tcKimlikNo = " + getTcKimlikNo() + ", ad = " + getAd() + ", soyad = " + getSoyad() + ", email = " + getEmail();
    }
}
