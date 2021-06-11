
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class OyunKayit {
   public static void OyunKaydet(Kart[][] kartlar){//Oyunu kaydetme fonksiyonunu oluşturuyoruz ve oyun içinde bize kartların son halini dönecek bir Kart[][] nesnesi yakalıyoruz
       try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Kayıt.bin"))){//Kayıt.bin adında dosya oluşturmasını söylüyoruz
           System.out.println("Oyun kaydedildi.");
           output.writeObject(kartlar);//dosya içine kartlarımızın son halini tutan kartlar referansımızı yazıyoruz
       } 
       catch (FileNotFoundException ex) {
           System.out.println("Dosya kaydedilemedi");
       } 
       catch (IOException ex) {
           System.out.println("yazma hatası");
       }
   }
   public static Kart[][] KaydiAc(){//kayıt dosyasını açma fonksiyonumuzu açıyoruz.
        try(ObjectInputStream input = new ObjectInputStream(new FileInputStream("Kayıt.bin"))){//varolan bir kayıt dosyası olup olmadığını kontrol edip açıyoruz.
           Kart[][] cikti = (Kart[][])input.readObject();//bir Kart[][] arrayi açıyoruz ve bunun içine okunan kartları yerleştiriyoruz
           return cikti;//oluşturduğumuz cikti referansını programa dönmesnini söylüyoruz.
       } 
       catch (FileNotFoundException ex) {
           System.out.println("Dosya kaydedilemedi");
       } 
       catch (IOException ex) {
           System.out.println("yazma hatası");
       } catch (ClassNotFoundException ex) {
            System.out.println("Class bulunmadı");
       }
        return null;//olası bir hata durumunda boş dönüyoruz.
   }
}

