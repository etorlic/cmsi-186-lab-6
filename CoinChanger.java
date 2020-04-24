import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;

public abstract class CoinChanger {
    abstract public int minCoins(int amount, Set<Integer> denominations);

    private static void checkArguments(int amount, Set<Integer> denominations) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be at least 1");
        }
        if (denominations.size() < 1) {
            throw new IllegalArgumentException("At least one denomination is required");
        }
        for (var d : denominations) {
            if (d < 0) {
                throw new IllegalArgumentException("Denominations must all be positive");
            }
        }
        if (!denominations.contains(1)) {
            throw new IllegalArgumentException("Denominations must have a 1-unit coin");
        }
    }

    public static class TopDown extends CoinChanger {
        private HashMap<String, Integer> results = new HashMap<>();
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);
            //key for hashmap
            var key = amount + "" + denominations; 
            //returns value of a result previously computed
            if (results.containsKey(key)) {
                return results.get(key);
            }
            //recursive for-loop
            var minNumCoins = Integer.MAX_VALUE;
            for (var d : denominations) {
                if (d == amount) {
                    minNumCoins = 0;
                }
                else if (d < amount) {
                    minNumCoins = Math.min(minNumCoins, minCoins(amount - d, denominations)); 
                }
            }
            //stores minimum coin in a hashmap at the key
            results.put(key, 1 + minNumCoins);
            return results.get(key);
        }
    }

    public static class BottomUp extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);
            //table to store minCoins at each index
            var table = new int[amount + 1];
            
            //fills table with arbitrary large num
            //and known num of coins at index 0
            Arrays.fill(table, amount + 1);
            table[0] = 0;

            // fills the table up from index 1 and onward with 
            // minimum coins for each index
            for (var i = 1; i <= amount; i++) {
                for (var d : denominations) {
                    if (i - d < 0) {
                        continue;
                    }
                    table[i] = Math.min(table[i - d] + 1, table[i]);
                }
            }
            return table[amount];
        }
    }
}
