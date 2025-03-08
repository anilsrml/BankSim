package org.example;

class VadesizHesap extends BankaHesap {

    private String hesapTuru;
    private double vadesizBakiye;
    int kesinti;

    public VadesizHesap(String hesapTuru, double vadesizBakiye) {
        super(getIban(), getToplamBakiye(), getHesapBilgisi());
        this.hesapTuru = hesapTuru;
        this.vadesizBakiye = vadesizBakiye;
    }

    //para transferi saglanan metod
    public double paraTransfer(String hesapTuru, long iban, double vadesizBakiye, int islemiktari) {

        vadesizBakiye += islemiktari;
        vadesizBakiye -= kesinti;

        return vadesizBakiye;
    }

    //kredi karti borcunun odenmesi icin kullanilan metod
    public double krediKartBorcOdeme(double vadesizBakiye, KrediKarti guncelBorc) {

        KrediKarti krediKarti = null;
        vadesizBakiye -= krediKarti.getGuncelBorc();

        return vadesizBakiye;
    }

    public double krediOdeme(int krediID, Krediler krediMiktari, Krediler taksitMiktari) {


        return 4;
    }

    //getter seetter metodları
    @Override
    public String getHesapTuru() {
        return hesapTuru;
    }

    @Override
    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    public double getVadesizBakiye() {
        return vadesizBakiye;
    }

    public void setVadesizBakiye(double vadesizBakiye) {
        this.vadesizBakiye = vadesizBakiye;
    }

    public String toString() {
        return "VadesizHesap -> " + "hesapTuru = " + hesapTuru + ", vadesizBakiye = " + vadesizBakiye + ", kesinti = " + kesinti;
    }

}
