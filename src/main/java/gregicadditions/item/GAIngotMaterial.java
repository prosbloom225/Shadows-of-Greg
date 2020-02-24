package gregicadditions.item;

import com.google.common.collect.ImmutableList;
import gregtech.api.unification.Element;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.stack.MaterialStack;

public class GAIngotMaterial extends IngotMaterial {
    public int EUt;
    public int duration;

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, float toolSpeed, float attackDamage, int toolDurability, int blastFurnaceTemperature, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element, toolSpeed, attackDamage, toolDurability, blastFurnaceTemperature);
        this.EUt = EUt;
        this.duration = duration;
    }

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element);
        this.EUt = EUt;
        this.duration = duration;
    }

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, int blastFurnaceTemperature, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element, blastFurnaceTemperature);
        this.EUt = EUt;
        this.duration = duration;
    }

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, Element element, float toolSpeed, float attackDamage, int toolDurability, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, element, toolSpeed, attackDamage, toolDurability);
        this.EUt = EUt;
        this.duration = duration;
    }

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, float toolSpeed, float attackDamage, int toolDurability, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags, toolSpeed, attackDamage, toolDurability);
        this.EUt = EUt;
        this.duration = duration;
    }

    public GAIngotMaterial(int metaItemSubId, String name, int materialRGB, MaterialIconSet materialIconSet, int harvestLevel, ImmutableList<MaterialStack> materialComponents, long materialGenerationFlags, int EUt, int duration) {
        super(metaItemSubId, name, materialRGB, materialIconSet, harvestLevel, materialComponents, materialGenerationFlags);
        this.EUt = EUt;
        this.duration = duration;
    }
}
