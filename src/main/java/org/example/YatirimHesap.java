package org.example;

class YatirimHesap extends BankaHesap {

    private String hesapTuru;
    private String yatirimTuru;
    private double yatirimBakiye;
    private float kur;

    public YatirimHesap(String hesapTuru, String yatirimTuru, double yatirimBakiye, float kur) {
        super(getIban(), getToplamBakiye(), getHesapBilgisi());
        this.hesapTuru = hesapTuru;
        this.yatirimTuru = yatirimTuru;
        this.yatirimBakiye = yatirimBakiye;
        this.kur = kur;
    }

    //kur girilir ve yatırım bakiyeye kura oranla bakiye eklenir
    public double paraEkle(double yatirimBakiye, String yatirimTuru, float kur) {
        System.out.println("kuru girin");
        kur = scan.nextFloat();

        return (yatirimBakiye += yatirimBakiye * kur);
    }

    //kur girilir ve yatırım bakiyeden kura oranla bakiye çıkarılır
    public double paraBoz(double yatirimBakiye, String yatirimTuru, float kur) {
        System.out.println("kuru girin");
        kur = scan.nextFloat();

        return (yatirimBakiye -= yatirimBakiye * kur);
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

    public String getYatirimTuru() {
        return yatirimTuru;
    }

    public void setYatirimTuru(String yatirimTuru) {
        this.yatirimTuru = yatirimTuru;
    }

    public double getYatirimBakiye() {
        return yatirimBakiye;
    }

    public void setYatirimBakiye(double yatirimBakiye) {
        this.yatirimBakiye = yatirimBakiye;
    }

    public float getKur() {
        return kur;
    }

    public void setKur(float kur) {
        this.kur = kur;
    }

    @Override
    public String toString() {
        return "Hesap Türü : " + hesapTuru + ", Yatırım Türü : " + yatirimTuru + ", Yatırım Bakiyesi : " + yatirimBakiye + ", Kur : " + kur;
    }

}
