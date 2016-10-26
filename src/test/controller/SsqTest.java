package test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;


@Controller
public class SsqTest {

    // 使用之前把文件删除(C:\pleiades\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp4\wtpwebapps\wangcyTest\WEB-INF\classes\com\mvc\controller\test.class)
    public static void main(String[] args) {

        /*
         * System.out.println(Arrays.asList("jpg","png","gif","asd"));
         * System.out.println(Arrays.asList("pbg","jpg","gif","asd"));
         * System.out.println(Arrays.asList("abc","png","gif","asd"));
         * System.out.println(Arrays.asList("gif","png","jpg","asd"));
         */

        final Integer RED_BALL_COUNT = 6;
        final Integer RED_BALL_COUNT_MAX = 33;
        final Integer BLUE_BALL_COUNT_MAX = 16;

        Random rd = new Random();

        List<Integer> redBallList = new ArrayList<Integer>();

        while (redBallList.size() < RED_BALL_COUNT) {

            int number = rd.nextInt(RED_BALL_COUNT_MAX);
            if (redBallList.contains(number) || number == 0) {
                continue;

            } else {
                redBallList.add(number);
            }
        }
        redBallList.sort(null);

        System.out.print("Red Ball : ");

        redBallList.forEach(redBall ->
                System.out.print((redBall < 10 ? "0" + redBall : redBall) + " ")
                );

        /*
         * for(int redBall:redBallList){ System.out.print(redBall + "  "); }
         */

        System.out.println();
        int blueBall = 0;

        while (blueBall == 0) {
            blueBall = rd.nextInt(BLUE_BALL_COUNT_MAX);
        }
        System.out.println("Blue Ball : " + (blueBall < 10 ? "0" + blueBall : blueBall));
    }
}
