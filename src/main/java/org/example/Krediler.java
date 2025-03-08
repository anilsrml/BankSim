package org.example;

import java.util.Scanner;

class Krediler {

    Scanner scan = new Scanner(System.in);

    private int krediID;
    private double krediMiktar;
    private double taksitMiktar;
    private int musteriNumarsi;

    public Krediler(int krediID, int musteriNumarsi, double krediMiktar) {
        this.krediID = krediID;
        this.krediMiktar = krediMiktar;
        this.musteriNumarsi = musteriNumarsi;
    }

    //kredi vermek icin olusturdugum bir metod
    public void krediAl(double bakiye, int krediID) {

        System.out.println("Kredi ID'nizi");
        krediID = scan.nextInt();

        int kazancYil = 0;
        kazancYil += bakiye * 12;
        krediMiktar = kazancYil * 0.5;

        System.out.println("VERİLEN KREDİ = " + krediMiktar);
        System.out.print("TAKSİT MİKTARI GİRİNİZ = ");
        int taksit = scan.nextInt();

        taksitMiktar = krediMiktar / taksit;

        System.out.println("KREDİ İÇİN AYLIK ÖDEYECEĞİNİZ TUTAR = " + taksitMiktar);

    }

    public void kampanya(KrediKarti limit) {


    }

    //getter setter metodları
    public int getKrediID() {
        return krediID;
    }

    public void setKrediID(int krediID) {
        this.krediID = krediID;
    }

    public double getKrediMiktar() {
        return krediMiktar;
    }

    public void setKrediMiktar(double krediMiktar) {
        this.krediMiktar = krediMiktar;
    }

    public double getTaksitMiktar() {
        return taksitMiktar;
    }

    public void setTaksitMiktar(double taksitMiktar) {
        this.taksitMiktar = taksitMiktar;
    }

    public int getMusteriNumarsi() {
        return musteriNumarsi;
    }

    public void setMusteriNumarsi(int musteriNumarsi) {
        this.musteriNumarsi = musteriNumarsi;
    }

    public String toString() {
        return "Kredi ID -> " + krediID + " , Kredi miktarı : " + krediMiktar + ", Taksit miktarı : " + taksitMiktar
                + " , Müşteri numarası : " + musteriNumarsi;
    }

}
