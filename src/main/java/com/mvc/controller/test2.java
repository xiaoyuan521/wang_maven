package com.mvc.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.mvc.dto.UserDto;

@Controller
public class test2 {
    public static void main(String[] args) {

        List<Integer> redBallList = new ArrayList<Integer>();

        // 定义数组：方法一，定义红球的数组，初期化都是0
        Integer redBallArry[] = new Integer[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0};
        // Integer BlueBallArry[] = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        // 定义数组：方法二，初期化篮球数组，只定义长度，所以每个参数初期化是null
        Integer BlueBallArry[] = new Integer[16];

        System.out.println(redBallArry.length);
        for (int i = 0; i < 10000; i++) {

            while (redBallList.size() < 6) {

                int number = (int)(1 + Math.random() * (33));
                redBallArry[number - 1] += 1;
                if (!redBallList.contains(number)) {
                    redBallList.add(number);
                }

            }
            redBallList.sort(null);
            System.out.print("RED BALL：");
            redBallList.forEach(redBall -> {
                System.out.print((redBall < 10 ? "0" + redBall : redBall) + " ");
            });
            System.out.println();
            int blueBall = (int)(1 + Math.random() * (16));

            if (BlueBallArry[blueBall - 1] == null) {
                BlueBallArry[blueBall - 1] = 1;
            } else {
                BlueBallArry[blueBall - 1] += 1;

            }

            System.out.println("BLUE BALL：" + (blueBall < 10 ? "0" + blueBall : blueBall));
            redBallList.clear();// 清空选中的球
        }
        // Arrays.sort(BlueBallArry);
        // Arrays.sort(redBallArry);
        System.out.println("------------------------------------------");
        // List<Integer> listRedBall =new ArrayList<Integer>(Arrays.asList(redBallArry));

        List<UserDto> redBallLsit = new ArrayList<UserDto>();

        // 把数组里面的数据存入list里面，为了排序
        for (int i = 0; i < redBallArry.length; i++) {

            UserDto userDto = new UserDto();

            userDto.setRedId(i);
            userDto.setRedCount(redBallArry[i]);
            redBallLsit.add(userDto);

        }

        redBallLsit.sort(Comparator.comparing(UserDto::getRedCount).reversed());
        for (UserDto UserDtoRedBall : redBallLsit) {
            Integer redBallIdResult = UserDtoRedBall.getRedId() + 1;

            // System.out.println("红球:" + redBallIdResult + " 出现的概率 " + UserDtoRedBall.getRedCount());
            System.out.println("红球:" + (redBallIdResult < 10 ? "0" + redBallIdResult : redBallIdResult) + "出现的概率" + UserDtoRedBall.getRedCount());
        }

        System.out.println("------------------------------------------");

        List<UserDto> blueBallLsit = new ArrayList<UserDto>();

        for (int j = 0; j < BlueBallArry.length; j++) {
            UserDto userDto = new UserDto();

            userDto.setBlueId(j);
            userDto.setBlueCount(BlueBallArry[j]);
            blueBallLsit.add(userDto);

        }
        blueBallLsit.sort(Comparator.comparing(UserDto::getBlueCount).reversed());

        /*
         * for(int x=0;x<blueBallLsit.size();x++){ UserDto userDtoBlueBall = blueBallLsit.get(x);
         * System.out.println("篮球:" + userDtoBlueBall.getBlueId()+1 + " 出现的概率 " + userDtoBlueBall.getBlueCount()); }
         */
        // 上面注释是for循环原型
        for (UserDto userDtoBlueBall : blueBallLsit) {
            Integer blueBallIdResult = userDtoBlueBall.getBlueId() + 1;

            System.out.println("篮球:" + (blueBallIdResult < 10 ? "0" + blueBallIdResult : blueBallIdResult) + " 出现的概率 " + userDtoBlueBall.getBlueCount());
        }

    }

}
