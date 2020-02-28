package gregicadditions;

import com.google.common.collect.ImmutableList;
import gregicadditions.item.GAIngotMaterial;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.stack.MaterialStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static gregtech.api.unification.material.Materials.*;

@IMaterialHandler.RegisterMaterialHandler
public class GAAlloys implements IMaterialHandler {
    public static List<GAIngotMaterial> alloys;

    @Override
    public void onMaterialsInit() {
        alloys = Stream.of(STABALLOY, TANTALLOY60, TANTALLOY61, TUMBAGA, POTIN, INCONEL625, INCONEL690, INCONEL792, NITINOL60,
                NITINOL60, ZERON100, MARAGING250, MARAGING300, MARAGING350, AQUATIC_STEEL, STELLITE, TALONITE, HASTELLOYW,
                HASTELLOYX, HASTELLOYN, HASTELLOYC276, INCOLOY020, INCOLOYDS, INCOLOYMA956, TUNGSTENTITANIUMCARBIDE,
                SILICONCARBIDE, TANTALUMCARBIDE, ZIRCONIUMCARBIDE, NIOBIUMCARBIDE, GRISIUM, EGLINSTEELBASE, EGLINSTEEL,
                LAFIUM, ABYSSAL, QUANTUM
        ).collect(Collectors.toList());
    }

    static long STD_METAL = DustMaterial.MatFlags.GENERATE_PLATE;
    static long EXT2_METAL = STD_METAL | SolidMaterial.MatFlags.GENERATE_ROD | IngotMaterial.MatFlags.GENERATE_BOLT_SCREW | SolidMaterial.MatFlags.GENERATE_GEAR | IngotMaterial.MatFlags.GENERATE_FOIL | IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
    static long EXT3_METAL = EXT2_METAL | SolidMaterial.MatFlags.GENERATE_FRAME;
    // GT Alloys



