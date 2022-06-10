package dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class Schedule {

    public static void main(String[] args) {
        // input: days left until deadline, cost
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Task> tasks = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int deadline = scanner.nextInt();
            int cost = scanner.nextInt();
            tasks.add(new Task(deadline, cost));
            max = max(deadline, max);
        }
        tasks = tasks.stream().sorted().collect(Collectors.toList());
        long ans = 0;
        boolean[] used = new boolean[max];
        for (int i = 0; i < n; i++) {
            int k = tasks.get(i).getDeadline();
            while (k >= 1 && used[k - 1]) {
                k--;
            }
            if (k == 0) {
                continue;
            }
            used[k - 1] = true;
            ans += tasks.get(i).getCost();
        }

        System.out.println(ans);
    }
}

class Task implements Comparable<Task> {

    public Task(int deadline, int cost) {
        this.deadline = deadline;
        this.cost = cost;
    }

    private int deadline;
    private int cost;

    public int getCost() {
        return cost;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task task) {
        if (this.getCost() < task.getCost()) {
            return 1;
        }
        if (this.getCost() > task.getCost()) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Deadline: %s Cost: %s\n", deadline, cost);
    }
}

