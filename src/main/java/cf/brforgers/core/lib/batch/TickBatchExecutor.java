package cf.brforgers.core.lib.batch;

import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public abstract class TickBatchExecutor extends BatchExecutor {
    public abstract static class Base extends BatchExecutor {
        public boolean runOnTickStart = true;
        public boolean runOnTickEnd = true;

        public void tickRun(TickEvent.Phase phase) {
            if (phase == TickEvent.Phase.START && runOnTickStart) tickRun();
            if (phase == TickEvent.Phase.END && runOnTickEnd) tickRun();
        }

        public void tickRun() {
            tickTimeout = MathHelper.clamp_int(tickTimeout, 1, 4);
            super.tickRun();
        }
    }

    public static class Server extends Base {
        @SubscribeEvent
        public void serverTick(TickEvent.ServerTickEvent e) {
            tickRun(e.phase);
        }
    }

    public static class Client extends Base {
        @SubscribeEvent
        public void clientTick(TickEvent.ClientTickEvent e) {
            tickRun(e.phase);
        }
    }

    public static class World extends Base {
        @SubscribeEvent
        public void worldTick(TickEvent.WorldTickEvent e) {
            tickRun(e.phase);
        }
    }

    public static class Player extends Base {
        @SubscribeEvent
        public void worldTick(TickEvent.PlayerTickEvent e) {
            tickRun(e.phase);
        }
    }
}