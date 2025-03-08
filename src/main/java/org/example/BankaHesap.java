package org.example;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;

class BankaHesap {

    static Scanner scan = new Scanner(System.in);
    static Random random = new Random();

    RandomSinifi randomSinifi = new RandomSinifi();

    private static long iban;
    private Date hesapAcilisTarih;
    private static double toplamBakiye;
    private double islemMiktar;
    private static String hesapBilgisi;
    private String hesapTuru;

    public BankaHesap(long iban, double toplamBakiye, String hesapBilgisi) {
        this.iban = iban;
        this.toplamBakiye = toplamBakiye;
        this.hesapBilgisi = hesapBilgisi;
    }

    //ibanin random sekilde olusturuldugu metod
    //ibanin bankaHesap sinifda olusturulmasi gerektigi yazdigi icin burada olusturuldu
    public static long generateRandomIBAN() {
        long iban = Math.abs(random.nextLong()) % 9999999999999999L;
        return iban;
    }


    //kullanicinn bilgilerinin ekrana yazdirildigi metod
    public void hesapGoruntuleme(int musteriNumarasi, String ad, String soyad, long iban, String hesapBilgisi) {

        System.out.println("musteri numarasi : " + musteriNumarasi);
        System.out.println("ad : " + ad);
        System.out.println("soyad : " + soyad);
        System.out.println("iban : TR" + iban);
        System.out.println("hesap bilgisi : " + hesapBilgisi);
        System.out.println("tc kimlik no : " + randomSinifi.TCOlusturucu());
        System.out.println("kart numarası : " + randomSinifi.KartNumarasiOlusturucu());
        System.out.println("bakiye : " + toplamBakiye);

    }

    //kullanicin girdisine gore hesap bilgisini (maas yada normal)gucelleyen alan
    public void hesapBilgisiGirdisi(String hesapBilgisi) {
        int secim;

        System.out.println(" MAAŞ HESABI İÇİN 1'İ, NORMAL HESAP İÇİN 2'Yİ TUŞLAYIN ");
        secim = scan.nextInt();
        if (secim == 1) {
            this.hesapBilgisi = "maaş";
        } else {
            this.hesapBilgisi = "normal";
        }
    }

    //getter setter metodları
    public static long getIban() {
        return iban;
    }

    public void setIban(long iban) {
        this.iban = iban;
    }

    public static double getToplamBakiye() {
        return toplamBakiye;
    }

    public void setToplamBakiye(double toplamBakiye) {
        this.toplamBakiye = toplamBakiye;
    }

    public double getIslemMiktar() {
        return islemMiktar;
    }

    public void setIslemMiktar(double islemMiktar) {
        this.islemMiktar = islemMiktar;
    }

    public static String getHesapBilgisi() {
        return hesapBilgisi;
    }

    public void setHesapBilgisi(String hesapBilgisi) {
        this.hesapBilgisi = hesapBilgisi;
    }

    public String getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    public Date getHesapAcilisTarih() {
        return hesapAcilisTarih;
    }

    public void setHesapAcilisTarih(Date hesapAcilisTarih) {
        this.hesapAcilisTarih = hesapAcilisTarih;
    }

    public String toString() {
        return "BankaHesap -> " + "iban = TR" + iban + ", hesapAcilisTarih=" + hesapAcilisTarih + ", toplamBakiye = " + toplamBakiye +
                ", islemMiktar = " + islemMiktar + ", hesapBilgisi = '" + hesapBilgisi + ", hesapTuru = '" + hesapTuru;
    }

}
