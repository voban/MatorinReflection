package homework;

/**
 * Created by Владимир on 18.12.2016.
 */
public class TestApplication {
    public static void main(String[] args) {
        TestClass a = new TestClass(20, "name", 30.0);
        TestClass b = new TestClass();
        System.out.println(b.getAge());
        System.out.println(b.getName());
        System.out.println(b.getPrice());
        BeanUtils.assign(b, a);
        System.out.println(b.getAge());
        System.out.println(b.getName());
        System.out.println(b.getPrice());
    }
}
