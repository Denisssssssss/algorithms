package dynamic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Cities {

    /*
    ростов
    владивосток
    вологда
    арск
    казань / каир
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] cities = new String[n];
        for (int i = 0; i < n; i++) {
            cities[i] = scanner.nextLine().toLowerCase();
        }

        Map<String, List<Pair<String, String>>> map = new HashMap<>();
        for (String city : cities) {
            String[] letters = city.split("");
            if (!map.containsKey(letters[0])) {
                List<Pair<String, String>> list = new LinkedList<>();
                list.add(Pair.of(letters[city.length() - 1], city));
                map.put(letters[0], list);
            } else {
                map.get(letters[0]).add(Pair.of(letters[city.length() - 1], city));
            }
        }
        String start = map.keySet().stream().findAny().get();
        List<String> path = new LinkedList<>();

        solve(map, start, path, true, cities.length);
        if (path.size() == cities.length) {
            path.forEach(city -> System.out.print(city + "->"));
        } else {
            System.out.println("No solution");
        }
    }

    public static void solve(Map<String, List<Pair<String, String>>> map, String start, List<String> path, boolean isStart, int size) {
        if (map.containsKey(start)) {
            if (map.get(start).size() != 0) {
                Pair<String, String> pair = map.get(start).get(0);
                map.get(start).remove(pair);
                map.get(start).add(pair);
                path.add(pair.getCity());
                if (path.size() != size) {
                    solve(map, pair.getLetter(), path, false, size);
                }
            } else {
                if (!isStart) {
                    map.remove(start);
                }
            }
        }
    }
}

class Pair<LETTER, CITY> {

    static <LETTER, CITY> Pair<LETTER, CITY> of(LETTER first, CITY second) {
        return new Pair<>(first, second);
    }

    private Pair(LETTER letter, CITY city) {
        this.letter = letter;
        this.city = city;
    }

    private LETTER letter;
    private CITY city;

    public LETTER getLetter() {
        return letter;
    }

    public CITY getCity() {
        return city;
    }
}
