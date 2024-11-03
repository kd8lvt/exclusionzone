package com.kd8lvt.exclusionzone.content.item.base;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.ExclusionZone.LOGGER;

public abstract class BlockQueue {
    public ArrayList<QueuedBlock> queue = new ArrayList<>();
    private final String name;
    public BlockQueue(String name) {this.name=name;}

    public abstract void processQueue(MinecraftServer server);

    public boolean contains(BlockPos pos) {
        return queue.stream().anyMatch(qBlock->qBlock.pos==pos);
    }

    public boolean add(QueuedBlock block) {
        if (!contains(block.pos)) {
            queue.add(block);
            return true;
        }
        return false;
    }

    public boolean add(BlockPos pos, int depth, ItemStack stack, Entity holder) {
        return add(new QueuedBlock(pos,depth,stack,holder));
    }

    public boolean addAll(List<QueuedBlock> toAdd) {
        return queue.addAll(toAdd);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void onServerShuttingDown() {
        LOGGER.info("Clearing %d queued breaks from the %s queue.".formatted(size(),name));
        queue.clear();
    }

    public void removeForHolder(Entity entity) {
        queue.removeAll(queue.stream().filter(qBlock->qBlock.holder.getUuid()==entity.getUuid()).toList());
    }

    public QueuedBlock nextInQueue() {
        return queue.stream().filter(qBlock->qBlock.getDepth()==0).toList().getFirst();
    }

    public static class QueuedBlock {
        public final BlockPos pos;
        public final ItemStack stack;

        private int depth;
        public final Entity holder;
        public QueuedBlock(BlockPos pos, int depth, ItemStack stack, Entity holder) {
            this.pos = pos;
            this.depth = depth;
            this.stack = stack;
            this.holder = holder;
        }

        public int getDepth() {
            return depth;
        }

        public void process() {
            depth--;
        }
    }
}
