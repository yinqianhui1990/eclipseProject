package com.yqh.netty.NettyTest;

/**
 * Created by qianhui.yin on 2016/2/29.
 */
public class Test implements Runnable{

    public void run(){
        try {
            NettyClient client = new NettyClient(9998, "45.78.40.30");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
  public  static  void   main(String arg[]){
      System.out.println("测试开始....");
for(int i=0;i<800;i++){
    Test test=new Test();
    Thread t1=new Thread(test);
    t1.start();
    System.out.println(i+1);
}


  }
}
