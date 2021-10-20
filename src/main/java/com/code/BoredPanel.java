package com.code;

import com.code.Sound.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

@Slf4j
@Singleton
public class BoredPanel extends PluginPanel {
    public static int xp;
    public static JButton button, wikiButton, completed;
    public JTextField text;
    public JCheckBox pking, Anything, pvming, skilling, prif;
    public static String Chekker, url, skillTask;
    static String[] pvmTasks, pvmg, skilT;
    public JTextArea explain;
    public static JTextArea datatext;
    private BufferedImage img = ImageUtil.loadImageResource(getClass(), "/pix.png");
    public JLabel Image = new JLabel(new ImageIcon(img));
    private Random random = new Random();
    private int index;

    @Inject
    Client client;

    @Inject
    BoredConfig config;

    @Inject
    SoundEngine soundEngine;


    @Inject
    CheckCombat checkCombat;

    @Inject
    public BoredPanel() throws IOException {
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setBorder(new EmptyBorder(8, 8, 8, 8));

        prif = new JCheckBox("Prifddinas unlocked?");
        pvming = new JCheckBox("Pvm");
        pking = new JCheckBox("Pking");
        Anything = new JCheckBox("Everything");
        skilling = new JCheckBox("Skilling");
        explain = new JTextArea("If this plugin does not work \nplease log in :).");
        datatext = new JTextArea("Points: 0");
        completed = new JButton("Task completed? Click here!");
        wikiButton = new JButton("Wiki");
        UserHandle.start();


        text = new JTextField("BoredGenerator");
        prif.setBounds(0, 50, 100, 30);
        prif.setBackground(Color.gray);
        pvming.setBounds(10, 50, 100, 30);
        pvming.setBackground(Color.gray);
        skilling.setBounds(20, 50, 100, 30);
        skilling.setBackground(Color.gray);
        Anything.setBounds(0, 80, 200, 30);
        Anything.setBackground(Color.gray);
        pking.setBounds(10, 80, 200, 30);
        pking.setBackground(Color.gray);

        Image.setMaximumSize(new Dimension(400, 400));

        wikiButton.setForeground(Color.orange);

        datatext.setEditable(false);
        datatext.setBackground(Color.gray);

        explain.setEditable(false);
        explain.setBackground(Color.gray);

        text.setEditable(false);
        text.setFont(new Font("Verdana", Font.BOLD, 15));
        text.setBackground(Color.gray);

        button = new JButton("Generate");
        button.setForeground(Color.WHITE);
        button.setBackground(ColorScheme.DARKER_GRAY_COLOR);

        completed.setForeground(Color.GREEN);
        completed.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        completed.setVisible(false);

        button.addActionListener(e -> {
            setItUp();
        });
        completed.addActionListener(e -> {
            if (config.CengineerC()) {
                soundEngine.playClip(Sound.COM);
            }
            try {
                UserHandle.handle();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            remove(Image);
            text.setText("Completed!");
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/pix.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText("You have completed your task!\n" +
                    "feel free to generate a new one!");
            add(Image);
        });
        wikiButton.addActionListener(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        add(datatext);
        add(prif);
        add(Anything);
        add(pking);
        add(skilling);
        add(pvming);
        add(text, BorderLayout.NORTH);
        add(explain);
        add(Image);
        add(completed);
        add(wikiButton);
        add(button, BorderLayout.SOUTH);
    }


    private void setItUp() {
        completed.setVisible(true);
        Random random = new Random();
        boolean whatCb = checkCombat.checkCombat();
        String[] medium_lvl_pvm = {
                "Barrows", "Zulrah", "Crazy archaeologist", "Venenatis", "Deranged archaeologist",
                "Mole", "Bryophyta", "Obor", "King Black Dragon"
        };
        String[] high_lvl_pvm = {
                "Corrupted Gauntlet", "Gauntlet", "Bandos", "Armadyl", "Zamorak", "Saradomin",
                "Zulrah", "Vorkath", "Tob", "Cox", "Nightmare", "Phosani's nightmare", "Corporeal Beast",
                "Callisto", "Vet'ion", "Barrows"
        };
        String[] skilling_Activities = {
                "Agility", "Herblore", "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining",
                "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Cooking"
        };
        String[] skillPvm = {
                "Corrupted Gauntlet", "Gauntlet", "Agility", "Herblore", "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining",
                "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Cooking", "Bandos", "Armadyl", "Zamorak", "Saradomin",
                "Zulrah", "Vorkath", "Tob", "Cox", "Nightmare", "Phosani's nightmare", "Corporeal Beast",
                "Callisto", "Vet'ion", "Barrows"
        };
        String[] skillPvmLow = {
                "Agility", "Herblore", "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining",
                "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Cooking","Barrows", "Zulrah", "Crazy archaeologist", "Venenatis", "Deranged archaeologist",
                "Mole", "Bryophyta", "Obor", "King Black Dragon"
        };
        String[] skillLowPvmPk = {
                "Agility", "Herblore", "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining",
                "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Cooking","Barrows", "Zulrah", "Crazy archaeologist", "Venenatis", "Deranged archaeologist",
                "Mole", "Bryophyta", "Obor", "King Black Dragon","Venging", "Nh", "Hybrid"
        };
        String[] skillPk = {
                "Agility", "Herblore", "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining",
                "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Cooking","Venging", "Nh", "Hybrid"
        };
        String[] pk = {
                "Venging", "Nh", "Hybrid"
        };
        String[] Everything = {
                "Corrupted Gauntlet", "Gauntlet", "Venging", "Nh", "Hybrid", "Agility", "Farming", "Herblore",
                "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining", "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting", "Bandos", "Armadyl", "Zamorak", "Saradomin",
                "Zulrah", "Vorkath", "Tob", "Cox", "Nightmare", "Phosani's nightmare", "Corporeal Beast",
                "Callisto", "Vet'ion", "Barrows"
        };
        String[] everythingLow = {
                "Venging", "Nh", "Hybrid", "Agility", "Farming", "Herblore",
                "Crafting", "Fishing",
                "Fletching", "Construction", "Hunter", "Mining", "Prayer", "Runecrafting", "Firemaking", "Smithing",
                "Thieving", "Woodcutting","Barrows", "Zulrah", "Crazy archaeologist", "Venenatis", "Deranged archaeologist",
                "Mole", "Bryophyta", "Obor", "King Black Dragon"
        };

        int randint;

            if (pvming.isSelected()&&!whatCb&& skilling.isSelected()&&pking.isSelected()) {

                randint = random.nextInt(skillLowPvmPk.length);
                Chekker = skillLowPvmPk[randint];
                check();
                text.setText(skillLowPvmPk[randint]);
            } else if (skilling.isSelected()&&pking.isSelected()) {

            randint = random.nextInt(skillPk.length);
            Chekker = skillPk[randint];
            check();
            text.setText(skillPk[randint]);
            } else if (Anything.isSelected()&&!whatCb) {

                randint = random.nextInt(everythingLow.length);
                Chekker = everythingLow[randint];
                check();
                text.setText(everythingLow[randint]);
            }
            else if (pvming.isSelected()&&!whatCb&& skilling.isSelected()) {

                randint = random.nextInt(skillPvmLow.length);
                Chekker = skillPvmLow[randint];
                check();
                text.setText(skillPvmLow[randint]);
            }
            else if (pvming.isSelected() && skilling.isSelected() && prif.isSelected()) {

                randint = random.nextInt(skillPvm.length);
                Chekker = skillPvm[randint];
                check();
                text.setText(skillPvm[randint]);
            } else if (Anything.isSelected() && prif.isSelected()) {

                randint = random.nextInt(Everything.length);
                Chekker = Everything[randint];
                check();
                text.setText(Everything[randint]);
            }
            else if (pvming.isSelected()&&!whatCb) {

                randint = random.nextInt(medium_lvl_pvm.length);
                Chekker = medium_lvl_pvm[randint];
                check();
                text.setText(medium_lvl_pvm[randint]);
            }
            else if (pvming.isSelected() && prif.isSelected()) {

                randint = random.nextInt(high_lvl_pvm.length);
                Chekker = high_lvl_pvm[randint];
                check();
                text.setText(high_lvl_pvm[randint]);
            }  else if (pvming.isSelected() && skilling.isSelected()) {

                randint = (int) Math.floor(Math.random() * (skillPvm.length - 1 - 2 + 1) + 2);
                Chekker = skillPvm[randint];
                check();
                text.setText(skillPvm[randint]);
            } else if (pvming.isSelected()) {

                randint = (int) Math.floor(Math.random() * (high_lvl_pvm.length - 1 - 2 + 1) + 2);
                Chekker = high_lvl_pvm[randint];
                check();
                text.setText(high_lvl_pvm[randint]);
            } else if (skilling.isSelected()) {

                randint = random.nextInt(skilling_Activities.length);
                Chekker = skilling_Activities[randint];
                check();
                text.setText(skilling_Activities[randint]);
            } else if (pking.isSelected()) {

                randint = random.nextInt(pk.length);
                Chekker = pk[randint];
                check();
                text.setText(pk[randint]);
            } else if (Anything.isSelected()) {

                randint = (int) Math.floor(Math.random() * (Everything.length - 1 - 2 + 1) + 2);
                Chekker = Everything[randint];
                check();
                text.setText(Everything[randint]);
            } else {
                text.setText("Thats not valid.");
            }

    }

    private void check() {


        skillCheckTask();

        pvmTasks = new String[]{
                "Kill " + Chekker + " " + (int) Math.floor(Math.random() * (10 - 5 + 1) + 5) + " times",
                "Get any unique from \n" + Chekker + "!",
                "Kill " + Chekker + " with gear \nworth under " +
                        (int) Math.floor(Math.random() * (50 - 15 + 1) + 15)
                        + "M"
        };
        pvmg = new String[]{
                "Kill " + Chekker + " " +
                        (int) Math.floor(Math.random() * (10 - 5 + 1) + 5) + " times",
                "Get any unique from " + Chekker + "!",
                "Kill " + Chekker + " in under \n6 minutes"
        };
        remove(wikiButton);
        remove(Image);
    }
    private void checkThatShit() {
        index = random.nextInt(3);
        skills();
        pk();
        skillingCheck();
        pvmCheck();

        add(Image);
        add(wikiButton);
        add(button, BorderLayout.SOUTH);
    }

    public void skillCheckTask() {
        if (Chekker.equals("Fishing")) {
            xp = client.getSkillExperience(Skill.FISHING);
            skillTask = SkillCheck.fishing();
            skilT = new String[]{
                    "Fish " + (int) Math.floor(Math.random() * (200 - 100 + 1) + 100) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Prayer")) {
            xp = client.getSkillExperience(Skill.PRAYER);
            String[] altar = {"Gilded Alatar", "Chaos Altar"};
            Random random = new Random();
            int randint = random.nextInt(2);
            skillTask = SkillCheck.prayer();
            skilT = new String[]{
                    "Sacrifice " + (int) Math.floor(Math.random() * (300 - 200 + 1) + 200) + " " + skillTask + "\nto the " + altar[randint] + "!"
            };
        } else if (Chekker.equals("Runecrafting")) {
            xp = client.getSkillExperience(Skill.RUNECRAFT);
            skillTask = SkillCheck.runecrafting();
            if (skillTask.equals("Ourania Altar")) {
                skilT = new String[]{
                        "Craft " + (int) Math.floor(Math.random() * (800 - 600 + 1) + 600) + " runes at the " + skillTask + "!"
                };
            } else {
                skilT = new String[]{
                        "Craft " + (int) Math.floor(Math.random() * (800 - 600 + 1) + 600) + " " + skillTask + "!"
                };
            }
        } else if (Chekker.equals("Smithing")) {
            xp = client.getSkillExperience(Skill.SMITHING);
            skillTask = SkillCheck.smithing();
            if (skillTask.equals("Just do the knights sword quest cmonn!")) {
                skilT = new String[]{
                        "Just do the knights sword quest cmonn!"
                };
            } else {
                skilT = new String[]{
                        "Smith " + (int) Math.floor(Math.random() * (600 - 400 + 1) + 400) + " " + skillTask + "!"
                };
            }
        } else if (Chekker.equals("Mining")) {
            xp = client.getSkillExperience(Skill.MINING);
            skillTask = SkillCheck.mining();
            if (skillTask.equals("Motherlode Mine")) {
                skilT = new String[]{
                        "Mine " + (int) Math.floor(Math.random() * (200 - 120 + 1) + 120) + " pay dirt\nin the " + skillTask + "!"
                };
            } else if(skillTask.equals("Volcanic Mine")) {
                skilT = new String[]{
                        "Do " + (int) Math.floor(Math.random() * (20 - 10 + 1) + 10) + " " + skillTask + "rounds!"
                };
            } else {
                skilT = new String[]{
                        "Mine " + (int) Math.floor(Math.random() * (200 - 120 + 1) + 120) + " " + skillTask + "!"
                };
            }
        } else if (Chekker.equals("Agility")) {
            xp = client.getSkillExperience(Skill.AGILITY);
            skillTask = SkillCheck.agility();
            skilT = new String[]{
                    "Complete " + (int) Math.floor(Math.random() * (50 - 15 + 1) + 15) + " laps of the\n" + skillTask + "!"
            };
        } else if (Chekker.equals("Hunter")) {
            xp = client.getSkillExperience(Skill.HUNTER);
            skillTask = SkillCheck.hunter();
            skilT = new String[]{
                    "Hunt " + (int) Math.floor(Math.random() * (300 - 200 + 1) + 200) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Construction")) {
            xp = client.getSkillExperience(Skill.CONSTRUCTION);
            skillTask = SkillCheck.construction();
            skilT = new String[]{
                    "Build " + (int) Math.floor(Math.random() * (200 - 100 + 1) + 100) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Herblore")) {
            xp = client.getSkillExperience(Skill.HERBLORE);
            skillTask = SkillCheck.herblore();
            skilT = new String[]{
                    "Make " + (int) Math.floor(Math.random() * (600 - 300 + 1) + 500) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Woodcutting")) {
            xp = client.getSkillExperience(Skill.WOODCUTTING);
            skillTask = SkillCheck.woodcutting();
            skilT = new String[]{
                    "Chop " + (int) Math.floor(Math.random() * (200 - 100 + 1) + 100) + " " + skillTask + " logs!"
            };
        } else if (Chekker.equals("Crafting")) {
            xp = client.getSkillExperience(Skill.CRAFTING);
            skillTask = SkillCheck.crafting();
            skilT = new String[]{
                    "Craft " + (int) Math.floor(Math.random() * (200 - 100 + 1) + 100) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Cooking")) {
            xp = client.getSkillExperience(Skill.COOKING);
            skillTask = SkillCheck.cooking();
            skilT = new String[]{
                    "Cook " + (int) Math.floor(Math.random() * (300 - 100 + 1) + 100) + " " + skillTask + "!"
            };
        } else if (Chekker.equals("Thieving")) {
            xp = client.getSkillExperience(Skill.THIEVING);
            skillTask = SkillCheck.theiving();
            skilT = new String[]{
                    "Thief " + skillTask + " " + (int) Math.floor(Math.random() * (200 - 100 + 1) + 200) + " times!"
            };
        } else if (Chekker.equals("Fletching")) {
            xp = client.getSkillExperience(Skill.FLETCHING);
            skillTask = SkillCheck.fletching();
            skilT = new String[]{
                    "Fletch " + skillTask + " " + (int) Math.floor(Math.random() * (300 - 200 + 1) + 200) + " times!"
            };
        } else if (Chekker.equals("Firemaking")) {
            xp = client.getSkillExperience(Skill.FIREMAKING);
            skillTask = SkillCheck.firemaking();
            if (skillTask.equals("Wintertodt")) {
                skilT = new String[]{
                        "Do " + (int) Math.floor(Math.random() * (25 - 10 + 1) + 10) + " " + skillTask + " rounds!"
                };
            } else {
                skilT = new String[]{
                        "Burn " + (int) Math.floor(Math.random() * (200 - 50 + 1) + 50) + " " + skillTask + " logs!"
                };
            }
        }
    }
    private void skills() {
        if (Chekker.equals("Agility")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Agility.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Agility";
        }  else if (Chekker.equals("Runecrafting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Runecraft.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Runecraft";
        } else if (Chekker.equals("Smithing")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Smithing.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Smithing";
        } else if (Chekker.equals("Prayer")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Prayer.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Prayer";
        } else if (Chekker.equals("Mining")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Mining.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Mining";
        } else if (Chekker.equals("Hunter")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Hunter.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Hunter";
        } else if (Chekker.equals("Construction")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Construction.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Construction";
        } else if (Chekker.equals("Cooking")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Cooking.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Cooking";
        } else if (Chekker.equals("Herblore")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Herblore.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Herblore";
        } else if (Chekker.equals("Fletching")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Fletching.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Fletching";
        } else if (Chekker.equals("Fishing")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Fishing.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Fishing";

        } else if (Chekker.equals("Woodcutting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Woodcutting.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Woodcutting";

        } else if (Chekker.equals("Thieving")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Thieving.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Thieving";
        } else if (Chekker.equals("Firemaking")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Firemaking.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Firemaking";
        } else if (Chekker.equals("Crafting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Crafting.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Crafting";
        }
    }
    private void pk() {

        if (Chekker.equals("Venging")) {

            String pkwep = RandPk.randveng();

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Venging.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText("Kill 1 Player using a \n" + pkwep + "\nas a last hit in  Vengance pking!");
            url = "https://oldschool.runescape.wiki/w/Player_killing";
        } else if (Chekker.equals("Hybrid")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Hybrid.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText("Kill " + (int) Math.floor(Math.random() * (10 - 5 + 1) + 5) + " Players using Hybrid!");
            url = "https://oldschool.runescape.wiki/w/Player_killing";
        }
        else if (Chekker.equals("Nh")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Tribrid.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText("Kill " + (int) Math.floor(Math.random() * (10 - 5 + 1) + 5) + " Players using Tribrid!");
            url = "https://oldschool.runescape.wiki/w/Player_killing";
        }
    }
    public void skillingCheck() {
        if (Chekker.equals("Agility")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Agility.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Agility";
        }
        else if (Chekker.equals("Runecrafting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Runecraft.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Runecraft";
        } else if (Chekker.equals("Smithing")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Smithing.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Smithing";
        } else if (Chekker.equals("Prayer")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Prayer.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Prayer";
        } else if (Chekker.equals("Mining")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Mining.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Mining";
        } else if (Chekker.equals("Hunter")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Hunter.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Hunter";
        } else if (Chekker.equals("Construction")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Construction.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Construction";
        } else if (Chekker.equals("Cooking")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Cooking.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Cooking";
        } else if (Chekker.equals("Herblore")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Herblore.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Herblore";
        } else if (Chekker.equals("Fletching")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Fletching.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Fletching";
        } else if (Chekker.equals("Fishing")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Fishing.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Fishing";

        } else if (Chekker.equals("Woodcutting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Woodcutting.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Woodcutting";

        } else if (Chekker.equals("Thieving")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Thieving.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Thieving";
        } else if (Chekker.equals("Firemaking")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Firemaking.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Firemaking";
        } else if (Chekker.equals("Crafting")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Crafting.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(skilT[0]);
            url = "https://oldschool.runescape.wiki/w/Crafting";
        } else if (Chekker.equals("Corrupted Gauntlet")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/CG.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmg[index]);
            url = "https://oldschool.runescape.wiki/w/The_Gauntlet#Corrupted_Gauntlet";
        }
    }
    private void pvmCheck() {
        if(Chekker.equals("Crazy archaeologist")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Crazy archaeologist.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Crazy_archaeologist";
        } else if(Chekker.equals("King Black Dragon")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/King Black Dragon.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/King_Black_Dragon";
        } else if(Chekker.equals("Obor")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Obor.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Obor";
        } else if(Chekker.equals("Venenatis")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Venenatis.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Venenatis";
        } else if(Chekker.equals("Deranged archaeologist")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Deranged archeologist.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Deranged_archaeologist";
        } else if(Chekker.equals("Mole")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Mole.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Giant_Mole";
        } else if(Chekker.equals("Bryophyta")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Bryophyta.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Bryophyta";
        }
        else if (Chekker.equals(("Gauntlet"))) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/G.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmg[index]);
            url = "https://oldschool.runescape.wiki/w/The_Gauntlet";
        } else if (Chekker.equals(("Corrupted Gauntlet"))) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/CG.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmg[index]);
            url = "https://oldschool.runescape.wiki/w/The_Gauntlet#Corrupted_Gauntlet";
        } else if (Chekker.equals("Bandos")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/General.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/General_Graardor";
        } else if (Chekker.equals("Armadyl")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Kree.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Kree%27arra";
        } else if (Chekker.equals("Zamorak")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Kril.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/K%27ril_Tsutsaroth";
        } else if (Chekker.equals("Saradomin")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Zilyana.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Commander_Zilyana";
        } else if (Chekker.equals("Zulrah")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Zulrah.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Zulrah";
        } else if (Chekker.equals("Vorkath")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Vorkath.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Vorkath";
        } else if (Chekker.equals("Tob")) {

            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Tob.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Theatre_of_Blood";
        } else if (Chekker.equals("Cox")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Cox.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Chambers_of_Xeric";
        } else if (Chekker.equals("Nightmare")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Nightmare.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/The_Nightmare";
        } else if (Chekker.equals("Phosani's nightmare")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/PNightmare.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Phosani%27s_Nightmare";
        } else if (Chekker.equals("Corporeal Beast")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Corp.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Corporeal_Beast";
        } else if (Chekker.equals("Callisto")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Callisto.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Callisto";
        } else if (Chekker.equals("Vet'ion")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Vet.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Callisto";
        } else if (Chekker.equals("Barrows")) {
            img = ImageUtil.loadImageResource(BoredPlugin.class, "/Barrows.png");
            Image = new JLabel(new ImageIcon(img));
            explain.setText(pvmTasks[index]);
            url = "https://oldschool.runescape.wiki/w/Barrows";
        }
    }


}
