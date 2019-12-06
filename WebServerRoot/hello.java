public class hello{

    public void  hello() {
        System.out.println("current classloader is " + getClass().getClassLoader().getClass());
    }
}