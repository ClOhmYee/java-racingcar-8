package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        List<String> carList = getCarName();
        int tries = getTries();

        // 추후 구현 예정
    }


    public static List<String> getCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String inputCarName = Console.readLine();

        if (inputCarName == null || inputCarName.isBlank())
            throw new IllegalArgumentException("오류: 자동차 이름을 최소 한 개 이상 입력해야 합니다.");

        return Arrays.stream(inputCarName.split(","))
                .map(String::trim)
                .filter(name -> !name.isEmpty())
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
}
