package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.behaviours.RandomSelector;
import game.grounds.Blight;
import game.grounds.CampFire;
import game.grounds.Floor;
import game.grounds.Soil;
import game.grounds.TeleportCircle;
import game.grounds.Wall;
import game.items.BloodroseSeed;
import game.items.InheritreeSeed;
import game.items.Medicine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The main class to setup and run the game.
 * @author Adrian Kristanto
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Blight(),
                new Wall(), new Floor(), new Soil());

        List<String> map = Arrays.asList(
                "xxxx...xxxxxxxxxxxxxxxxxxxxxxx........xx",
                "xxx.....xxxxxxx..xxxxxxxxxxxxx.........x",
                "..........xxxx....xxxxxxxxxxxxxx.......x",
                "....xxx...........xxxxxxxxxxxxxxx.....xx",
                "...xxxxx...........xxxxxxxxxxxxxx.....xx",
                "...xxxxxxxxxx.......xxxxxxxx...xx......x",
                "....xxxxxxxxxx........xxxxxx...xxx......",
                "....xxxxxxxxxxx.........xxx....xxxx.....",
                "....xxxxxxxxxxx................xxxx.....",
                "...xxxx...xxxxxx.....#####.....xxx......",
                "...xxx....xxxxxxx....#___#.....xx.......",
                "..xxxx...xxxxxxxxx...#___#....xx........",
                "xxxxx...xxxxxxxxxx...##_##...xxx.......x",
                "xxxxx..xxxxxxxxxxx.........xxxxx......xx",
                "xxxxx..xxxxxxxxxxxx.......xxxxxx......xx");
                
        List<String> limveldMap = Arrays.asList(
                ".............xxxx",
                "..............xxx",
                "................x",
                ".................",
                "................x",
                "...............xx",
                "..............xxx",
                "..............xxx",
                "..............xxx",
                ".............xxxx",
                ".............xxxx",
                "....xxx.....xxxxx",
                "....xxxx...xxxxxx");
        //add in limeveld 
        GameMap limveld = new GameMap("Limveld", groundFactory, limveldMap);
        world.addGameMap(limveld);

        GameMap gameMap = new GameMap("Valley of the Inheritree", groundFactory, map);
        world.addGameMap(gameMap);

        // BEHOLD, ELDEN THING!
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Farmer", '@', 100);
        world.addPlayer(player, gameMap.at(23, 10));

        // Teleport circles and their destinations
        Location valleyTeleport = gameMap.at(22, 9);
        Location limveldTeleport = limveld.at(3, 0);

        ArrayList<Location> valleyTeleportTargets = new ArrayList<>();
        ArrayList<Location> limveldTeleportTargets = new ArrayList<>();

        //add campfire
        Location campfireLocationLimveld = limveld.at(3,1);
        Location campfireLocation = gameMap.at(24, 8);
        campfireLocation.setGround(new CampFire());
        campfireLocationLimveld.setGround(new CampFire());

        valleyTeleportTargets.add(limveldTeleport);
        limveldTeleportTargets.add(valleyTeleport);

        valleyTeleport.setGround(new TeleportCircle(valleyTeleportTargets));
        limveldTeleport.setGround(new TeleportCircle(limveldTeleportTargets));

        // Game Setup
        gameMap.at(24, 11).addItem(new Medicine());
        gameMap.at(14,11).addActor(new OmenSheep());
        limveld.at(12,4).addActor(new OmenSheep(new RandomSelector()));
        limveld.at(14,5).addActor(new SpiritGoat(new RandomSelector()));
        gameMap.at(23, 14).addActor(new GoldenBeetle());
        gameMap.at(25,14).addActor(new PlantingAssistant(3));
        gameMap.at(22,10).addActor(new BankingAssistant(0.1));
        gameMap.at(14,12).addActor(new HealingAssistant());

        gameMap.at(24,12).addActor(new Guts());
        gameMap.at(24,9).addActor(new Sellen());
        gameMap.at(24,10).addActor(new Kale());
        limveld.at(8, 6).addActor(new BedOfChaos());


        player.addItemToInventory(new InheritreeSeed());
        player.addItemToInventory(new BloodroseSeed());
        player.addBalance(100);
        world.run();
    }
}
