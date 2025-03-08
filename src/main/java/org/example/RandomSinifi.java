package org.example;

import java.util.Random;

class RandomSinifi {
    static Random random = new Random();

    //random sekilde tc kimlik no olusturan metod
    static int TCOlusturucu() {

        String tcKimlikNo = "";

        // İlk rakam 0 olamaz, diğer rakamlar 0-9 arasında olabilir
        tcKimlikNo += (random.nextInt(9) + 1); // İlk rakam
        for (int i = 1; i <= 6; i++) {
            int rakam = random.nextInt(10);
            tcKimlikNo += rakam;
        }

        return Integer.parseInt(tcKimlikNo);
    }

    //random sekilde kart numarasi olusturan metod
    public static String KartNumarasiOlusturucu() {

        String kartNo = "";

        final int KART_NO_UZUNLUGU = 16;
        for (int i = 0; i < KART_NO_UZUNLUGU; i++) {
            int rakam = random.nextInt(10);
            kartNo += rakam;
        }
        return kartNo;
    }

    //random sekilde musteri numarasi olusturan metod
    public static int musteriNoOlusturucu() {

        String musteriNo = "";

        final int MUSTERI_NO_UZUNLUGU = 8;
        for (int i = 0; i < MUSTERI_NO_UZUNLUGU; i++) {
            int rakam = random.nextInt(10);
            musteriNo += rakam;
        }

        return Integer.parseInt(musteriNo);
    }

    //random sekilde personel numarrasi olusturan metod
    public static void PersonelIDOlusturucu() {

        int randomNum = (int) (Math.random() * 900000) + 100000;
        System.out.println("PErsonel ID: " + randomNum);

    }

}
