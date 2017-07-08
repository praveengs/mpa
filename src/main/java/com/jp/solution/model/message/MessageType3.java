package com.jp.solution.model.message;

import com.jp.solution.common.AppConstants;
import com.jp.solution.model.Sale;

/**
 * contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations
 * can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have
 * recorded.
 * 
 * @author praveen.nair, created on 04/07/2017.
 */
public class MessageType3 extends AbstractMessageType {

  /**
   * adjustment operation to be applied to all stored sales of this product type.
   */
  private Operation operation;

  public MessageType3(Sale sale, Operation operation) {
    super(AppConstants.MESSAGE_TYPE_3);
    setSale(sale);
    setOperation(operation);
  }

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;

    MessageType3 that = (MessageType3) o;

    return super.equals(o) && getOperation() == that.getOperation();
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageType3{");
    sb.append("sale=").append(getSale());
    sb.append(", operation=").append(operation);
    sb.append('}');
    return sb.toString();
  }
}
