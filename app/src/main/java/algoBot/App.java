/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package algoBot;
import algoBot.timer.Scheduler;

public class App {
    public String getGreeting() {
        return "알고리즘을 푸는 게으름뱅이들을 위한 EkJoo 봇을 시작합니다";
    }
    public static void main(String[] args) {
        System.out.println((new App().getGreeting()));
        new Scheduler().schedule();
    }
}
