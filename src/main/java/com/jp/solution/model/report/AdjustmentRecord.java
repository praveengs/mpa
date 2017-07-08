package com.jp.solution.model.report;

import com.jp.solution.model.message.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Record for an individual adjustment.
 *
 * @author praveen.nair, created on 07/07/2017.
 */
public class AdjustmentRecord {

  private Operation operation;
  private double adjustmentInPence;

  public AdjustmentRecord(Operation operation, double adjustmentInPence) {
    this.operation = operation;
    this.adjustmentInPence = adjustmentInPence;
  }

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public double getAdjustmentInPence() {
    return adjustmentInPence;
  }

  public void setAdjustmentInPence(double adjustmentInPence) {
    this.adjustmentInPence = adjustmentInPence;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AdjustmentRecord that = (AdjustmentRecord) o;

    if (Double.compare(that.adjustmentInPence, adjustmentInPence) != 0) {
      return false;
    }
    return operation == that.operation;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = operation != null ? operation.hashCode() : 0;
    temp = Double.doubleToLongBits(adjustmentInPence);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "AdjustmentRecord{" +
        "operation=" + operation +
        ", adjustmentInPence=" + adjustmentInPence +
        '}';
  }
}
