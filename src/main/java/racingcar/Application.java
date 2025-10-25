package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;


public class Application {
    public static void main(String[] args) {
         List<String> carList = getCarName();
         int tries = getTries();
         Map<String, Integer> carScore = new HashMap<>();

         System.out.println("실행 결과");
         for (int t = 0; t < tries; t++) {
             for (String car : carList) {
                 if (decideForward()) {
                     carScore.put(car, carScore.getOrDefault(car, 0) + 1);
                 }
                 else {
                     carScore.put(car, carScore.getOrDefault(car, 0));
                 }
             }

             visualizeProgress(carList, carScore);
         }

        visualizeWinner(carList, carScore);
    }


    public static List<String> getCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputCarName = Console.readLine();

        if (inputCarName == null || inputCarName.isBlank()) {
            throw new IllegalArgumentException("오류: 자동차 이름을 최소 한 개 이상 입력해야 합니다.");
        }

        return Arrays.stream(inputCarName.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
                .peek(name -> {
                    if (name.length() > 5) {
                        throw new IllegalArgumentException("오류: 자동차 이름은 5자를 초과할 수 없습니다.");
                    }
                })
                .collect(Collectors.toList());
    }

    public static int getTries() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String inputTries = Console.readLine();
        int numTries;

        try {
            numTries = Integer.parseInt(inputTries);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("오류: 올바르지 않은 시도 횟수입니다. ", e);
        }

        if (numTries < 0) {
            throw new IllegalArgumentException("오류: 음수의 시도 횟수를 가질 수 없습니다.");
        }

        return numTries;
    }

    public static boolean decideForward() {
        return Randoms.pickNumberInRange(0, 9) >= 4;
    }

    public static void visualizeProgress(List<String> car, Map<String, Integer> carForward) {
        for (String name : car) {
            Integer repeat = carForward.get(name);

            if (repeat == null) {
                repeat = 0;
            }

            System.out.printf("%s : ", name);

            for (int j = 0; j < repeat; j++) {
                System.out.print("-");
            }

            System.out.println();
        }

        System.out.println();
    }

    public static void visualizeWinner(List<String> car, Map<String, Integer> score) {
        int maxScore = 0;
        ArrayList<String> winners = new ArrayList<>();

        for (String name : car) {
            if (maxScore < score.get(name)) {
                maxScore = score.get(name);
                winners.clear();
                winners.add(name);
            }
            else if (maxScore == score.get(name)) {
                winners.add(name);
            }
        }

        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
