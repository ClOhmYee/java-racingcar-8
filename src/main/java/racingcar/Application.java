package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        List<String> carList = getCarName();

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
}
