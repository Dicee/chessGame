package utils;

import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestReadableBoard implements ReadableBoard {
    private Map<ImmutablePoint, Player> occupiedCells = new HashMap<>();

    public TestReadableBoard withOccupied(Player player, ImmutablePoint firstPos, ImmutablePoint... positions) {
        return withOccupied(player, RichIterators.prepend(firstPos, RichIterators.of(positions)).toSet());
    }

    public TestReadableBoard withOccupied(Player player, Set<ImmutablePoint> positions) {
        for (ImmutablePoint pos : positions) occupiedCells.put(pos, player);
        return this;
    }

    @Override
    public Player getOccupier(int x, int y) { return occupiedCells.get(new ImmutablePoint(x, y)); }
}
