
import java.io.File;
import java.util.Scanner;

public class Main {

    private static Kart[][] kartlar = new Kart[4][4]; //kartlarımızı oluşturuyoruz

    public static void KayittanAl() { //kaydedilmiş oyun var mı diye kontrol etmek için bir method yazıyoruz
        File file = new File("Kayıt.bin"); //içinde Kayıt.bin değeri olan bir File referansı oluşturuyoruz
        if (file.exists()) {//ve içindeki değerle eşleşen bir aynı isimde bir dosya olup olmadığını kontrol ediyoruz.
            System.out.print("kaydedilmiş bir oyununuz var kayıttan devam etmek ister misiniz? [E/H]:");//varsa kullanıcıya bu dosyayı yüklemek isteyip istemediğini soruyoruz.
            Scanner scanner = new Scanner(System.in);
            String cevap = scanner.nextLine();
            if (cevap.equals("E")) {
                kartlar = OyunKayit.KaydiAc();//cevap evetse OyunKayit classımızın kaydı aç özelliğiyle kayıtlı dosyamızdaki kartları yukarıda oluşturduğumuz kartlara atıyoruz.
                return;//ve else vermediğimiz için kartları varsayalına aşağıda sıfırlamasın diye return diyip fonksiyonu bitiriyoruz.
            }

        }
        kartlar[0][0] = new Kart('E'); //kullanıcı hayır demişse kartları varsayılan olarak oluşturuyoruz.
        kartlar[0][1] = new Kart('A');
        kartlar[0][2] = new Kart('B');
        kartlar[0][3] = new Kart('F');
        kartlar[1][0] = new Kart('G');
        kartlar[1][1] = new Kart('A');
        kartlar[1][2] = new Kart('D');
        kartlar[1][3] = new Kart('H');
        kartlar[2][0] = new Kart('F');
        kartlar[2][1] = new Kart('C');
        kartlar[2][2] = new Kart('D');
        kartlar[2][3] = new Kart('H');
        kartlar[3][0] = new Kart('E');
        kartlar[3][1] = new Kart('G');
        kartlar[3][2] = new Kart('B');
        kartlar[3][3] = new Kart('C');

    }

    public static void main(String[] args) {

        KayittanAl();//oyun başlangıcında KayittanAl methodumuzu çağırıyoruz ki kayıt var mı yok mu kontrol etsin.
        
        while (oyunBittiMi() == false) {

            oyunTahtasi();

            System.out.println("Çıkmak için -1 yazın!");//her oyun tahtası bastırıldğında kullanıcıya çıkma cevabını hatırlatıyoruz.

            tahminEt();

            if (oyunBittiMi() == true) {
                System.err.println("Tebrikler Oyunu Kazandınız!");
            }

        }
    }

    public static void tahminEt() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Birinci tahmin (tahminlerinizi i boşluk j şeklinde giriniz) :");
        int i1 = scanner.nextInt();

        if (i1 == -1) {//eğer kullanıcı çıkmak isterse
            scanner.nextLine();//kullanıcının enter'a basmasını yakalıyoruz
            System.out.print("Oyunu kaydetmek istiyor musunuz? [E/H]:");
            String kayit = scanner.nextLine();
            if (kayit.equals("E")) {
                OyunKayit.OyunKaydet(kartlar);//eğer kullanıcı kaydetmek isterse OyunKayit içerisindeki Oyunkaydet fonksiyonuna şuanki kartlarımızı yolluyoruz
                System.exit(0);//ve programdan çıkmasını söylüyoruz

            } else {
                System.out.println("GoodBye!");//eğer kaydetmek istemezse 
                System.exit(0);//direkt çıkıyoruz
            }

        }
        
        int j1 = scanner.nextInt();
        kartlar[i1][j1].setTahmin(true);
        oyunTahtasi();

        System.out.print("İkinci tahmin (tahminlerinizi i boşluk j şeklinde giriniz) :");
        int i2 = scanner.nextInt();
        int j2 = scanner.nextInt();
        if (kartlar[i1][j1].getDeger() == kartlar[i2][j2].getDeger()) {
            System.out.println("Doğru tahmin");
            kartlar[i2][j2].setTahmin(true);
        } else {
            System.out.println("Yanlış tahmin");
            kartlar[i1][j1].setTahmin(false);

        }
    }

    public static boolean oyunBittiMi() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin() == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void oyunTahtasi() {
        for (int i = 0; i < 4; i++) {
            System.out.println("____________________");
            for (int j = 0; j < 4; j++) {
                if (kartlar[i][j].isTahmin()) {
                    System.out.print(" |" + kartlar[i][j].getDeger() + "| ");
                } else {
                    System.out.print(" | | ");
                }
            }
            System.out.println("");
        }
        System.out.println("____________________");
    }
}
