package com.code;

import java.util.Random;

public class RandPk {
    private static int xp = 1;
    private static String pkwep;


    public static String randveng() {
        Random random = new Random();
        String[] pkWeapons = {
                "Dragon halbert","Granite maul","Rod of ivandis","Dragon harpoon","Ancient crozier",
                "Steel mace","Granite longsword","Mithril crossbow","Steel crossbow","Black dagger",
                "Steel knife","Pearl fishing rod","Adamant spear","Staff of earth","Adamant longsword",
                "Armadyl godsword","Granite maul","Whip","Magic staff","Wooden spoon","Rune knife","Adamant hasta",
                "Dragon pickaxe","Leaf bladed sword","Leaf Bladed axe","Beginner wand"
        };
        pkwep = pkWeapons[random.nextInt(pkWeapons.length)];
        return pkwep;
    };

}
