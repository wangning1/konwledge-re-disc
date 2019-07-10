package com.web.Exception;

/**
 * Created by wangning on 2017/1/17.
 */
public class ControllerException extends Exception{

  public ControllerException(){
    super();
  }

  public ControllerException(String message){
    super(message);
  }

  public ControllerException(String message,Throwable throwable){
    super(message,throwable);
  }

}
