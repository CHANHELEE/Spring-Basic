package hello.core.singleTone;

import org.junit.jupiter.api.Test;

public class StatefulService {

    //private int price; // 공유 필드를 써서 stateful 상태가 되고, 문제가 터짐
    //해결책 : 공유 필드를 쓰지않고 price 자체를 메소드 내에서 return 함.

    /*public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price ; // 여기가 문제
    }*/

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

    //public int getPrice() {
      //  return price;
    //}

}
