package org.example;

import java.util.Scanner;

class KrediKarti {

    String ad;
    String soyad;
    String email;
    int telefonNumarasi;

    Scanner scan = new Scanner(System.in);

    RandomSinifi random = new RandomSinifi();
    Musteri musteri = new Musteri(ad, soyad, email, telefonNumarasi, random.musteriNoOlusturucu());

    private String kartNumarasi;//16 karakter kullanacagim icin int yerine string kullandim
    private double limit;
    private double guncelBorc;

    public KrediKarti(String kartNumarasi, double limit, double guncelBorc, int kullanilabilirLimit) {
        this.kartNumarasi = kartNumarasi;
        this.limit = limit;
        this.guncelBorc = 0;
    }

    //kredi karti ekledigimiz metod
    public void KrediKartiEkle(String kartNumarasi, double limit, KrediKarti krediKarti) {
        musteri.getKrediKartlari().add(krediKarti);
        System.out.println("Kredi kartı eklendi.");
    }

    //kredi karti sildigimiz metod
    public void KrediKartiSil(String kartNumarasi, double limit, double guncelBorc, KrediKarti krediKarti) {
        //borç sorfulanır ,ancak borç yoksa kart silinir
        if (krediKarti.getGuncelBorc() == 0) {
            musteri.getKrediKartlari().remove(krediKarti);
            System.out.println("Kredi kartı silindi.");
        } else {
            System.out.println("Lütfen öncelikle borç ödemesi yapınız.");
        }
    }

    //kullanilabilir limiti geri donduren metod
    public double kullanilabilirLimit(String kartNumarasi, double guncelBorc) {

        System.out.println(" KULLANIALBİLİR LİMİTİ GİRİNİZ ");
        this.limit = scan.nextInt();

        return getLimit();
    }

    //getter setter metodları
    public String getKartNumarasi() {
        return kartNumarasi;
    }

    public void setKartNumarasi(String kartNumarasi) {
        this.kartNumarasi = kartNumarasi;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getGuncelBorc() {
        return guncelBorc;
    }

    public void setGuncelBorc(double guncelBorc) {
        this.guncelBorc = guncelBorc;
    }

    @Override
    public String toString() {
        return "KrediKarti -> " + "kartNumarasi = " + kartNumarasi + ", limit = " + limit + ", guncelBorc = " + guncelBorc;
    }

}
