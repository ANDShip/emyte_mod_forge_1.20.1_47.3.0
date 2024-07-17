package net.andship.emyte_mod.item.custom;

import net.andship.emyte_mod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockDetectorItem extends Item {

    public BlockDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;
            int radius = 3;

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos currentPos = positionClicked.offset(x, y, z);
                        BlockState state = pContext.getLevel().getBlockState(currentPos);

                        if (isValuableBlock(state)) {
                            outputValuableCoordinates(currentPos, player, state.getBlock());
                            harvestBlock(currentPos, player, state.getBlock(), pContext);
                            foundBlock = true;
                        }
                    }
                }
            }


            /*for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if(isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                    
                }

            }*/

            if(!foundBlock) {
                player.sendSystemMessage(Component.literal("Nothing found!"));

            }

        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.emyte_mod.block_detector.tooltip"));


        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void harvestBlock(BlockPos blockPos, Player player, Block block, UseOnContext pContext) {
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);
        // Replace the valuable block with air
        pContext.getLevel().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);

        // Optionally drop the block as an item
        Block.popResource(pContext.getLevel(), blockPos, blockState.getBlock().asItem().getDefaultInstance());
    }


    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
            player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) +
                    " at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));

    }





    private boolean isValuableBlock(BlockState state) {

        return state.is(ModTags.Blocks.BLOCK_DETECTOR_VALUABLES);
    }
}
