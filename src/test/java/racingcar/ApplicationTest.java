package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 기능_심화_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("cl,ohm,yee,john,james", "5");
                    assertThat(output()).contains("cl : -----", "ohm : ", "yee : ", "john : ", "james : ", "최종 우승자 : cl");
                },
                MOVING_FORWARD, STOP, STOP, STOP, STOP,
                MOVING_FORWARD, STOP, STOP, STOP, STOP,
                MOVING_FORWARD, STOP, STOP, STOP, STOP,
                MOVING_FORWARD, STOP, STOP, STOP, STOP,
                MOVING_FORWARD, STOP, STOP, STOP, STOP
        );
    }

    @Test
    void 엣지_케이스_테스트() {
        assertSimpleTest(() -> {
            run("pobi,woni", "0");
            assertThat(output()).contains("pobi : ", "woni : ", "최종 우승자 : pobi, woni");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
