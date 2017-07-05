package com.jp.solution.model;

import java.io.Serializable;

/**
 * Class to denote a sale type.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class Sale implements Serializable {

  /**
   * The product type for the sale.
   */
  private String productType;

  /**
   * the value for the product
   */
  private double productValue;

  /**
   * Constructor.
   * 
   * @param productType the product type for the sale.
   * @param productValue the product value for the sale.
   */
  public Sale(String productType, double productValue) {
    this.productType = productType;
    this.productValue = productValue;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public double getProductValue() {
    return productValue;
  }

  public void setProductValue(double productValue) {
    this.productValue = productValue;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null || getClass() != object.getClass())
      return false;

    Sale sale = (Sale) object;

    if (Double.compare(sale.getProductValue(), getProductValue()) != 0) {
      return false;
    }
    return getProductType() != null ? getProductType().equals(sale.getProductType()) : sale.getProductType() == null;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = getProductType() != null ? getProductType().hashCode() : 0;
    temp = Double.doubleToLongBits(getProductValue());
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Sale{");
    sb.append("productType='").append(productType).append('\'');
    sb.append(", productValue=").append(productValue);
    sb.append('}');
    return sb.toString();
  }
}
