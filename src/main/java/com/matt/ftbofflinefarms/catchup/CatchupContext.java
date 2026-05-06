package com.matt.ftbofflinefarms.catchup;

public class CatchupContext {
    private int remainingBlockUpdates;

    public CatchupContext(int remainingBlockUpdates) {
        this.remainingBlockUpdates = remainingBlockUpdates;
    }

    public boolean consumeUpdates(int updates) {
        if (remainingBlockUpdates < updates) return false;
        remainingBlockUpdates -= updates;
        return true;
    }
}
