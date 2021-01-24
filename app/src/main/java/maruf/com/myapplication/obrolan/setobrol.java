package maruf.com.myapplication.obrolan;

public class setobrol {

    String nama,gambar,pesan;

    public setobrol() {
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public setobrol(String nama, String gambar, String pesan) {
        this.nama = nama;
        this.gambar = gambar;
        this.pesan = pesan;
    }
}
