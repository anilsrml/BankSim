package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        //kisisel bilgileri aldigimiz alan
        System.out.println("BİLGİLERİNİZİ GİRİN ");

        System.out.print("ADINIZI GİRİN : ");
        String ad = scan.nextLine();
        System.out.print("SOYADINIZI GİRİN : ");
        String soyad = scan.nextLine();
        System.out.print("EMAİLNİZİ GİRİN: ");
        String email = scan.nextLine();
        System.out.print("TELEFON NUMARANIZI GİRİN: ");
        long telefonNumarasi = scan.nextLong();

        // rastgele olusturulan musteri numarasini atiyoruz
        System.out.printf("MÜŞTERI NUMARANIZ OLUSTURULDU => %s \n", RandomSinifi.musteriNoOlusturucu());
        int musteriNumarasi = RandomSinifi.musteriNoOlusturucu();

        System.out.println(" MERHABA "+ad+" HOŞGELDİN");
        Musteri musteri = new Musteri (ad, soyad, email, (int) telefonNumarasi,musteriNumarasi);
        RandomSinifi random = new RandomSinifi();

        //Metod ile oluşturduğumuz ibanı atıyoruz
        long iban = BankaHesap.generateRandomIBAN();
        double toplamBakiye=0.0;

        BankaHesap bankahesap = new BankaHesap(iban,toplamBakiye, "aaa");

        int kesinti = 0;
        float faizOrani= 0.0F ;
        double vadeliBakiye= 0.0;
        double vadesizBakiye = 0.0;
        double yatirimBakiye =0.0;
        String yatirimTuru = null;
        float kur = 0;

        int limit = 0;
        int kullanilabilirLimit= 0;
        int guncelBorc =0;
        int krediID = 0;
        double krediMiktar=0;

        VadeliHesap vadeliHesap = new VadeliHesap("",vadeliBakiye,0);
        VadesizHesap vadesizHesap = new VadesizHesap("",vadesizBakiye);
        YatirimHesap yatirimHesap = new YatirimHesap("","",yatirimBakiye,0);

        //maas mi yoksa normal hesap mi sorgulanan yer
        bankahesap.hesapBilgisiGirdisi(bankahesap.getHesapBilgisi());

        int secim;

        Musteri vadeli=null;
        Musteri vadesiz=null;
        Musteri yatirim=null;

        boolean devamEt = true;
        //maaş veya normal hesaba girilir
        if(bankahesap.getHesapBilgisi().equals("maaş")){

            System.out.println("MAAŞINIZI GİRİN");
            double maas =scan.nextDouble();

            //kisi maas hesabini sectiginde kesinti ve faiz degerlerini olmasi gerektigi degere sabitliyoruz
            vadesizHesap.kesinti=0;
            vadeliHesap.setFaizOrani(0.2F);

            System.out.println(" AŞAĞIDAKİ SEÇENEKLERN-DEN YAPMAK İSTEDİĞİNİZİ SEÇİN ?");

            while(devamEt) {
                System.out.println("1- HESAP EKLE  \n2- HESAP SİL \n3- KREDİ İŞLEMLERİ \n4- KİŞİSEL BİLGİLERİ GÖRÜNTÜLE \n5- ÇIKIŞ");

                //disardan girdi alarak uygulamanın nereye dallanacağını girdiye bırakıyoruz
                secim = scan.nextInt();
                switch(secim){
                    case 1:
                        //hesap ekleme metodunu çağırıyoruz
                        musteri.hesapEkle(RandomSinifi.TCOlusturucu(),RandomSinifi.musteriNoOlusturucu(),ad,soyad);

                        //burada hesap türü kontrol ediliyor.Hesap türüne göre bakiye o hesap türünün bakiyesine atanıyor
                        if (bankahesap.getHesapTuru() != null && bankahesap.getHesapTuru().equals("vadeli")) {
                            //hesaplar arraylistine vadeli isminde ekleme yapılıyor
                            musteri.getHesaplar().add(vadeli);
                            vadeliBakiye=toplamBakiye;
                        } else if (bankahesap.getHesapTuru() != null && bankahesap.getHesapTuru().equals("vadesiz")) {
                            vadesizBakiye=toplamBakiye;
                            //hesaplar arraylistine vadesiz isminde ekleme yapılıyor
                            musteri.getHesaplar().add(vadesiz);

                        }else {
                            //hesaplar arraylistine yatırım isminde ekleme yapılıyor
                            musteri.getHesaplar().add(yatirim);

                            //yatırım hesabında yapmak istesiğimiz işlemi seçiyoruz
                            System.out.println("HANGİSİ İLE İŞLEM YAPMAK İSTERSİNİZ");
                            String maden = scan.nextLine();
                            if (maden.equals("altın"))
                                yatirimTuru = "altın";
                            else
                                yatirimTuru = "dolar";

                            yatirimBakiye=toplamBakiye;
                            //para ekle veya para boz seçimi isteniyor ona göre metodlar çağırılıyor
                            System.out.println("1- PARA EKLE \n2- PARA BOZ ");
                            int karar= scan.nextInt();
                            if(karar==1){
                                yatirimHesap.paraEkle(yatirimBakiye,yatirimTuru,kur);
                            }else {
                                yatirimHesap.paraBoz(yatirimBakiye,yatirimTuru,kur);
                            }
                        }
                        break;


                    case 2:
                        //hesap sil metodu çağırılıyor
                        musteri.hesapSil(RandomSinifi.TCOlusturucu(),RandomSinifi.musteriNoOlusturucu(),ad,soyad);
                        //seçilen hesaba göre o hesabı silen bölüm
                        musteri.getHesaplar().remove(bankahesap.getHesapBilgisi());
                        break;

                    case 3:
                        //kredi islemleri bolumunde kredi karti , krediler ve kampanya islemlerini getiriyoruz
                        boolean devam = false;
                        do{
                            //kredi islemleri bolumunde kredi karti , krediler ve kampanya islemlerini getiriyoruz
                            System.out.println("1- KREDİ KARTI İŞLEMLERİ  \n 2- KREDİ VE KAMPANYALAR ");
                            int sayi1 = scan.nextInt();
                            if (sayi1 == 1) {

                                //kredi karti bolmesinde kredi karti ekle veye sil metodlarini kullaniciya kullandiriyoruz
                                KrediKarti krediKarti = new KrediKarti(random.KartNumarasiOlusturucu(), limit, guncelBorc, kullanilabilirLimit);
                                System.out.println("1- KREDİ KARTI EKLE  \n2- KREDİ KARTI SİL \n3- KULLANILABİLİR LİMİT\n4- GERİ");
                                int sayi2 = scan.nextInt();
                                if (sayi2 == 1) {
                                    krediKarti.KrediKartiEkle(random.KartNumarasiOlusturucu(), limit,krediKarti);
                                    break;

                                } else if(sayi2==2) {
                                    krediKarti.KrediKartiSil(random.KartNumarasiOlusturucu(), limit, guncelBorc,krediKarti);
                                    break;

                                } else if (sayi2==3){
                                    System.out.println("Kullanılabilir Limit : "+krediKarti.kullanilabilirLimit(random.KartNumarasiOlusturucu(),guncelBorc));
                                    break;

                                }else {
                                    devam=true;
                                }
                            } else {
                                Krediler kredi = new Krediler(krediID, random.musteriNoOlusturucu(), krediMiktar);

                                //kredi alimi veya kampanyalara goz atmak amaciyla kullaniya secim yaptiriyoruz
                                System.out.println("KREDİ ALMAK İÇİN 1'İ, KAMPANYAYA GÖZ ATMAK İÇİN 2'YE BASINIZ");
                                int sayi3 = scan.nextInt();
                                if (sayi3 == 1) {
                                    kredi.krediAl(bankahesap.getToplamBakiye(), krediID);
                                    break;

                                } else {
                                    //kredi.kampanya();
                                    break;

                                }
                            }
                        }while(devam);
                        break;

                    case 4:
                        System.out.println(" KİŞİSEL BİLGİLERİ GÖRÜNTÜLE ");
                        bankahesap.hesapGoruntuleme(musteriNumarasi,ad,soyad,iban, bankahesap.getHesapBilgisi());
                        break;

                    default:

                }
            }
        }
        else{
            while(devamEt) {

                //bakiyeyi yani geliri girdigimiz alan
                System.out.print("BAKİYEYİ GİRİN : ");
                bankahesap.setToplamBakiye(scan.nextDouble());

                //kisi maas hesabini sectiginde kesinti ve faiz degerlerini olmasi gerektigi degere sabitliyoruz
                vadesizHesap.kesinti = 0;
                vadeliHesap.setFaizOrani(0.2F);

                System.out.println(" AŞAĞIDAKİ SEÇENEKLERDEN YAPMAK İSTEDİĞİNİZİ SEÇİN ?");
                System.out.println(" 1- HESAP EKLE  \n 2- HESAP SİL \n 3- KREDİ İŞLEMLERİ \n4- KİŞİSEL BİLGİLERİ GÖRÜNTÜLE");

                //disardan girdi alarak uygulamanin nereye dallanacagini girdiye birakiyoruz
                secim = scan.nextInt();
                switch (secim) {
                    case 1:
                        //hesap ekleme metodunu çağırıyoruz
                        musteri.hesapEkle(RandomSinifi.TCOlusturucu(), RandomSinifi.musteriNoOlusturucu(), ad, soyad);

                        //burada hesap türü kontrol ediliyor.Hesap türüne göre bakiye o hesap türünün bakiyesine atanıyor
                        if (bankahesap.getHesapTuru().equals("vadeli")) {
                            //hesaplar arraylistine vadeli isminde ekleme yapılıyor
                            musteri.getHesaplar().add(vadeli);
                            vadeliBakiye = toplamBakiye;
                        } else if (bankahesap.getHesapTuru().equals("vadesiz")) {
                            vadesizBakiye = toplamBakiye;
                            //hesaplar arraylistine vadesiz isminde ekleme yapılıyor
                            musteri.getHesaplar().add(vadesiz);

                        } else {
                            //yatırım hesabında yapmak istesiğimiz işlemi seçiyoruz
                            System.out.println("HANGİSİ İLE İŞLEM YAPMAK İSTERSİNİZ");
                            String maden = scan.nextLine();
                            //hesaplar arraylistine yatırım isminde ekleme yapılıyor
                            musteri.getHesaplar().add(yatirim);
                            if (maden.equals("altın"))
                                yatirimTuru = "altın";
                            else
                                yatirimTuru = "dolar";

                            yatirimBakiye = toplamBakiye;
                            //para ekle veya para boz seçimi isteniyor ona göre metodlar çağırılıyor
                            System.out.println("1- PARA EKLE \n2- PARA BOZ ");
                            int karar = scan.nextInt();
                            if (karar == 1) {
                                yatirimHesap.paraEkle(yatirimBakiye, yatirimTuru, kur);
                            } else {
                                yatirimHesap.paraBoz(yatirimBakiye, yatirimTuru, kur);
                            }
                        }
                        break;

                    case 2:
                        //hesap sil metodu çağırılıyor
                        musteri.hesapSil(RandomSinifi.TCOlusturucu(), RandomSinifi.musteriNoOlusturucu(), ad, soyad);
                        break;

                    case 3:
                        //kredi islemleri bolumunde kredi karti , krediler ve kampanya islemlerini getiriyoruz
                        boolean devam = false;
                        do {
                            //do while ile geriye dönme şansı tanıyoruz
                            //kredi islemleri bolumunde kredi karti , krediler ve kampanya islemlerini getiriyoruz
                            System.out.println("1- KREDİ KARTI İŞLEMLERİ  \n2- KREDİ VE KAMPANYALAR ");
                            int sayi1 = scan.nextInt();
                            if (sayi1 == 1) {

                                //kredi karti bolmesinde kredi karti ekle veye sil metodlarini kullaniciya kullandiriyoruz
                                KrediKarti krediKarti = new KrediKarti(random.KartNumarasiOlusturucu(), limit, guncelBorc, kullanilabilirLimit);
                                System.out.println("1- KREDİ KARTI EKLE  \n2- KREDİ KARTI SİL \n3- KULLANILABİLİR LİMİT\n4- GERİ");
                                int sayi2 = scan.nextInt();
                                if (sayi2 == 1) {
                                    krediKarti.KrediKartiEkle(random.KartNumarasiOlusturucu(), limit, krediKarti);
                                    break;

                                } else if (sayi2 == 2) {
                                    krediKarti.KrediKartiSil(random.KartNumarasiOlusturucu(), limit, guncelBorc, krediKarti);
                                    break;

                                } else if (sayi2 == 3) {
                                    System.out.println("Kullanılabilir Limit : " + krediKarti.kullanilabilirLimit(random.KartNumarasiOlusturucu(), guncelBorc));
                                    break;

                                } else {
                                    devam = true;
                                }
                            } else {
                                Krediler kredi = new Krediler(krediID, random.musteriNoOlusturucu(), krediMiktar);

                                //kredi alimi veya kampanyalara goz atmak amaciyla kullaniya secim yaptiriyoruz
                                System.out.println("KREDİ ALMAK İÇİN 1'E, KAMPANYAYA GÖZ ATMAK İÇİN 2'YE BASINIZ");
                                int sayi3 = scan.nextInt();
                                if (sayi3 == 1) {
                                    kredi.krediAl(bankahesap.getToplamBakiye(), krediID);
                                    break;

                                } else if (sayi3 == 2) {
                                    //kredi.kampanya();
                                    break;

                                } else {
                                    devam = true;
                                }
                            }
                        } while (devam);
                        break;

                    case 4:
                        System.out.println(" KİŞİSEL BİLGİLERİ GÖRÜNTÜLE ");
                        bankahesap.hesapGoruntuleme(musteriNumarasi, ad, soyad, iban, bankahesap.getHesapBilgisi());
                        break;

                    default:

                }

            }
        }
    }

}

