package com.play4fun.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LeoLock {

    private static class LeoSync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    private LeoSync leoSync = new LeoSync();

    public void lock() {
        leoSync.acquire(1);
    }

    public void unLock() {
        leoSync.release(1);
    }


}
