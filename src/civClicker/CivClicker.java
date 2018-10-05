// Basic clicker game inspired by other clicker games and strategy games such as Civilization
// A user collects resources, builds up their kingdom, and then can fight other nations for land
// Written by Ben Torgerson

// Future tasks: Upgrades, Achievements, more buildings

package civClicker;

import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CivClicker extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	Player player = new Player();
    	
    	
        primaryStage.setTitle("Ben's Clicker Game");
        GridPane resourcesGrid = new GridPane();
        resourcesGrid.setHgap(10);
        resourcesGrid.setVgap(10);
        resourcesGrid.setPadding(new Insets(100,0,0,350));
        
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
    	HBox mainTitle = new HBox(8);
    	Label mainTitleLabel = new Label("Welcome to Ben's Clicker Game! Your kingdom currently has a population of: 100");
    	mainTitle.setPadding(new Insets(100,0,0,125));
    	mainTitleLabel.setFont(new Font("Arial", 20));
    	mainTitle.getChildren().addAll(mainTitleLabel);
    	
    
    
        // Resources + Buildings Tab
        Label foodCount = new Label("Current Food: 0");
        Label woodCount = new Label("Current Wood: 0");
        Label stoneCount = new Label("Current Stone: 0");
        Label farmCount = new Label("Current Farms: 1");
        Label campCount = new Label("Current Camps: 1");
        Label mineCount = new Label("Current Mines: 1");
        Label houseCount = new Label("Current Houses: 0");
        Label barracksCount = new Label("Current Barracks: 0");
        
        Button farm = new Button("Gather Food");
        Button chop = new Button("Chop down wood");
        Button mine = new Button("Mine some stone");
        Button buildFarm = new Button("Build a Farm");
        Button buildCamp = new Button("Build a Lumber Camp");
        Button buildMine = new Button("Build a Stone Mine");
        Button buildHouse = new Button("Build a house");
        Button buildBarracks = new Button("Build a barracks");
        
        Label houseCost = new Label("Costs 10 Food and 10 Wood, increases Population by 5");
        Label farmCost = new Label("Costs 10 Wood, Increases Food Income");
        Label campCost = new Label("Costs 10 Wood and 10 Stone, Increases Wood Income");
        Label mineCost = new Label("Costs 10 Wood and 10 Stone, Increases Stone Income");
        Label barracksCost = new Label("Costs 100 Wood. Increases Military by 5");
        
        resourcesGrid.add(farm,  0,  0);
        resourcesGrid.add(chop, 0, 1);
        resourcesGrid.add(mine, 0, 2);
        resourcesGrid.add(buildFarm, 0, 3);
        resourcesGrid.add(buildCamp, 0, 4);
        resourcesGrid.add(buildMine, 0, 5);
        resourcesGrid.add(buildHouse, 0, 6);
        resourcesGrid.add(buildBarracks, 0, 7);
        
        resourcesGrid.add(foodCount, 1, 0);
        resourcesGrid.add(woodCount, 1, 1);
        resourcesGrid.add(stoneCount, 1, 2);
        resourcesGrid.add(farmCount, 1, 3);
        resourcesGrid.add(campCount, 1, 4);
        resourcesGrid.add(mineCount, 1, 5);
        resourcesGrid.add(houseCount, 1, 6);
        resourcesGrid.add(barracksCount, 1, 7);
        
        resourcesGrid.add(farmCost, 2, 3);
        resourcesGrid.add(campCost, 2, 4);
        resourcesGrid.add(mineCost, 2, 5);
        resourcesGrid.add(houseCost, 2, 6);
        resourcesGrid.add(barracksCost, 2, 7);
        
        
        farm.setOnAction (value -> {
        	player.addFood(1);
        	updateLabel(player, foodCount, "Food");
        });
        
        chop.setOnAction (value -> {
        	player.addWood(1);
        	updateLabel(player, woodCount, "Wood");
        });
        
        mine.setOnAction (value -> {
        	player.addStone(1);
        	updateLabel(player, stoneCount, "Stone");
        });
        
        buildHouse.setOnAction (value -> {
        	if (player.build("House")) {
        		player.addPopulation(5);
        		updateLabel(player, houseCount, "House");
        		updateLabel(player, woodCount, "Wood");
        		updateLabel(player, foodCount, "Food");
        		updateLabel(player, mainTitleLabel, "Population");
        	}
        });
        
        buildFarm.setOnAction (value -> {
        	if (player.build("Farm")) {
        		updateLabel(player, farmCount, "Farm");
        		updateLabel(player, woodCount, "Wood");
        	}
        });
        
        buildCamp.setOnAction (value -> {
        	if (player.build("Camp")) {
        		updateLabel(player, campCount, "Camp");
        		updateLabel(player, woodCount, "Wood");
        		updateLabel(player, stoneCount, "Stone");
        	}
        	
        });
        
        buildMine.setOnAction (value -> {
        	if (player.build("Mine")) {
        		updateLabel(player, mineCount, "Mine");
        		updateLabel(player, woodCount, "Wood");
        		updateLabel(player, stoneCount, "Stone");
        	}
        });

        
      
        GridPane firstTab = new GridPane();
        firstTab.setHgap(10);
        firstTab.setVgap(10);
        firstTab.add(mainTitle, 0, 0);
        firstTab.add(resourcesGrid, 0, 1);
        mainTitleLabel.setFont(new Font("Arial", 20));
        

        
        
        
        
        
        
        
        // Military Tab!
        
        GridPane factionsGrid = new GridPane();
        factionsGrid.setPadding(new Insets(100,0,0,400));
        
        
        
        Faction firstFaction = new Faction();
        Faction secondFaction = new Faction();
        Faction thirdFaction = new Faction();
        Faction fourthFaction = new Faction();
        
        Label playerFactionLabel = new Label("Your Control: ");
        Label firstFactionLabel = new Label("Faction 1: ");
        Label secondFactionLabel = new Label("Faction 2: ");
        Label thirdFactionLabel = new Label("Faction 3: ");
        Label fourthFactionLabel = new Label("Faction 4: ");
        
        Label playerStrength = new Label("20%");
        Label firstFactionStrength = new Label("20%");
        Label secondFactionStrength = new Label("20%");
        Label thirdFactionStrength = new Label("20%");
        Label fourthFactionStrength = new Label("20%");
        
        playerFactionLabel.setFont(Font.font("Arial", 18));
        firstFactionLabel.setFont(Font.font("Arial", 18));
        secondFactionLabel.setFont(Font.font("Arial", 18));
        thirdFactionLabel.setFont(Font.font("Arial", 18));
        fourthFactionLabel.setFont(Font.font("Arial", 18));
        playerStrength.setFont(Font.font("Arial", 18));
        firstFactionStrength.setFont(Font.font("Arial", 18));
        secondFactionStrength.setFont(Font.font("Arial", 18));
        thirdFactionStrength.setFont(Font.font("Arial", 18));
        fourthFactionStrength.setFont(Font.font("Arial", 18));
        
        Button attackFirst = new Button("Attack");
        Button attackSecond = new Button("Attack");
        Button attackThird = new Button("Attack");
        Button attackFourth = new Button("Attack");
        
        HBox militaryTitle = new HBox();
        Label militaryLabel = new Label("Welcome to your military page. Here you can choose to attack neighboring factions. Your current Military count is: 0");
        militaryLabel.setPadding(new Insets(100,0,0,50));
        militaryTitle.getChildren().addAll(militaryLabel);
        militaryLabel.setFont(Font.font("Arial", 17));
        
        HBox resultsBox = new HBox();
        Label results = new Label("");
        results.setPadding(new Insets(100,0,0,50));
        resultsBox.getChildren().addAll(results);
        results.setFont(Font.font("Arial", 17));
        
        factionsGrid.add(playerFactionLabel, 0, 0);
        factionsGrid.add(firstFactionLabel, 0, 1);
        factionsGrid.add(secondFactionLabel, 0, 2);
        factionsGrid.add(thirdFactionLabel, 0, 3);
        factionsGrid.add(fourthFactionLabel, 0, 4);
        factionsGrid.add(playerStrength, 1, 0);
        factionsGrid.add(firstFactionStrength, 1, 1);
        factionsGrid.add(secondFactionStrength, 1, 2);
        factionsGrid.add(thirdFactionStrength, 1, 3);
        factionsGrid.add(fourthFactionStrength, 1, 4);
        factionsGrid.add(attackFirst, 2, 1);
        factionsGrid.add(attackSecond, 2, 2);
        factionsGrid.add(attackThird, 2, 3);
        factionsGrid.add(attackFourth, 2, 4);
        
        attackFirst.setOnAction (value -> {
        	int attackResults = firstFaction.attack(player);
        	updateLabel(player, militaryLabel, "Military");
        	if (attackResults == 0) {
        		postAttackResults(player, results, "Defeat", firstFaction, attackResults);
        		updateFactions(player, playerStrength, firstFaction, firstFactionStrength);
        	}
        	else {
        		postAttackResults(player, results, "Victory", firstFaction, attackResults);
        		updateFactions(player, playerStrength, firstFaction, firstFactionStrength);
        	}
        });
        attackSecond.setOnAction (value -> {
        	int attackResults = secondFaction.attack(player);
        	updateLabel(player, militaryLabel, "Military");
        	if (attackResults == 0) {
        		postAttackResults(player, results, "Defeat", secondFaction, attackResults);
        		updateFactions(player, playerStrength, secondFaction, secondFactionStrength);
        	}
        	else {
        		postAttackResults(player, results, "Victory", secondFaction, attackResults);
        		updateFactions(player, playerStrength, secondFaction, secondFactionStrength);
        	}
        });
        attackThird.setOnAction (value -> {
        	int attackResults = thirdFaction.attack(player);
        	updateLabel(player, militaryLabel, "Military");
        	if (attackResults == 0) {
        		postAttackResults(player, results, "Defeat", thirdFaction, attackResults);
        		updateFactions(player, playerStrength, thirdFaction, thirdFactionStrength);
        	}
        	else {
        		postAttackResults(player, results, "Victory", thirdFaction, attackResults);
        		updateFactions(player, playerStrength, thirdFaction, thirdFactionStrength);
        	}
        });
        attackFourth.setOnAction (value -> {
        	int attackResults = fourthFaction.attack(player);
        	updateLabel(player, militaryLabel, "Military");
        	if (attackResults == 0) {
        		postAttackResults(player, results, "Defeat", fourthFaction, attackResults);
        		updateFactions(player, playerStrength, fourthFaction, fourthFactionStrength);
        	}
        	else {
        		postAttackResults(player, results, "Victory", fourthFaction, attackResults);
        		updateFactions(player, playerStrength, fourthFaction, fourthFactionStrength);
        	}
        });
        
        GridPane thirdTab = new GridPane();
        thirdTab.add(militaryLabel,0,0);
        thirdTab.add(factionsGrid, 0, 1);
        thirdTab.add(results, 0, 2);
        
        
        
        //Help page
        
        GridPane helpGrid = new GridPane();
        helpGrid.setPadding(new Insets(300,0,0,140));
        
        Label help0 = new Label("Thank you for playing my game!");
        Label help1 = new Label("The purpose of this game is to create a great Kingdom, and to take over the world.");
        Label help2 = new Label("This game is inspired by clicker games and strategy games such as Civilization.");
        
        GridPane.setHalignment(help0,  HPos.CENTER);
        GridPane.setHalignment(help1,  HPos.CENTER);
        GridPane.setHalignment(help2,  HPos.CENTER);
        
        help0.setFont(new Font("Arial", 20));
        help1.setFont(new Font("Arial", 20));
        help2.setFont(new Font("Arial", 20));
        
        helpGrid.add(help0, 0, 0);
        helpGrid.add(help1, 0, 1);
        helpGrid.add(help2, 0, 2);
        
        
    	Tab resourcesTab = new Tab();
    	resourcesTab.setText("Resources");
    	resourcesTab.setContent(firstTab);
    	
    	Tab upgradesTab = new Tab();
    	upgradesTab.setText("Upgrades (todo)");
    	
    	Tab factionsTab = new Tab();
    	factionsTab.setText("Factions");
    	factionsTab.setContent(thirdTab);
    	
    	Tab achievementsTab = new Tab();
    	achievementsTab.setText("Achievements (todo)");
    	
    	Tab helpTab = new Tab();
    	helpTab.setText("Help/Credits");
    	helpTab.setContent(helpGrid);
    	
    	tabPane.getTabs().add(resourcesTab);
    	tabPane.getTabs().add(upgradesTab);
    	tabPane.getTabs().add(factionsTab);
    	tabPane.getTabs().add(achievementsTab);
    	tabPane.getTabs().add(helpTab);
        Scene scene = new Scene(tabPane, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        buildBarracks.setOnAction (value -> {
        	if (player.build("Barracks")) {
        		player.setMilitary(player.getMilitary() + 5);
        		updateLabel(player, militaryLabel, "Military");
        		updateLabel(player, barracksCount, "Barracks");
        		updateLabel(player, woodCount, "Wood");
        	}
        });
    }
    
    // Given a label and the resource, it updates that label with the new, correct amount
    public static void updateLabel(Player player, Label label, String resource) {
    	if (resource.equals("Food")) {
    		label.setText("Current " + resource + ": " + player.getFood());
    	}
    	if (resource.equals("Stone")) {
    		label.setText("Current " + resource + ": " + player.getStone());
    	}
    	if (resource.equals("Wood")) {
    		label.setText("Current " + resource + ": " + player.getWood());
    	}
    	if (resource.equals("House")) {
    		label.setText("Current " + resource + ": " + player.getHouses());
    	}
    	if (resource.equals("Population")) {
    		label.setText("Welcome to Ben's Clicker Game! Your kingdom currently has a population of: " + player.getPopulation());
    	}
    	if (resource.equals("Military")) {
    		label.setText("Welcome to your military page. Here you can choose to attack neighboring factions. Your current Military count is: " + player.getMilitary());
    	}
    	if (resource.equals("Barracks")) {
    		label.setText("Current Barracks: " + player.getBarracks());
    	}
    	if (resource.equals("Farm")) {
    		label.setText("Current Farms: " + player.getFarms());
    	}
    	if (resource.equals("Camp")) {
    		label.setText("Current Camps: " + player.getCamps());
    	}
    	if (resource.equals("Mine")) {
    		label.setText("Current Mines: " + player.getMines());
    	}
    }
    
    // Takes in a defeat or victory for the player and updates their respective control %s 
    // Currently it is just a random number. A player or faction can lose all of their land in one attack if they are unlucky
    public static void postAttackResults(Player player, Label playerLabel, String result, Faction faction, int numOfTroops) {
    	if (result.equals("Defeat")) {
    		int lostArea = ThreadLocalRandom.current().nextInt(0, player.getControlPct() + 1);
    		String lostAreaStr = Integer.toString(lostArea);
    		playerLabel.setText("Unfortunately your troops have been defeated. You have lost all of your troops, and have lost " + lostAreaStr + " percentage of your area.");
    		int currentArea = player.getControlPct();
    		if (player.subControlPct(lostArea)) {
    			faction.addControlPct(lostArea);
    		}
    		else {
    			faction.addControlPct(currentArea);
    		}
    		
    	}
    	else {
    		int wonArea = ThreadLocalRandom.current().nextInt(1, faction.getControlPct() + 1);
    		playerLabel.setText("You have won! You have " + Integer.toString(numOfTroops) + " troops left. You took over " + Integer.toString(wonArea) + " % of land.");
    		int currentArea = faction.getControlPct();
    		if (faction.subControlPct(faction.getControlPct() - wonArea)) {
    			player.addControlPct(player.getControlPct() + wonArea);
    		}
    		else {
    			player.addControlPct(currentArea);
    		}
    	}
    }
    
    // Takes in a player, and a faction and updates their %s on the factions page
    public static void updateFactions(Player player, Label playerStrength, Faction faction, Label factionStrength) {
    	playerStrength.setText(Integer.toString(player.getControlPct()) + "%");
    	factionStrength.setText(Integer.toString(faction.getControlPct()) + "%");
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
}