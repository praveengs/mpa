package com.jp.solution.model.message;

/**
 * contains the details of a sale and an adjustment operation to be applied to all stored sales of this product type. Operations
 * can be add, subtract, or multiply e.g Add 20p apples would instruct your application to add 20p to each sale of apples you have
 * recorded.
 * 
 * @author praveen.nair, created on 04/07/2017.
 */
public class MessageType3 extends MessageType1 {

  /**
   * adjustment operation to be applied to all stored sales of this product type.
   */
  private Operation operation;


}
