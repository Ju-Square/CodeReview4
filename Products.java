// Julia Hinterecker
package com.company;

public class Products {
    String productName;
    String productSize;
    double productOldPrice;
    double productActionPrice;
    String productImage;
    String productDescription;
    public Products(String productName, String productSize, double productOldPrice, double productActionPrice, String productImage, String productDescription){
        this.productName = productName;
        this.productSize = productSize;
        this.productOldPrice = productOldPrice;
        this.productActionPrice = productActionPrice;
        this.productImage = productImage;
        this.productDescription = productDescription;
    }

    // Setter
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
    public void setProductOldPrice(double productOldPrice) {
        this.productOldPrice = productOldPrice;
    }
    public void setProductActionPrice(double productActionPrice) {
        this.productActionPrice = productActionPrice;
    }
    public void setImage(String image) {
        this.productImage = image;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    // Getter
    public String getProductName() {
        return productName;
    }
    public String getProductSize() {
        return productSize;
    }
    public double getProductOldPrice() { return productOldPrice; }
    public double getProductActionPrice() { return productActionPrice; }
    public String getImage() { return productImage; }
    public String getProductDescription() { return productDescription; }

    // ToString to combine the Name, oldPrice, actionPrice for the User to choose
    @Override
    public String toString(){
        return '{' + productName + ", old=" + productOldPrice + ", new=" + productActionPrice + '}';
    }
}
