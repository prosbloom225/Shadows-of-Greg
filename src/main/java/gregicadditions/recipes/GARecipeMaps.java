package gregicadditions.recipes;

import crafttweaker.annotations.ZenRegister;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.api.recipes.machines.FuelRecipeMap;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenProperty;

@ZenClass("mods.gtadditions.recipe.GARecipeMaps")
@ZenRegister

public class GARecipeMaps {
	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> CLUSTER_MILL_RECIPES;
	@ZenProperty
	public static final RecipeMapAssemblyLine<SimpleRecipeBuilder> ASSEMBLY_LINE_RECIPES;
	@ZenProperty
	public static final FuelRecipeMap NAQUADAH_REACTOR_FUELS;
	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> MASS_FAB_RECIPES;
	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> REPLICATOR_RECIPES;
	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> CRACKER_UNIT_RECIPES;
	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> PROCESSING_ARRAY_RECIPES;

	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> ALLOY_BLAST_SMELTER;

	@ZenProperty
	public static final RecipeMap<SimpleRecipeBuilder> TREE_FARM;

	static {
		CLUSTER_MILL_RECIPES = new RecipeMap<>("cluster_mill", 1, 1, 1, 1, 0, 0, 0, 0, new SimpleRecipeBuilder()).setSlotOverlay(false, false, GuiTextures.BENDER_OVERLAY).setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);
		ASSEMBLY_LINE_RECIPES = new RecipeMapAssemblyLine<>("assembly_line", 4, 16, 1, 1, 0, 4, 0, 0, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);
		ASSEMBLY_LINE_RECIPES.setSlotOverlay(false, false, GuiTextures.MOLD_OVERLAY);
		NAQUADAH_REACTOR_FUELS = new FuelRecipeMap("naquadah_reactor");
		MASS_FAB_RECIPES = new RecipeMap<>("mass_fab", 0, 1, 0, 0, 0, 1, 1, 2, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);
		REPLICATOR_RECIPES = new RecipeMap<>("replicator", 0, 1, 0, 1, 1, 2, 0, 1, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);
		CRACKER_UNIT_RECIPES = new RecipeMap<>("cracker_unit", 0, 0, 0, 0, 2, 2, 1, 1, new SimpleRecipeBuilder());
		PROCESSING_ARRAY_RECIPES = new RecipeMap<>("processing_array", 0, 9, 0, 6, 0, 3, 0, 2, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_BENDING, ProgressWidget.MoveType.HORIZONTAL);
		ALLOY_BLAST_SMELTER = new RecipeMap<>("alloy_blast_smelter", 0, 9, 0, 1, 0, 9, 0, 1, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);
		TREE_FARM = new RecipeMap<>("tree_farm", 1, 1, 0, 3, 0, 0, 0, 1, new SimpleRecipeBuilder()).setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, ProgressWidget.MoveType.HORIZONTAL);
	}
}
