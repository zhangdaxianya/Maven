import org.junit.Test;

import java.util.Random;

public class Demo {



    @Test
    public void demo(){
        Random random = new Random();

        String[] str = {"A", "B", "C", "D", "E", "F", "G", "a", "b", "c", "d", "e", "f", "g"};
        String code = "";
        for (int i = 0; i < 5; i++) {
            Integer index = random.nextInt(str.length);
            code += str[index];
        }

        System.out.println(code);

        System.out.println(System.currentTimeMillis());

        System.out.println(System.currentTimeMillis()+code);


    }
}
