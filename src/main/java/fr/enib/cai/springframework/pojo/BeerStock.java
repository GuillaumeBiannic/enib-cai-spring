package fr.enib.cai.springframework.pojo;

public class BeerStock {

  private Beer beer;
  private int stock;

  public BeerStock(Beer beer, Integer stock) {
    this.beer = beer;
    this.stock = stock;
  }

  public void setBeer(Beer beer) {
    this.beer = beer;
  }

  public Beer getBeer() {
    return beer;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getStock() {
    return stock;
  }

}

