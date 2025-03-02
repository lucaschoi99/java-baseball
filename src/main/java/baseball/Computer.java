package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.platform.commons.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    private static final int LEN_NUM = 3;
    private int strikeCnt = 0;
    private int ballCnt = 0;

    private List<Integer> numbers;

    public Computer() {
        numbers = new ArrayList<>();
    }

    // 각 자리 수가 모두 다른 숫자인지 중복 확인하며 세 자리수 생성
    public void generateNumber() {
        while (numbers.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
    }

    public void init() {
        strikeCnt = 0;
        ballCnt = 0;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> setNum) {
        numbers = setNum;
    }

    // 같은 수가 같은 자리에 있으면 "스트라이크"
    // 같은 수가 다른 자리에 있으면 "볼"
    public void count(List<Integer> userNumber) {
        init();
        for (int i = 0; i < userNumber.size(); i++) {
            Integer digit = userNumber.get(i);
            Integer target = numbers.get(i);

            if (digit == target) {
                this.strikeCnt += 1;
                continue;
            }
            if (numbers.contains(digit)) {
                this.ballCnt += 1;
            }
        }
    }

    public int getStrike() {
        return strikeCnt;
    }
    public void setStrike(int s) {
        strikeCnt = s;
    }

    public int getBall() {
        return ballCnt;
    }

    public void setBall(int b) {
        ballCnt = b;
    }

    // 같은 수가 전혀 없으면 "낫싱"
    public boolean isNothing() {
        if (getStrike() == 0 && getBall() == 0) {
            return true;
        }
        return false;
    }

    // 최종 결과
    public void result() {
        if (isNothing()) {
            System.out.println("낫싱");
            return;
        }

        if (getBall() != 0 && getStrike() != 0) {
            System.out.println(getBall() + "볼 " + getStrike() + "스트라이크");
        } else if (getStrike() != 0) {
            System.out.println(getStrike() + "스트라이크");
        } else {
            System.out.println(getBall() + "볼");
        }
    }

    // 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임을 종료
    public boolean isCorrect() {
        if (getStrike() == 3) {
            numbers.clear();
            return true;
        }
        return false;
    }

}
