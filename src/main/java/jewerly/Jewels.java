package jewerly;

import java.util.HashMap;
import java.util.Map;

public class Jewels {
    /*
     * There are two thongs
     * String jewelery = "AuiP"; --> types of jewelry
     * String stones = "AUUUUiHNhfgkpPKjAAaakndsdl"; --> a set of stones that
     * contains simple stones and jewels
     * !!! Find how many and what jewels in a set of stones
     */

    public static void main(String[] args) {
        String jewelery = "AuiP";
        String stones = "AUUUUiHNhfgkpPKjAAaakndsdl";

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < stones.length(); i++) {
            map.merge(stones.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < jewelery.length(); i++) {
            Character jewel = jewelery.charAt(i);
            System.out.printf("Jewel [%c] -> [%d]\n", jewel, map.get(jewel));
        }
    }
}
