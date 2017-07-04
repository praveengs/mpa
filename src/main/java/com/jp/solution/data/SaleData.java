/**********************************************************************.
 *                                                                     *
 *         Copyright (c) Ultra Electronics Airport Systems 2017     *
 *                         All rights reserved                         *
 *                                                                     *
 ***********************************************************************/

package com.jp.solution.data;

import com.jp.solution.model.message.MessageType1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * This class will be used as a data cache for all sale types.
 *
 * @author praveen.nair, created on 04/07/2017.
 */
public class SaleData {
  /**
   * The Constant LOGGER.
   */
  private static final Logger LOGGER = LogManager.getLogger(SaleData.class);

  private ConcurrentHashMap<String, MessageType1> messageMap;
}
