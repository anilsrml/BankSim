import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class main {
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
        int telefonNumarasi = scan.nextInt();

        // rastgele olusturulan musteri numarasini atiyoruz
        System.out.printf("MÜŞTERI NUMARANIZ OLUSTURULDU => %s \n", RandomSinifi.musteriNoOlusturucu());
        int musteriNumarasi = RandomSinifi.musteriNoOlusturucu();

        System.out.println(" MERHABA "+ad+" HOŞGELDİN");
        Musteri musteri = new Musteri (ad, soyad, email, telefonNumarasi,musteriNumarasi);
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

        //maaş veya normal hesaba girilir
        if(bankahesap.getHesapBilgisi().equals("maaş")){

            System.out.println("MAAŞINIZI GİRİN");
            double maas =scan.nextDouble();

            //kisi maas hesabini sectiginde kesinti ve faiz degerlerini olmasi gerektigi degere sabitliyoruz
            vadesizHesap.kesinti=0;
            vadeliHesap.setFaizOrani(0.2F);

            System.out.println(" AŞAĞIDAKİ SEÇENEKLERN-DEN YAPMAK İSTEDİĞİNİZİ SEÇİN ?");
            System.out.println("1- HESAP EKLE  \n2- HESAP SİL \n3- KREDİ İŞLEMLERİ \n4- KİŞİSEL BİLGİLERİ GÖRÜNTÜLE");

            //disardan girdi alarak uygulamanin nereye dallanacagini girdiye birakiyoruz
            secim= scan.nextInt();
            switch(secim){
                case 1:
                    //hesap ekleme metodunu çağırıyoruz
                    musteri.hesapEkle(RandomSinifi.TCOlusturucu(),RandomSinifi.musteriNoOlusturucu(),ad,soyad);

                    //burada hesap türü kontrol ediliyor.Hesap türüne göre bakiye o hesap türünün bakiyesine atanıyor
                    if(bankahesap.getHesapTuru().equals("vadeli")){
                        //hesaplar arraylistine vadeli isminde ekleme yapılıyor
                        musteri.getHesaplar().add(vadeli);
                        vadeliBakiye=toplamBakiye;
                    } else if (bankahesap.getHesapTuru().equals("vadesiz")) {
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
        else {

            //bakiyeyi yani geliri girdigimiz alan
            System.out.print("BAKİYEYİ GİRİN : ");
            bankahesap.setToplamBakiye(scan.nextDouble());

            //kisi maas hesabini sectiginde kesinti ve faiz degerlerini olmasi gerektigi degere sabitliyoruz
            vadesizHesap.kesinti=0;
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
                    if(bankahesap.getHesapTuru().equals("vadeli")){
                        //hesaplar arraylistine vadeli isminde ekleme yapılıyor
                        musteri.getHesaplar().add(vadeli);
                        vadeliBakiye=toplamBakiye;
                    } else if (bankahesap.getHesapTuru().equals("vadesiz")) {
                        vadesizBakiye=toplamBakiye;
                        //hesaplar arraylistine vadesiz isminde ekleme yapılıyor
                        musteri.getHesaplar().add(vadesiz);

                    }else {
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
                    do{
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
                            System.out.println("KREDİ ALMAK İÇİN 1'E, KAMPANYAYA GÖZ ATMAK İÇİN 2'YE BASINIZ");
                            int sayi3 = scan.nextInt();
                            if (sayi3 == 1) {
                                kredi.krediAl(bankahesap.getToplamBakiye(), krediID);
                                break;

                            } else if(sayi3==2){
                                //kredi.kampanya();
                                break;

                            }else {
                                devam=true;
                            }
                        }
                    }while(devam);
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

class Kisi {

    private int tcKimlikNo;
    private String ad;
    private String soyad;
    private String email;
    private int telefonNumarasi;

    public Kisi(String ad, String soyad, String email,int telefonNumarasi ) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
    }

    //değişkenlerin getter setter metosları
    public int getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(int tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(int telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public String toString() {
        return "Kisi -> ad = " + ad + ", soyad = " + soyad + ", email = " + email + ", telefonNumarasi = " + telefonNumarasi ;
    }

}

class BankaPersonel extends Kisi{

    private int personelID;
    private ArrayList<Musteri> musteriler = new ArrayList<Musteri>();
    private int telefonNumarasi;

    public BankaPersonel(int tcKimlikNo,String ad, String soyad, String email,int telefonNumarasi) {
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
                + ", telefonNumarasi = " + telefonNumarasi + ", tcKimlikNo = " + getTcKimlikNo() + ", ad = " + getAd() + ", soyad = " + getSoyad() + ", email = " + getEmail() ;
    }
}

class Musteri extends Kisi {

    Scanner scan = new Scanner(System.in);

    private int musteriNumarasi;
    private static ArrayList<KrediKarti> krediKartlari =new ArrayList<>();
    private static ArrayList<Musteri> hesaplar = new ArrayList<>();


    String ad ;
    String soyad;
    String email;
    int telefonNumarasi;
    String hesapTuru;

    RandomSinifi random = new RandomSinifi();

    BankaHesap bankahesap = new BankaHesap(BankaHesap.generateRandomIBAN(),0, hesapTuru);

    public Musteri(String ad, String soyad, String email, int telefonNumarasi, int musteriNumarasi) {
        super(ad, soyad, email, telefonNumarasi);
        this.musteriNumarasi = musteriNumarasi;
        this.hesaplar = new ArrayList<Musteri>();
        this.krediKartlari = new ArrayList<>();
    }

    //hesap ekledigimiz metod
    public void hesapEkle(int tcKimlikNo, int musteriNumarasi, String ad, String soyad) {

        //oluşturulan objelere null atanıyor
        Musteri vadeliHesap=null;
        Musteri vadesizHesap=null;
        Musteri yatirimHesap=null;

        //hesap bilgisi normal ise bakiyeyi girmemizi sağlayan şartsal kod
        if(bankahesap.getHesapBilgisi().equals("normal")) {
            //bakiyeyi yani geliri girdigimiz alan
            System.out.print("BAKİYEYİ GİRİN : ");
            bankahesap.setToplamBakiye(scan.nextDouble());
        }

        //kullaniciya hesap turu sorulur
        System.out.println("\t1- vadeli \n\t2- vadesiz  \n\t3- yatırım ");
        System.out.println("---- SEÇMEK İSTEDİĞİNİZ HESAP TÜRÜN NUMARASINI YAZIN ----");
        int secim = scan.nextInt();

        double yatirimBakiye,vadesizBakiye,vadeliBakiye;

        //seçime göre hesap türüne 3 hesap isminden biri atanır
        if (secim==1) {
            bankahesap.setHesapTuru("vadeli");
            vadeliBakiye= bankahesap.getToplamBakiye();
        } else if (secim==2) {
            bankahesap.setHesapTuru("vadesiz");
            vadesizBakiye= bankahesap.getToplamBakiye();
        } else if (secim==3) {
            bankahesap.setHesapTuru("yatirim");
            yatirimBakiye= bankahesap.getToplamBakiye();
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
                ", telefonNumarasi = " + telefonNumarasi + ", tcKimlikNo = " + getTcKimlikNo() + ", ad = " + getAd() + ", soyad = " + getSoyad() + ", email  = " + getEmail() ;
    }

}

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
        System.out.println("bakiye : "+ toplamBakiye);

    }

    //kullanicin girdisine gore hesap bilgisini (maas yada normal)gucelleyen alan
    public void hesapBilgisiGirdisi(String hesapBilgisi){
        int secim ;

        System.out.println(" MAAŞ HESABI İÇİN 1'İ, NORMAL HESAP İÇİN 2'Yİ TUŞLAYIN ");
        secim = scan.nextInt();
        if (secim==1){
            this.hesapBilgisi="maaş";
        }
        else{
            this.hesapBilgisi="normal";
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
                ", islemMiktar = " + islemMiktar + ", hesapBilgisi = '" + hesapBilgisi + ", hesapTuru = '" + hesapTuru ;
    }

}

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
        return "VadeliHesap -> " + "hesapTuru = '" + hesapTuru  + ", vadeliBakiye = " + vadeliBakiye +
        ", faizOrani = " + faizOrani + ", iban = TR" + getIban() + ", toplamBakiye = " + getToplamBakiye() + ", hesapBilgisi = " + getHesapBilgisi() ;
    }

}

class VadesizHesap extends BankaHesap{

    private String hesapTuru;
    private double vadesizBakiye;
    int kesinti;

    public VadesizHesap(String hesapTuru, double vadesizBakiye) {
        super(getIban(), getToplamBakiye(), getHesapBilgisi());
        this.hesapTuru = hesapTuru;
        this.vadesizBakiye = vadesizBakiye;
    }

    //para transferi saglanan metod
    public double paraTransfer(String hesapTuru,long iban,double vadesizBakiye,int islemiktari){

        vadesizBakiye+=islemiktari;
        vadesizBakiye-=kesinti;

        return vadesizBakiye;
    }

    //kredi karti borcunun odenmesi icin kullanilan metod
    public double krediKartBorcOdeme(double vadesizBakiye,KrediKarti guncelBorc){

            KrediKarti krediKarti = null;
            vadesizBakiye -= krediKarti.getGuncelBorc();

            return vadesizBakiye;
        }

    public double krediOdeme(int krediID,Krediler krediMiktari,Krediler taksitMiktari){


        return 4 ;
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
        return "VadesizHesap -> " + "hesapTuru = " + hesapTuru  + ", vadesizBakiye = " + vadesizBakiye + ", kesinti = " + kesinti;
    }

}

class YatirimHesap extends BankaHesap{

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
    public double paraEkle (double yatirimBakiye,String yatirimTuru,float kur){
        System.out.println("kuru girin");
        kur= scan.nextFloat();

        return (yatirimBakiye+=yatirimBakiye*kur);
    }

    //kur girilir ve yatırım bakiyeden kura oranla bakiye çıkarılır
    public double paraBoz(double yatirimBakiye,String yatirimTuru,float kur){
        System.out.println("kuru girin");
        kur= scan.nextFloat();

        return (yatirimBakiye-=yatirimBakiye*kur);
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

class KrediKarti {

    String ad ;
    String soyad;
    String email;
    int telefonNumarasi;

    Scanner scan = new Scanner(System.in);

    RandomSinifi random = new RandomSinifi();
    Musteri musteri = new Musteri(ad,soyad,email,telefonNumarasi,random.musteriNoOlusturucu());

    private String kartNumarasi;//16 karakter kullanacagim icin int yerine string kullandim
    private double limit;
    private double guncelBorc;

    public KrediKarti(String kartNumarasi, double limit, double guncelBorc, int kullanilabilirLimit) {
        this.kartNumarasi = kartNumarasi;
        this.limit = limit;
        this.guncelBorc = 0;
    }

    //kredi karti ekledigimiz metod
    public void KrediKartiEkle(String kartNumarasi, double limit,KrediKarti krediKarti){
        musteri.getKrediKartlari().add(krediKarti);
        System.out.println("Kredi kartı eklendi.");
    }

    //kredi karti sildigimiz metod
    public void KrediKartiSil(String kartNumarasi, double limit,double guncelBorc,KrediKarti krediKarti){
        //borç sorfulanır ,ancak borç yoksa kart silinir
        if (krediKarti.getGuncelBorc()== 0) {
            musteri.getKrediKartlari().remove(krediKarti);
            System.out.println("Kredi kartı silindi.");
        } else {
            System.out.println("Lütfen öncelikle borç ödemesi yapınız.");
        }
    }

    //kullanilabilir limiti geri donduren metod
    public double kullanilabilirLimit(String kartNumarasi,double guncelBorc) {

        System.out.println(" KULLANIALBİLİR LİMİTİ GİRİNİZ ");
        this.limit=scan.nextInt();

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
        return "KrediKarti -> " + "kartNumarasi = " + kartNumarasi + ", limit = " + limit + ", guncelBorc = " + guncelBorc ;
    }

}

class Krediler {

    Scanner scan = new Scanner(System.in);

    private int krediID;
    private double krediMiktar;
    private double taksitMiktar;
    private int musteriNumarsi;

    public Krediler(int krediID, int musteriNumarsi ,double krediMiktar) {
        this.krediID = krediID;
        this.krediMiktar = krediMiktar;
        this.musteriNumarsi=musteriNumarsi;
    }

    //kredi vermek icin olusturdugum bir metod
    public void krediAl(double bakiye,int krediID){

        System.out.println("Kredi ID'nizi");
        krediID = scan.nextInt();

        int kazancYil=0;
        kazancYil+=bakiye*12;
        krediMiktar=kazancYil*0.5;

        System.out.println("VERİLEN KREDİ = "+ krediMiktar);
        System.out.print("TAKSİT MİKTARI GİRİNİZ = ");
        int taksit = scan.nextInt();

        taksitMiktar=krediMiktar/taksit;

        System.out.println("KREDİ İÇİN AYLIK ÖDEYECEĞİNİZ TUTAR = "+taksitMiktar );

    }
    public void kampanya(KrediKarti limit){


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

class RandomSinifi {
    static Random random = new Random();

    //random sekilde tc kimlik no olusturan metod
    static int TCOlusturucu (){

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
    public static String KartNumarasiOlusturucu () {

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