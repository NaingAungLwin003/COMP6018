package Battleships;

import java.util.List;

class Ship {
    private final List<int[]> positions;
    private final boolean[] hits;

    public Ship(List<int[]> pos) {
        positions = pos;
        hits = new boolean[pos.size()];
    }

    public boolean hit(int row, int col) {
        for (int i = 0; i < positions.size(); i++) {
            int[] p = positions.get(i);
            if (p[0] == row && p[1] == col) {
                hits[i] = true;
                return true;
            }
        }
        return false;
    }

    public boolean isSunk() {
        for (boolean h : hits) if (!h) return false;
        return true;
    }
}