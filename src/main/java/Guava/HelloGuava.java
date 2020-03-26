package Guava;

import com.google.common.base.Optional;

public class HelloGuava {

    public void guava() {
        //guava
        Optional<Integer> possible = Optional.of(6);
        if (possible.isPresent()) {
            System.out.println("possible isPresent:" +
                    possible.isPresent());
            System.out.println("possible value:" + possible.get());
        }
    }

    public static void main(String[] args) {
        HelloGuava hello = new HelloGuava();
        hello.guava();
    }

}
