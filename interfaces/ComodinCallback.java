package interfaces;

import entities.Pawn;

public interface ComodinCallback {
    void onCallback(Pawn pawn);
    void onCallback(Pawn pawn1, Pawn pawn2);
}
