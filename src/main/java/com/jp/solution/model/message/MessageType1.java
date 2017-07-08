package com.jp.solution.model.message;

import com.jp.solution.common.AppConstants;
import com.jp.solution.model.Sale;

/**
 * Contains the details of 1 sale E.g apple at 10p. Ideally this
 * class in itself is not needed, since it doesn't add any additional functionalities
 * to the parent class. But this is here for clarity in inheritance.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class MessageType1 extends AbstractMessageType {

  public MessageType1(Sale sale) {
    super(AppConstants.MESSAGE_TYPE_1);
    setSale(sale);
  }

  public String toString() {
    final StringBuilder sb = new StringBuilder("MessageType1{");
    sb.append("sale=").append(getSale());
    sb.append(", messageType='").append(getMessageType()).append('\'');
    sb.append('}');
    return sb.toString();
  }

}
