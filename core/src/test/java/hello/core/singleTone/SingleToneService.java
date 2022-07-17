package hello.core.singleTone;

public class SingleToneService {

    private  static final  SingleToneService instance = new SingleToneService();

    public static  SingleToneService getInstance(){
        return instance;
    }

    private SingleToneService(){ // 생성자를 private 으로 막아서 객체 외부에서 new로 생성을 못하도록 막음.
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
