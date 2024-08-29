package org.redstonestudio.quick_lamp;

import net.fabricmc.api.ModInitializer;
import org.redstonestudio.quick_lamp.blocks.QuickLampBlock;

public class QuickLamp implements ModInitializer {

    @Override
    public void onInitialize() {
        QuickLampBlock.initialize();
    }
}
