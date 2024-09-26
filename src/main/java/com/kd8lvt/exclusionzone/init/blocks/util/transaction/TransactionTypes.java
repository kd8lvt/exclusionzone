package com.kd8lvt.exclusionzone.init.blocks.util.transaction;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.fabricmc.fabric.api.transfer.v1.transaction.base.SnapshotParticipant;

@SuppressWarnings("unused")
public class TransactionTypes {
    public static class INTEGER extends SnapshotParticipant<Integer> {
        private Integer value;

        @SuppressWarnings("unused")
        public INTEGER() {this(0);}
        public INTEGER(Integer startingValue) {
            this.value = startingValue;
        }

        public Integer get() {
            return value;
        }

        public void increment(Integer amount, TransactionContext transaction) {
            updateSnapshots(transaction);
            value += amount;
        }

        @Override
        protected Integer createSnapshot() {
            return value;
        }

        @Override
        protected void readSnapshot(Integer snapshot) {
            this.value = snapshot;
        }

    }
}
