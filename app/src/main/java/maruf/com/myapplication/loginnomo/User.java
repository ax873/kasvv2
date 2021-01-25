package maruf.com.myapplication.loginnomo;

public class User {
String id;
        String username;
    String pass;
    String image;
    String rayon;
    String status;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User(String id, String username, String pass, String image, String rayon, String status) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.image = image;
        this.rayon = rayon;
        this.status = status;
    }
}
