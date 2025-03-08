package org.example;

class VadeliHesap extends BankaHesap {

    private String hesapTuru;
    private double vadeliBakiye;
    private float faizOrani;

    public VadeliHesap(String hesapTuru, double vadeliBakiye, float faizOrani) {
        super(getIban(), getToplamBakiye(), getHesapBilgisi());
        this.hesapTuru = hesapTuru;
        this.vadeliBakiye = vadeliBakiye;
        this.faizOrani = faizOrani;
    }

    //getter setter metodları
    @Override
    public String getHesapTuru() {
        return hesapTuru;
    }

    @Override
    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    public double getVadeliBakiye() {
        return vadeliBakiye;
    }

    public void setVadeliBakiye(double vadeliBakiye) {
        this.vadeliBakiye = vadeliBakiye;
    }

    public float getFaizOrani() {
        return faizOrani;
    }

    public void setFaizOrani(float faizOrani) {
        this.faizOrani = faizOrani;
    }

    public String toString() {
        return "VadeliHesap -> " + "hesapTuru = '" + hesapTuru + ", vadeliBakiye = " + vadeliBakiye +
                ", faizOrani = " + faizOrani + ", iban = TR" + getIban() + ", toplamBakiye = " + getToplamBakiye() + ", hesapBilgisi = " + getHesapBilgisi();
    }

}
