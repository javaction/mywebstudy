/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

import java.util.logging.Logger;

/**
 *
 * @author Ussopp.Su
 */
public class LogManager {
 
  /**
   * 获取日志对象
   * @param clazz
   * 
   */
  public static Logger getLogger(Class clazz) {
      Logger logger = Logger.getLogger(clazz.getName());
      return logger;
  }
    
    
}
