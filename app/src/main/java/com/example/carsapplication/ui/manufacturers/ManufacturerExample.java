package com.example.carsapplication.ui.manufacturers;

public class ManufacturerExample {

    private String title;
    private String country;
    private int imageLogo;

    public ManufacturerExample() {
    }

    public ManufacturerExample(String title, String country, int imageLogo) {
        this.title = title;
        this.country = country;
        this.imageLogo = imageLogo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(int imageLogo) {
        this.imageLogo = imageLogo;
    }
}
