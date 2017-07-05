/**********************************************************************.
 *                                                                     *
 *         Copyright (c) Ultra Electronics Airport Systems 2017     *
 *                         All rights reserved                         *
 *                                                                     *
 ***********************************************************************/

package com.jp.solution.model.message;

import com.jp.solution.model.Sale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author praveen.nair, created on 05/07/2017.
 */
public abstract class AbstractMessageType {
  /**
   * instance of the sale.
   */
  private Sale sale;

  private String messageType;

  public AbstractMessageType(String messageType) {
    this.messageType = messageType;
  }

  public Sale getSale() {
    return sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    AbstractMessageType that = (AbstractMessageType) o;

    if (getSale() != null ? !getSale().equals(that.getSale()) : that.getSale() != null)
      return false;
    return getMessageType() != null ? getMessageType().equals(that.getMessageType()) : that.getMessageType() == null;
  }

  @Override
  public int hashCode() {
    int result = getSale() != null ? getSale().hashCode() : 0;
    result = 31 * result + (getMessageType() != null ? getMessageType().hashCode() : 0);
    return result;
  }

  public abstract String toString();
}
