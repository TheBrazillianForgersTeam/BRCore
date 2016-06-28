package cf.brforgers.core.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FastFactory {
	public static class FactoryBlock extends Block {protected FactoryBlock(Material mat) {super(mat);}}
	public static class FactoryItem extends Item {protected FactoryItem(){}}
	
	public CreativeTabs defaultTab;
	public String texturePrefix;
	public Material defaultMaterial;

	public Block newBlock(String name)
	{
		return new FactoryBlock(defaultMaterial).setBlockName(name).setBlockTextureName(texturePrefix + name).setCreativeTab(defaultTab);
	}
	
	public <BlockType extends Block> BlockType processBlock(BlockType block, String name)
	{
		return (BlockType) block.setBlockName(name).setBlockTextureName(texturePrefix + name).setCreativeTab(defaultTab);
	}
	
	public Item newItem(String name)
	{
		return new FactoryItem().setUnlocalizedName(name).setTextureName(texturePrefix + name).setCreativeTab(defaultTab);
	}
	
	public <ItemType extends Item> ItemType processItem(ItemType item, String name)
	{
		return (ItemType) item.setUnlocalizedName(name).setTextureName(texturePrefix + name).setCreativeTab(defaultTab);
	}
	
	public static FastFactory newFactory(CreativeTabs defaultTab, String texturePrefix, Material defaultMaterial) {
		FastFactory f = new FastFactory();
		f.defaultTab = defaultTab;
		f.texturePrefix = texturePrefix;
		f.defaultMaterial = defaultMaterial;
		return f;
	}
	private FastFactory() {}
}
