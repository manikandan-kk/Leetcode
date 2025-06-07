package leetcode.ds;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VeganFriendly {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        int rl = restaurants.length;
        return IntStream.range(0, rl)
                .filter(idx -> {
                    int vf = restaurants[idx][2];
                    int price = restaurants[idx][3];
                    int distance = restaurants[idx][4];
                    return !((veganFriendly == 1 && vf == 0) || price > maxPrice || distance > maxDistance);
                })
                .boxed()
                .sorted((o1, o2) -> {
                    int r1 = restaurants[o1][1], r2 = restaurants[o2][1];
                    int idx1 = restaurants[o1][0], idx2 = restaurants[o2][0];
                    return r1 > r2 ? -1 : (r1 < r2 ? 1 : (idx1 > idx2 ? -1 : 1));
                })
                .map(idx -> restaurants[idx][0])
                .collect(Collectors.toList());
    }
}