    // GT++ Alloys
    public static final GAIngotMaterial STABALLOY = new GAIngotMaterial(704, "staballoy", 0x3c423a, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Titanium, 1),
                    new MaterialStack(Uranium, 9)),
            EXT3_METAL, null, 7.0F, 3.0F, 1600, 3450, 480, 600);

    public static final GAIngotMaterial TANTALLOY60 = new GAIngotMaterial(705, "tantalloy60", 0xd5e7ed, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Tungsten, 4),
                    new MaterialStack(Tantalum, 46)),
            STD_METAL, null, 7.0F, 3.0F, 1600, 3025, 480, 600);

    public static final GAIngotMaterial TANTALLOY61 = new GAIngotMaterial(706, "tantalloy61", 0xc1d3d9, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(TANTALLOY60, 4),
                    new MaterialStack(Titanium, 12),
                    new MaterialStack(Yttrium, 8)),
            STD_METAL, null, 7.0F, 3.0F, 1600, 3030, 480, 600);

    public static final GAIngotMaterial TUMBAGA = new GAIngotMaterial(707, "tumbaga", 0xffb30f, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Gold, 70),
                    new MaterialStack(Copper, 30)),
            EXT3_METAL, null, 3.0F, 1.0F, 400, 7, 4480);

    public static final GAIngotMaterial POTIN = new GAIngotMaterial(708, "potin", 0xc99781, MaterialIconSet.METALLIC, 2,
            ImmutableList.of(new MaterialStack(Lead, 1),
                    new MaterialStack(Bronze, 1),
                    new MaterialStack(Tin, 1)),
            STD_METAL, null, 3.0F, 1.0F, 800, 16, 266);

    public static final GAIngotMaterial INCONEL625 = new GAIngotMaterial(709, "inconel625", 0x80c880, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Nichrome, 10),
                    new MaterialStack(Nickel, 3),
                    new MaterialStack(Chrome, 7),
                    new MaterialStack(Invar, 10),
                    new MaterialStack(Molybdenum, 10)),
            EXT3_METAL, null, 6.0F, 3.0F, 1800, 3758, 120, 400);

    public static final GAIngotMaterial INCONEL690  = new GAIngotMaterial(710, "inconel690", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nichrome, 10),
                    new MaterialStack(Nickel, 3),
                    new MaterialStack(Chrome, 7),
                    new MaterialStack(Invar, 10),
                    new MaterialStack(Molybdenum, 10)),
            STD_METAL, null, 7.0F, 3.5F, 2200, 4895, 480, 600);

    public static final GAIngotMaterial INCONEL792 = new GAIngotMaterial(711, "inconel792", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nickel, 20),
                    new MaterialStack(Niobium, 10),
                    new MaterialStack(Aluminium, 20),
                    new MaterialStack(Nichrome, 10)),
            STD_METAL, null, 7.0F, 3.5F, 2200, 6200, 480, 600);

    public static final GAIngotMaterial NITINOL60 = new GAIngotMaterial(712, "nitinol60", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nickel, 2),
                    new MaterialStack(Titanium, 3)),
            STD_METAL, null, 8.0F, 4.0F, 2800, 8975, 7680, 1500);

    public static final GAIngotMaterial ZERON100 = new GAIngotMaterial(713, "zeron100", 0xb4b414, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Chrome, 26),
                    new MaterialStack(Nickel, 6),
                    new MaterialStack(Molybdenum, 6),
                    new MaterialStack(Copper, 20),
                    new MaterialStack(Tungsten, 4),
                    new MaterialStack(Steel, 40)),
            STD_METAL, null, 8.0F, 4.0F, 2900, 9785, 30720, 7200);

    public static final GAIngotMaterial MARAGING250 = new GAIngotMaterial(714, "maraging250", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Molybdenum, 4),
                    new MaterialStack(Titanium, 4),
                    new MaterialStack(Nickel, 16),
                    new MaterialStack(Cobalt, 8)),
            STD_METAL, null, 7.0F, 3.0F, 1800, 4555, 120, 16);

    public static final GAIngotMaterial MARAGING300 = new GAIngotMaterial(715, "maraging300", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Titanium, 4),
                    new MaterialStack(Nickel, 16),
                    new MaterialStack(Aluminium, 8),
                    new MaterialStack(Cobalt, 8)),
    STD_METAL, null, 7.0F, 3.0F, 2000, 4555, 120, 16);

    public static final GAIngotMaterial MARAGING350 = new GAIngotMaterial(716, "maraging350", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Aluminium, 4),
                    new MaterialStack(Molybdenum, 4),
                    new MaterialStack(Nickel, 4),
                    new MaterialStack(Cobalt, 8)),
            STD_METAL, null, 7.0F, 4.0F, 2200, 4555, 120, 400);

    public static final GAIngotMaterial AQUATIC_STEEL = new GAIngotMaterial(717, "aquatic_steel", 0x7878b4, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 60),
                    new MaterialStack(Carbon, 10),
                    new MaterialStack(Manganese, 5),
                    new MaterialStack(Silicon, 10),
                    new MaterialStack(Phosphorus, 5),
                    //new MaterialStack(Sulfur, 5),
                    new MaterialStack(Aluminium, 5)),
    STD_METAL, null, 6.0F, 3.0F, 1800, 4835, 120, 400);

    public static final GAIngotMaterial STELLITE = new GAIngotMaterial(718, "stellite", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Cobalt, 35),
                    new MaterialStack(Chrome, 35),
                    new MaterialStack(Manganese, 20),
                    new MaterialStack(Titanium, 10)),
    STD_METAL, null, 5.0F, 3.0F, 1900, 6250, 1920, 800);

    public static final GAIngotMaterial TALONITE = new GAIngotMaterial(719, "talonite", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Cobalt, 40),
                    new MaterialStack(Chrome, 30),
                    new MaterialStack(Phosphorus, 20),
                    new MaterialStack(Molybdenum, 10)),
                    EXT3_METAL, null, 5.0F, 5.0F, 1600, 5500, 480, 600);

    public static final GAIngotMaterial HASTELLOYW = new GAIngotMaterial(720, "hastelloyw", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 6),
                    new MaterialStack(Cobalt, 24),
                    new MaterialStack(Chrome, 6),
                    new MaterialStack(Nickel, 62)),
            STD_METAL, null, 5.0F, 5.0F, 1600, 5755, 480, 600);

    public static final GAIngotMaterial HASTELLOYX = new GAIngotMaterial(721, "hastelloyx", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 18),
                    new MaterialStack(Manganese, 2),
                    new MaterialStack(Silicon, 2),
                    new MaterialStack(Molybdenum, 8),
                    new MaterialStack(Chrome, 22),
                    new MaterialStack(Nickel, 48)),
                    STD_METAL, null, 5.0F, 6.0F, 2800, 5755, 480, 600);

    public static final GAIngotMaterial HASTELLOYN = new GAIngotMaterial(722, "hastelloyn", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Yttrium, 8),
                    new MaterialStack(Molybdenum, 16),
                    new MaterialStack(Chrome, 8),
                    new MaterialStack(Titanium, 8),
                    new MaterialStack(Nickel, 60)),
                    STD_METAL, null, 5.0F, 7.0F, 3200, 6875, 1920, 1000);

    public static final GAIngotMaterial HASTELLOYC276 = new GAIngotMaterial(723, "hastelloyc276", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Cobalt, 2),
                    new MaterialStack(Molybdenum, 16),
                    new MaterialStack(Tungsten, 2),
                    new MaterialStack(Copper, 2),
                    new MaterialStack(Chrome, 14),
                    new MaterialStack(Nickel, 64)),
                    STD_METAL, null, 5.0F, 5.0F, 2600, 6250, 1920, 800);

    public static final GAIngotMaterial INCOLOY020 = new GAIngotMaterial(724, "incoloy020", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 40),
                    new MaterialStack(Copper, 4),
                    new MaterialStack(Chrome, 20),
                    new MaterialStack(Nickel, 36)),
            EXT3_METAL, null, 3.0F, 3.0F, 2200, 5420, 480, 600);

    public static final GAIngotMaterial INCOLOYDS = new GAIngotMaterial(725, "incoloyds", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 40),
                    new MaterialStack(Cobalt, 18),
                    new MaterialStack(Chrome, 18),
                    new MaterialStack(Nickel, 18)),
            STD_METAL, null, 3.0F, 3.0F, 2400, 5420, 480, 600);

    public static final GAIngotMaterial INCOLOYMA956 = new GAIngotMaterial(726, "incoloyma956", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 64),
                    new MaterialStack(Aluminium, 12),
                    new MaterialStack(Chrome, 20),
                    new MaterialStack(Yttrium, 4)),
            EXT3_METAL, null, 3.0F, 3.0F, 2400, 6875, 1920, 800);

    public static final GAIngotMaterial TUNGSTENTITANIUMCARBIDE = new GAIngotMaterial(727, "tungsten_titanium_carbide", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(TungstenCarbide, 70),
                    new MaterialStack(Titanium, 30)),
                    STD_METAL, null, 5.0F, 5.0F, 3300, 1920, 1000);

    public static final GAIngotMaterial SILICONCARBIDE = new GAIngotMaterial(728, "silicon_carbide", 0x283024, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Silicon, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 3.0F, 3.0F, 2200, 15, 200);

    public static final GAIngotMaterial TANTALUMCARBIDE = new GAIngotMaterial(729, "tantalum_carbide", 0x8b8878, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Tantalum, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 4.0F, 4.0F, 2600, 480, 600);

    public static final GAIngotMaterial ZIRCONIUMCARBIDE = new GAIngotMaterial(730, "zirconium_carbide", 0xdecab4, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Diamond, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 5.0F, 5.0F, 1800, 15, 200);

    public static final GAIngotMaterial NIOBIUMCARBIDE = new GAIngotMaterial(731, "niobium_carbide", 0xdecab4, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Niobium, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 4.0F, 4.0F, 2200, 120, 400);

    public static final GAIngotMaterial GRISIUM = new GAIngotMaterial(732, "grisium", 0x294953, MaterialIconSet.METALLIC, 2,
            ImmutableList.of(new MaterialStack(Titanium, 9),
                    new MaterialStack(Carbon, 9),
                    new MaterialStack(Lithium, 9),
                    new MaterialStack(Sulfur, 9),
                    new MaterialStack(Potassium, 9)),
            EXT3_METAL,
            null, 8.0F, 3.0F, 1280, 4125, 1920, 800);
    public static final GAIngotMaterial EGLINSTEELBASE = new GAIngotMaterial(733, "eglin_steel_base", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Iron, 12),
                    new MaterialStack(Kanthal, 3),
                    new MaterialStack(Invar, 15)),
            EXT2_METAL,
            null, 3.0F, 3.0F, 1600 , 16, 102);

    public static final GAIngotMaterial EGLINSTEEL = new GAIngotMaterial(734, "eglin_steel", 0xfcfcfc, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(EGLINSTEELBASE, 10),
                    new MaterialStack(Sulfur, 1),
                    new MaterialStack(Silicon, 4),
                    new MaterialStack(Carbon, 1)),
            EXT3_METAL,
            null, 3.0F, 3.0F, 2800, 15, 200);

    // TODO - trinium and its alloys

    public static final GAIngotMaterial LAFIUM = new GAIngotMaterial(735, "lafium", 0xffffcc, MaterialIconSet.METALLIC, 6,
            ImmutableList.of(new MaterialStack(Naquadah, 4),
                    new MaterialStack(HASTELLOYN, 8),
                    new MaterialStack(Tungsten, 4),
                    new MaterialStack(Aluminium, 6),
                    new MaterialStack(Nickel, 8),
                    new MaterialStack(Carbon, 2)),
            EXT2_METAL,
            null, 7.0F, 5.0F,3200, 9865, 122880, 8400);

    public static final GAIngotMaterial ABYSSAL = new GAIngotMaterial(738, "abyssal", 0x3467ba, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(StainlessSteel, 10),
                    new MaterialStack(TungstenCarbide, 10),
                    new MaterialStack(Nichrome, 10),
                    new MaterialStack(Bronze, 10),
                    new MaterialStack(INCOLOYMA956, 10)),
            EXT2_METAL,
            null, 11.0F, 11.0F, 3000, 13765, 1966080, 10800);

