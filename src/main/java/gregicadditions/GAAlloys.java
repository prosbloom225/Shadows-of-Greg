package gregicadditions;

import com.google.common.collect.ImmutableList;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.MaterialIconSet;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.stack.MaterialStack;

import static gregtech.api.unification.material.Materials.*;

@IMaterialHandler.RegisterMaterialHandler
public class GAAlloys implements IMaterialHandler {

    @Override
    public void onMaterialsInit() {

    }

    static long STD_METAL = DustMaterial.MatFlags.GENERATE_PLATE;
    static long EXT2_METAL = STD_METAL | SolidMaterial.MatFlags.GENERATE_ROD | IngotMaterial.MatFlags.GENERATE_BOLT_SCREW | SolidMaterial.MatFlags.GENERATE_GEAR | IngotMaterial.MatFlags.GENERATE_FOIL | IngotMaterial.MatFlags.GENERATE_FINE_WIRE;
    static long EXT3_METAL = EXT2_METAL | SolidMaterial.MatFlags.GENERATE_FRAME;
    // GT Alloys



    // GT++ Alloys
    public static final IngotMaterial STABALLOY = new IngotMaterial(704, "staballoy", 0x3c423a, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Titanium, 1),
                    new MaterialStack(Uranium, 9)),
            STD_METAL, null, 7.0F, 3.0F, 1600, 3450);

    public static final IngotMaterial TANTALLOY60 = new IngotMaterial(705, "tantalloy60", 0xd5e7ed, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Tungsten, 4),
                    new MaterialStack(Tantalum, 46)),
            STD_METAL, null, 7.0F, 3.0F, 1600, 3025);

    public static final IngotMaterial TANTALLOY61 = new IngotMaterial(706, "tantalloy61", 0xc1d3d9, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(TANTALLOY60, 4),
                    new MaterialStack(Titanium, 12),
                    new MaterialStack(Yttrium, 8)),
            STD_METAL, null, 7.0F, 3.0F, 1600, 3030);

    public static final IngotMaterial TUMBAGA = new IngotMaterial(707, "tumbaga", 0xffb30f, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Gold, 70),
                    new MaterialStack(Copper, 30)),
            EXT3_METAL, null, 3.0F, 1.0F, 400);

    public static final IngotMaterial POTIN = new IngotMaterial(708, "potin", 0xc99781, MaterialIconSet.METALLIC, 2,
            ImmutableList.of(new MaterialStack(Lead, 40),
                    new MaterialStack(Bronze, 40),
                    new MaterialStack(Tin, 40)),
            STD_METAL, null, 3.0F, 1.0F, 800);

    public static final IngotMaterial INCONEL625 = new IngotMaterial(709, "inconel625", 0x80c880, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Nichrome, 10),
                    new MaterialStack(Nickel, 3),
                    new MaterialStack(Chrome, 7),
                    new MaterialStack(Invar, 10),
                    new MaterialStack(Molybdenum, 10)),
            EXT3_METAL, null, 6.0F, 3.0F, 1800, 3758);

    public static final IngotMaterial INCONEL690  = new IngotMaterial(710, "inconel690", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nichrome, 10),
                    new MaterialStack(Nickel, 3),
                    new MaterialStack(Chrome, 7),
                    new MaterialStack(Invar, 10),
                    new MaterialStack(Molybdenum, 10)),
            STD_METAL, null, 7.0F, 3.5F, 2200, 4895);

    public static final IngotMaterial INCONEL792 = new IngotMaterial(711, "inconel792", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nickel, 20),
                    new MaterialStack(Niobium, 10),
                    new MaterialStack(Aluminium, 20),
                    new MaterialStack(Nichrome, 10)),
            STD_METAL, null, 7.0F, 3.5F, 2200, 6200);

    public static final IngotMaterial NITINOL60 = new IngotMaterial(712, "nitinol60", 0x76dc8a, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Nickel, 40),
                    new MaterialStack(Titanium, 60)),
            STD_METAL, null, 8.0F, 4.0F, 2800, 8975);

    public static final IngotMaterial ZERON100 = new IngotMaterial(713, "zeron100", 0xb4b414, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Chrome, 26),
                    new MaterialStack(Nickel, 6),
                    new MaterialStack(Molybdenum, 6),
                    new MaterialStack(Copper, 20),
                    new MaterialStack(Tungsten, 4),
                    new MaterialStack(Steel, 40)),
            STD_METAL, null, 8.0F, 4.0F, 2900, 9785);

    public static final IngotMaterial MARAGING250 = new IngotMaterial(714, "maraging250", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Molybdenum, 4),
                    new MaterialStack(Titanium, 4),
                    new MaterialStack(Nickel, 16),
                    new MaterialStack(Cobalt, 8)),
            STD_METAL, null, 7.0F, 3.0F, 1800, 4555);

    public static final IngotMaterial MARAGING300 = new IngotMaterial(715, "maraging300", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Titanium, 4),
                    new MaterialStack(Nickel, 16),
                    new MaterialStack(Aluminium, 8),
                    new MaterialStack(Cobalt, 8)),
    STD_METAL, null, 7.0F, 3.0F, 2000, 4555);

    public static final IngotMaterial MARAGING350 = new IngotMaterial(716, "maraging350", 0xfcfcf, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 64),
                    new MaterialStack(Aluminium, 4),
                    new MaterialStack(Molybdenum, 4),
                    new MaterialStack(Nickel, 4),
                    new MaterialStack(Cobalt, 8)),
            STD_METAL, null, 7.0F, 4.0F, 2200, 4555);

    public static final IngotMaterial AQUATIC_STEEL = new IngotMaterial(717, "aquatic_steel", 0x7878b4, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Steel, 60),
                    new MaterialStack(Carbon, 10),
                    new MaterialStack(Manganese, 5),
                    new MaterialStack(Silicon, 10),
                    new MaterialStack(Phosphorus, 5),
                    //new MaterialStack(Sulfur, 5),
                    new MaterialStack(Aluminium, 5)),
    STD_METAL, null, 6.0F, 3.0F, 1800, 4835);

    public static final IngotMaterial STELLITE = new IngotMaterial(718, "stellite", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Cobalt, 35),
                    new MaterialStack(Chrome, 35),
                    new MaterialStack(Manganese, 20),
                    new MaterialStack(Titanium, 10)),
    STD_METAL, null, 5.0F, 3.0F, 1900, 6250);

    public static final IngotMaterial TALONITE = new IngotMaterial(719, "talonite", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Cobalt, 40),
                    new MaterialStack(Chrome, 30),
                    new MaterialStack(Phosphorus, 20),
                    new MaterialStack(Molybdenum, 10)),
                    STD_METAL, null, 5.0F, 5.0F, 1600, 5500);

    public static final IngotMaterial HASTELLOYW= new IngotMaterial(720, "hastelloyw", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 6),
                    new MaterialStack(Cobalt, 24),
                    new MaterialStack(Chrome, 6),
                    new MaterialStack(Nickel, 62)),
            STD_METAL, null, 5.0F, 5.0F, 1600, 5755);

    public static final IngotMaterial HASTELLOYX= new IngotMaterial(721, "hastelloyx", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 18),
                    new MaterialStack(Manganese, 2),
                    new MaterialStack(Silicon, 2),
                    new MaterialStack(Molybdenum, 8),
                    new MaterialStack(Chrome, 22),
                    new MaterialStack(Nickel, 48)),
                    STD_METAL, null, 5.0F, 6.0F, 2800, 5755);

    public static final IngotMaterial HASTELLOYN= new IngotMaterial(722, "hastelloyn", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Yttrium, 8),
                    new MaterialStack(Molybdenum, 16),
                    new MaterialStack(Chrome, 8),
                    new MaterialStack(Titanium, 8),
                    new MaterialStack(Nickel, 60)),
                    STD_METAL, null, 5.0F, 7.0F, 3200, 6875);

    public static final IngotMaterial HASTELLOYC276 = new IngotMaterial(723, "hastelloyc276", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Cobalt, 2),
                    new MaterialStack(Molybdenum, 16),
                    new MaterialStack(Tungsten, 2),
                    new MaterialStack(Copper, 2),
                    new MaterialStack(Chrome, 14),
                    new MaterialStack(Nickel, 64)),
                    STD_METAL, null, 5.0F, 5.0F, 2600, 6250);

    public static final IngotMaterial INCOLOY020 = new IngotMaterial(724, "incoloy020", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 40),
                    new MaterialStack(Copper, 4),
                    new MaterialStack(Chrome, 20),
                    new MaterialStack(Nickel, 36)),
            STD_METAL, null, 3.0F, 3.0F, 2200, 5420);

    public static final IngotMaterial INCOLOYDS = new IngotMaterial(725, "incoloyds", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 40),
                    new MaterialStack(Cobalt, 18),
                    new MaterialStack(Chrome, 18),
                    new MaterialStack(Nickel, 18)),
            STD_METAL, null, 3.0F, 3.0F, 2400, 5420);

    public static final IngotMaterial INCOLOYMA956 = new IngotMaterial(726, "incoloyma956", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(Iron, 64),
                    new MaterialStack(Aluminium, 12),
                    new MaterialStack(Chrome, 20),
                    new MaterialStack(Yttrium, 4)),
            STD_METAL, null, 3.0F, 3.0F, 2400, 6875);

    public static final IngotMaterial TUNGSTENTITANIUMCARBIDE = new IngotMaterial(727, "tungsten_titanium_carbide", 0xfcfcfc, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(TungstenCarbide, 70),
                    new MaterialStack(Titanium, 30)),
                    STD_METAL, null, 5.0F, 5.0F, 3300);

    public static final IngotMaterial SILICONCARBIDE = new IngotMaterial(728, "silicon_carbide", 0x283024, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Silicon, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 3.0F, 3.0F, 2200);

    public static final IngotMaterial TANTALUMCARBIDE = new IngotMaterial(729, "tantalum_carbide", 0x8b8878, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Tantalum, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 4.0F, 4.0F, 2600);

    public static final IngotMaterial ZIRCONIUMCARBIDE = new IngotMaterial(730, "zirconium_carbide", 0xdecab4, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Diamond, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 5.0F, 5.0F, 1800);

    public static final IngotMaterial NIOBIUMCARBIDE = new IngotMaterial(731, "niobium_carbide", 0xdecab4, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(Niobium, 50),
                    new MaterialStack(Carbon, 50)),
            STD_METAL, null, 4.0F, 4.0F, 2200);

    public static final IngotMaterial GRISIUM = new IngotMaterial(732, "grisium", 0x294953, MaterialIconSet.METALLIC, 2,
            ImmutableList.of(new MaterialStack(Titanium, 9),
                    new MaterialStack(Carbon, 9),
                    new MaterialStack(Lithium, 9),
                    new MaterialStack(Sulfur, 9),
                    new MaterialStack(Potassium, 9)),
            EXT3_METAL,
            null, 8.0F, 3.0F, 1280, 4125);
    public static final IngotMaterial EGLINSTEELBASE = new IngotMaterial(733, "eglin_steel_base", 0xfcfcfc, MaterialIconSet.METALLIC, 3,
            ImmutableList.of(new MaterialStack(Iron, 12),
                    new MaterialStack(Kanthal, 3),
                    new MaterialStack(Invar, 15)),
            EXT2_METAL,
            null, 3.0F, 3.0F, 1600 );

    public static final IngotMaterial EGLINSTEEL = new IngotMaterial(734, "eglin_steel", 0xfcfcfc, MaterialIconSet.METALLIC, 4,
            ImmutableList.of(new MaterialStack(EGLINSTEELBASE, 10),
                    new MaterialStack(Sulfur, 1),
                    new MaterialStack(Silicon, 4),
                    new MaterialStack(Carbon, 1)),
            EXT2_METAL,
            null, 3.0F, 3.0F, 2800);

    // TODO - trinium and its alloys

    public static final IngotMaterial LAFIUM = new IngotMaterial(735, "lafium", 0xffffcc, MaterialIconSet.METALLIC, 6,
            ImmutableList.of(new MaterialStack(Naquadah, 4),
                    new MaterialStack(HASTELLOYN, 8),
                    new MaterialStack(Tungsten, 4),
                    new MaterialStack(Aluminium, 6),
                    new MaterialStack(Nickel, 8),
                    new MaterialStack(Carbon, 2)),
            EXT2_METAL,
            null, 7.0F, 5.0F,3200, 9865);

    public static final IngotMaterial ABYSSAL = new IngotMaterial(738, "abyssal", 0x3467ba, MaterialIconSet.METALLIC, 5,
            ImmutableList.of(new MaterialStack(StainlessSteel, 10),
                    new MaterialStack(TungstenCarbide, 10),
                    new MaterialStack(Nichrome, 10),
                    new MaterialStack(Bronze, 10),
                    new MaterialStack(INCOLOYMA956, 10)),
            EXT2_METAL,
            null, 11.0F, 11.0F, 3000, 13765);

    public static final IngotMaterial QUANTUM = new IngotMaterial(741, "quantum", 0xfffffb, MaterialIconSet.METALLIC, 9,
            ImmutableList.of(new MaterialStack(STELLITE, 15),
                    new MaterialStack(SILICONCARBIDE, 5),
                    new MaterialStack(Gallium, 5),
                    new MaterialStack(Americium, 5),
                    new MaterialStack(Palladium, 5),
                    new MaterialStack(Bismuth, 5)),
            EXT2_METAL,
            null, 19.0F, 19.0F, 25000);
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
