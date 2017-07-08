package com.jp.solution.model.message;

import com.jp.solution.common.AppConstants;
import com.jp.solution.model.Sale;

/**
 * Contains the details of a sale and the number of occurrences of
 * that sale. E.g 20 sales of apples at 10p each.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class MessageType2 extends AbstractMessageType {
  /**
   * Number of occurrences of the sale.
   */
  private int numOccurances;

  public MessageType2(Sale sale, int numOccurances) {
    super(AppConstants.MESSAGE_TYPE_2);
    setSale(sale);
    setNumOccurances(numOccurances);
  }

  public int getNumOccurances() {
    return numOccurances;
  }

  public void setNumOccurances(int numOccurances) {
    this.numOccurances = numOccurances;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    MessageType2 that = (MessageType2) o;

    return super.equals(o) && getNumOccurances() == that.getNumOccurances();
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + getNumOccurances();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageType2{");
    sb.append("sale=").append(getSale());
    sb.append(", numOccurances=").append(numOccurances);
    sb.append('}');
    return sb.toString();
  }
}