// TODO - quantum blast furnace temp
    public static final GAIngotMaterial QUANTUM = new GAIngotMaterial(741, "quantum", 0xfffffb, MaterialIconSet.METALLIC, 9,
            ImmutableList.of(new MaterialStack(STELLITE, 15),
                    new MaterialStack(SILICONCARBIDE, 5),
                    new MaterialStack(Gallium, 5),
                    new MaterialStack(Americium, 5),
                    new MaterialStack(Palladium, 5),
                    new MaterialStack(Bismuth, 5)),
            EXT2_METAL,
            null, 19.0F, 19.0F, 25000, 7864320, 12000);
    /*
    public static final IngotMaterial CINOBYTE = new IngotMaterial(736, "cinobyte", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(ZERON100, 16),
                    new MaterialStack(Naquadria, 7),
                    new MaterialStack(Gadolinium, 5),
                    new MaterialStack(Aluminium, 3),
                    //new MaterialStack(Mercury, 2),
                    new MaterialStack(Tin, 2),
                    new MaterialStack(Titanium, 12),
                    new MaterialStack(Osmiridium, 6)),
            EXT2_METAL,
            null, 8.0F, 7.0F, 3800, 12565);


    public static final IngotMaterial PIKYONIUM64B = new IngotMaterial(737, "pikyonium64b", 0x3467ba, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(INCONEL792, 16),
                    new MaterialStack(EGLINSTEEL, 10),
                    new MaterialStack(NaquadahEnriched, 8),
                    new MaterialStack(Cerium, 6),
                    new MaterialStack(Antimony, 4),
                    new MaterialStack(Platinum, 4),
                    new MaterialStack(Ytterbium, 2),
                    new MaterialStack(TungstenSteel, 8)),
            EXT2_METAL,
            null, 8.0F, 8.0F, 4000, 11765);
    public static final IngotMaterial BLACKTITANIUM = new IngotMaterial(739, "black_titanium", 0x00000f, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Titanium, 55),
                    new MaterialStack(Lanthanum, 12),
                    new MaterialStack(Tungsten, 8),
                    new MaterialStack(Cobalt, 6),
                    new MaterialStack(Manganese, 4),
                    new MaterialStack(Phosphorus, 4),
                    new MaterialStack(Palladium, 4),
                    new MaterialStack(Niobium, 2)),
            EXT2_METAL,
            null, 9.0F, 10.0F, 4000);

    public static final IngotMaterial BABBIT = new IngotMaterial(740, "babbit", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Tin, 10),
                    new MaterialStack(Lead, 72),
                    new MaterialStack(Antimony, 16),
                    new MaterialStack(Arsenic, 2)),
                    EXT2_METAL,
                    null, 5.0F, 4.0F, 589);
 */

}
