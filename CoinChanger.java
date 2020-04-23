import java.util.Set;
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
        var oneUnitCoin = false; //boolean to say whether there is a 1-unit coin or not
        for (var d : denominations) {
            if (d < 0) {
                throw new IllegalArgumentException("Denominations must all be positive");
            }
            if (d != 1) {
                oneUnitCoin = false;
            }
        }
        if (oneUnitCoin == false) {
            throw new IllegalArgumentException("Denominations must have a 1-unit coin");
        }
    }

    public static class TopDown extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            // TODO: Do the top-down-with-memoization algorithm here. You should
            // do this recursively, so write a separate, private, recursive,
            // "helper" method. This method here will call that recursive
            // method with the memo object and the initial amount.
            return 0; // TODO change this line, of course
        }
    }

    public static class BottomUp extends CoinChanger {
        public int minCoins(int amount, Set<Integer> denominations) {
            checkArguments(amount, denominations);

            // TODO: Implement this method using the bottom-up approach with
            // a table.
            return 0; // TODO change this line, of course
        }
    }
}
