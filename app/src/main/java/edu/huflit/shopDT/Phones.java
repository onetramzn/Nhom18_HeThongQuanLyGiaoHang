package edu.huflit.shopDT;

public class Phones {

    String name;
    String price;
    String de;
    String imgURL;

    public Phones( String name, String price,String de,String imgURL) {
        this.name = name;
        this.price = price;
        this.de = de;
        this.imgURL = imgURL;


    }
    public Phones( ) {}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


}
