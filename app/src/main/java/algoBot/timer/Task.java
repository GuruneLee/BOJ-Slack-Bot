package algoBot.timer;

import java.util.TimerTask;

// TODO 삭제하기. thread 에 직접 이름 지을 수 있음을 확인함
public class Task{
    TimerTask timerTask;
    String name;

    public Task(TimerTask timerTask, String name) {
        this.timerTask = timerTask;
        this.name = name;
    }
}
