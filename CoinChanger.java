import java.util.Set;
import java.util.HashMap;
// TODO of course, you may wish to import more things...

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
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);
            var results = new HashMap<Integer, Integer>();

            if (results.containsKey(amount)) {
                return results.get(amount);
            }

            var minCoins = Integer.MAX_VALUE;
            for (var d : denominations) {
                var newAmount = minCoins(amount - d, denominations);

                if (newAmount < minCoins) {
                    minCoins = newAmount;
                }
            }

            if (!(minCoins == Integer.MAX_VALUE)) {
                minCoins += 1;
            }

            results.put(amount, minCoins);
            // TODO: Do the top-down-with-memoization algorithm here. You should
            // do this recursively, so write a separate, private, recursive,
            // "helper" method. This method here will call that recursive
            // method with the memo object and the initial amount.
            return minCoins; // TODO change this line, of course
        }
    }

    public static class BottomUp extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);
            
            var table = new int[amount + 1];

            // index 0 is known because it takes 0 coins for 0 amount
            // index 1 is known due to checking if one of denominations contains 1,
            // so it takes 1 coin of 1 unit to fill an amount of 1
            table[0] = 0;
            table[1] = 1;

            // an arbitrary value to fill the rest of the table
            for (var t = 2; t <= amount; t++) {
                table[t] = amount + 1;
            }

            // fills the table up from index 2 and onward with 
            // mininimum coins for each index
            for (var i = 2; i <= amount; i++) {
                for (var d : denominations) {
                    if (i - d < 0) {
                        break;
                    }
                    table[i] = Math.min(table[i - d] + 1, table[i]);
                }
            }

            return table[amount];
        }
    }
}
