import java.util.*;

public class DesignUndergroundSystem {
    Map<Integer, Pair> checkInMap;
    Map<String, int[]> routeMap;

    class Pair {
        String station;
        int time;

        Pair(String s, int t) {
            station = s;
            time = t;
        }
    }

    public DesignUndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair p = checkInMap.remove(id);
        String route = p.station + "-" + stationName;
        int[] data = routeMap.getOrDefault(route, new int[2]);
        data[0] += t - p.time;
        data[1]++;
        routeMap.put(route, data);
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] data = routeMap.get(startStation + "-" + endStation);
        return (double) data[0] / data[1];
    }
}