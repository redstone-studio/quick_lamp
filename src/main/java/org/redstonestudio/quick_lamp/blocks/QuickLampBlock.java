package org.redstonestudio.quick_lamp.blocks;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuickLampBlock extends Block {
    public static final BooleanProperty LIT = Properties.LIT;

    public static final Block QUICK_LAMP = new QuickLampBlock(FabricBlockSettings.create().strength(4.0f).luminance(state -> state.get(LIT) ? 15 : 0));

    public QuickLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            boolean isLit = state.get(LIT);
            world.setBlockState(pos, state.with(LIT, !isLit), 3);
            world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        return ActionResult.SUCCESS;
    }

    public static void initialize() {
        Registry.register(Registries.BLOCK, new Identifier("quick_lamp", "quick_lamp"), QUICK_LAMP);
        Registry.register(Registries.ITEM, new Identifier("quick_lamp", "quick_lamp"), new BlockItem(QUICK_LAMP, new Item.Settings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.add(QUICK_LAMP);
        });
    }
}
