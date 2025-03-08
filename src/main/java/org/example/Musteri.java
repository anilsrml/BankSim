package org.example;

import java.util.ArrayList;
import java.util.Scanner;

class Musteri extends Kisi {

    Scanner scan = new Scanner(System.in);

    private int musteriNumarasi;
    private static ArrayList<KrediKarti> krediKartlari = new ArrayList<>();
    private static ArrayList<Musteri> hesaplar = new ArrayList<>();


    String ad;
    String soyad;
    String email;
    int telefonNumarasi;
    String hesapTuru;

    RandomSinifi random = new RandomSinifi();

    BankaHesap bankahesap = new BankaHesap(BankaHesap.generateRandomIBAN(), 0, hesapTuru);

    public Musteri(String ad, String soyad, String email, int telefonNumarasi, int musteriNumarasi) {
        super(ad, soyad, email, telefonNumarasi);
        this.musteriNumarasi = musteriNumarasi;
        this.hesaplar = new ArrayList<Musteri>();
        this.krediKartlari = new ArrayList<>();
    }

    //hesap ekledigimiz metod
    public void hesapEkle(int tcKimlikNo, int musteriNumarasi, String ad, String soyad) {

        //oluşturulan objelere null atanıyor
        Musteri vadeliHesap = null;
        Musteri vadesizHesap = null;
        Musteri yatirimHesap = null;

        //hesap bilgisi normal ise bakiyeyi girmemizi sağlayan şartsal kod
        if (bankahesap.getHesapBilgisi().equals("normal")) {
            //bakiyeyi yani geliri girdigimiz alan
            System.out.print("BAKİYEYİ GİRİN : ");
            bankahesap.setToplamBakiye(scan.nextDouble());
        }

        //kullaniciya hesap turu sorulur
        System.out.println("\t1- vadeli \n\t2- vadesiz  \n\t3- yatırım ");
        System.out.println("---- SEÇMEK İSTEDİĞİNİZ HESAP TÜRÜN NUMARASINI YAZIN ----");
        int secim = scan.nextInt();

        double yatirimBakiye, vadesizBakiye, vadeliBakiye;

        //seçime göre hesap türüne 3 hesap isminden biri atanır
        if (secim == 1) {
            bankahesap.setHesapTuru("vadeli");
            vadeliBakiye = bankahesap.getToplamBakiye();
        } else if (secim == 2) {
            bankahesap.setHesapTuru("vadesiz");
            vadesizBakiye = bankahesap.getToplamBakiye();
        } else if (secim == 3) {
            bankahesap.setHesapTuru("yatirim");
            yatirimBakiye = bankahesap.getToplamBakiye();
        } else {
            System.out.println("Hatalı hesap türü!");
        }
        System.out.println("HESAP TÜRÜ = " + bankahesap.getHesapTuru());
    }

    //hesap silen metod
    public void hesapSil(int tcKimlikNo, int musteriNumarasi, String ad, String soyad) {

        if (bankahesap.getToplamBakiye() == 0) {
            System.out.println("HANGİ HESABI SİLMEK İSTİYORSUNUZ");
            //silmek istenen hesap yazılır (vadeli,vadesiz,yatirim)
            bankahesap.setHesapBilgisi(scan.nextLine());
            System.out.println(" HESABINIZ SİLİNDİ ");
        } else {
            System.out.println("HESABINIZI SİLMEK İÇİN HESAPTAKİ PARAYI BAŞKA BİR HESABA AKTARMALISINIZ ");
        }
    }

    //getter setter metodları
    public int getMusteriNumarasi() {
        return musteriNumarasi;
    }

    public void setMusteriNumarasi(int musteriNumarasi) {
        this.musteriNumarasi = musteriNumarasi;
    }

    public static ArrayList<KrediKarti> getKrediKartlari() {
        return krediKartlari;
    }

    public static void setKrediKartlari(ArrayList<KrediKarti> krediKartlari) {
        Musteri.krediKartlari = krediKartlari;
    }

    public ArrayList<Musteri> getHesaplar() {
        return hesaplar;
    }

    public void setHesaplar(ArrayList<Musteri> hesaplar) {
        this.hesaplar = hesaplar;
    }

    public String toString() {
        return "Musteri -> musteriNumarasi = " + musteriNumarasi + ", krediKartlari = " + krediKartlari + ", hesaplar = " + hesaplar +
                ", telefonNumarasi = " + telefonNumarasi + ", tcKimlikNo = " + getTcKimlikNo() + ", ad = " + getAd() + ", soyad = " + getSoyad() + ", email  = " + getEmail();
    }

}
