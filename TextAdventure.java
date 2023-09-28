import java.util.*;

public class TextAdventure 
{
  FancyConsole console;
  Scanner inScanner;
  Player ourHero;
  int gold;
  int health;
  int maxHealth = 100;
  int attack;
  int monstersDefeated;

  public TextAdventure()
  {
    console = new FancyConsole("Great Text Adventure!", 600, 600);
    inScanner = new Scanner(System.in);

    // feel free to change the player's starting values
    ourHero = new Player("Bob", 100, 0);
  }

  public void play()
  {
    String input;
    // start of adventure. You can change this if you like
    console.setImage("heyYou.jpg");
    health = 100;
    // ask the user for their name.
    System.out.println("Hey, you. You're finally awake.\nWhat is your name, adventurer: ");
    input = inScanner.nextLine();

    // Change ourHero's name
    // ADD CODE HERE
    ourHero.changeName(input);
    // describe the starting situation. Feel free to change this
    System.out.println("Nice to meet you, " + ourHero.getName() + ". We are heading to the town of Betun. Where will you be heading off to next? \n\ncity: You can go to the shop there \nforest: There will be easy monsters to defeat there \ncave: Something might be in there \nhouse: Check your stats or sleep");
    // get user input and go to the appropriate zone based on their input
    // ADD CODE HERE
    System.out.println("(city/forest/cave/house): ");
    input = inScanner.nextLine();
    if(input.equals("city")){
        enterZone1();
    } else if(input.equals("forest")){
        enterZone2();
    } else if(input.equals("cave")){
        enterZone3();
    } else if(input.equals("house")){
        enterZone4();
    } else{
        System.out.println("That location does not exist. Please try again");
        play();
    }
  }

  private void enterZone1()
  {
    // change image
    // ADD CODE HERE
    console.setImage("city.jpg");
    // describe the area/situation to the user.
    System.out.println();
    System.out.println("You arrive at the city. The city streets are crowded with people and merchants. Up above on a hill, you see a majestic castle where the royal family resides. You eventually find the weapons shop.");
    // Give them options for choices.
    // ADD CODE HERE
    System.out.println("Would you like to enter\n(yes/no): ");
    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    String temp = inScanner.nextLine();
    if(temp.equals("yes")){
        enterWeaponShop();
    } else{
        enterTown();
    }
  }

  private void enterZone2()
  {
    // change image
    // ADD CODE HERE
    console.setImage("forest.jpg");
    // describe the area/situation to the user.
    System.out.println();
    System.out.println("You arrive at the forest. The forest is shrouded in fog. You hope to find some monsters that you can kill.");
    // Give them options for choices.
    // ADD CODE HERE
    System.out.println("A monster appears!");
    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    String temp;
    int monster = 20;
    int damage;
    int mDamage;
    while(monster > 0 && !(health <= 0)){
        System.out.println("What do you want to do?\n(fight/run): ");
        temp = inScanner.nextLine();
        if(temp.equals("fight")){
            damage = (int)((Math.random()*11)+attack);
            monster = monster - damage;
            System.out.println("You attacked the monster for " + damage + " damage! The monster is now at " + monster + " hp!");
        } else if(temp.equals("run")){
            System.out.println("You ran away!");
            enterTown();
        } else{
            System.out.println("Invalid option!");
            enterZone2();
        }
        if(monster <= 0){
            break;
        }
        mDamage = (int)(Math.random()*6);
        System.out.println("The monster attacks you for " + mDamage + " damage!");
        health = ourHero.getHealth() - mDamage;
        ourHero.setHealth(health);
        System.out.println("You are at " +  ourHero.getHealth() + " hp!");
        if(health <= 0){
            gameEnd();
        }
    }
    if(!(health <= 0)){
       System.out.println("Great job! You defeated the monster! (Gold +5)");
        ourHero.defeatMonster();
        ourHero.setGold(gold + 5);
        System.out.println("You return to town");
        enterTown(); 
    }
  }

  private void enterZone3()
  {
    // change image
    // ADD CODE HERE
    console.setImage("cave.jpg");
    // describe the area/situation to the user.
    System.out.println();
    System.out.println("You arrive at the cave. You notice that the cave is guarded by a powerful monster. What could it be guarding?");
    // Give them options for choices.
    // ADD CODE HERE
    System.out.println("Do you want to enter the cave?\n(yes/no): ");
    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    String temp;
    temp = inScanner.nextLine();
    if(temp.equals("yes")){
        System.out.println("You prepare to fight the monster");
        int monster = 100;
        int damage;
        int mDamage;
        while(monster > 0 && !(health <= 0)){
            System.out.println("What do you want to do?\n(fight/run): ");
            temp = inScanner.nextLine();
            if(temp.equals("fight")){
                damage = (int)((Math.random()*11)+attack);
                monster = monster-damage;
                System.out.println("You attacked the monster for " + damage + " damage! The monster is now at " + monster + " hp!");
            } else if(temp.equals("run")){
                System.out.println("You cannot run away!");
            } else{
                System.out.println("Invalid option!");
            }
            if(monster <= 0){
                break;
            }
            mDamage = (int)(Math.random()*11);
            health = ourHero.getHealth() - mDamage;
            ourHero.setHealth(health);
            System.out.println("The monster attacks you for " + mDamage + " damage!");
            System.out.println("You are at " + ourHero.getHealth() + " hp!");
            if(health <= 0){
                gameEnd();
            }
        }
    } else if(temp.equals("no")){
        System.out.println("You return to town");
        enterTown();
    } else{
        System.out.println("Invalid option!");
        enterZone3();
    }
    if(!(health <= 0)){
        System.out.println("Great job! You defeated the monster! (Gold +10)");
        ourHero.defeatMonster();
        ourHero.setGold(gold + 10);
        console.setImage("treasure.jpg");
        System.out.println();
        System.out.println("You wander through the cave in search of treasure when you find a treasure chest!");
        System.out.println("Do you want to open the treasure chest?\n(yes/no): ");
    }
    temp = inScanner.nextLine();
    if(temp.equals("yes")){
        System.out.println("You open the chess...");
        System.out.println("You find 50 gold!");
        System.out.println("You return back to town");
        enterTown();
    }else{
       System.out.println("You return back to town");
       enterTown(); 
    }
  }

  private void enterZone4()
  {
    // change image
    // ADD CODE HERE
    console.setImage("house.png");
    // describe the area/situation to the user.
    System.out.println();
    System.out.println("You go inside the house. You feel the warmth from fireplace soon as you step inside. Sitting on the bed, you can hear the kids playing outside. What do you want to do?");
    // Give them options for choices.
    // ADD CODE HERE
    System.out.println("name: your name \nchange name: you can change your name \nhealth: the amount of health you have \ngold: the amount of gold you have \nmonsters defeated: the amount of monsters you defeated \nattack: how strong you are \nsleep: heal your health \nleave: return to town\n");
    // Take action or go to another zone based on their choice
    // ADD CODE HERE
    System.out.println("(name/change name/ health/gold/monsters defeated/attack/sleep/leave): ");
    String temp = inScanner.nextLine();
    if(temp.equals("name")){
        System.out.println("Your name is: " + ourHero.getName());
        enterZone4();
    } else if(temp.equals("change name")){
        System.out.println("What is your new name?\n");
        String temp2 = inScanner.nextLine();
        ourHero.changeName(temp2);
        System.out.println("Successfully changed your name!");
        enterZone4();
    } else if(temp.equals("health")){
        System.out.println("Your health is at: " + ourHero.getHealth());
        enterZone4();
    } else if(temp.equals("gold")){
        System.out.println("You have " + ourHero.getGold() + " gold");
        enterZone4();
    } else if(temp.equals("monsters defeated")){
        System.out.println("You defeated " + ourHero.getMonstersDefeated() + " monsters");
        enterZone4();
    } else if(temp.equals("attack")){
        System.out.println("Your strength is at: +" + ourHero.getAttack());
        enterZone4();
    } else if(temp.equals("sleep")){
        ourHero.setHealth(maxHealth);
        System.out.println("Good Morning! You have successfully slept through the night and are fully healed!"); 
        enterZone4();
    } else if(temp.equals("leave")){
        enterTown();
    }else{
        System.out.println("This command does not exist. Please try again");
        enterZone4();
    }
  }

  private void enterTown()
  {
    console.setImage("betun.jpg");
    
    System.out.println();
    System.out.println("Welcome back to Betun, " + ourHero.getName() + ". Where will you be heading off to now? \n\ncity: You can go to the shop there \nforest: There will be easy monsters to defeat there \ncave: Powerful monsters guard a cave \nhouse:  Check your stats or sleep\n");
    System.out.println("(city/forest/cave/house): ");
    
    String temp = inScanner.nextLine();
    if(temp.equals("city")){
        enterZone1();
    } else if(temp.equals("forest")){
        enterZone2();
    } else if(temp.equals("cave")){
        enterZone3();
    } else if(temp.equals("house")){
        enterZone4();
    } else{
        System.out.println("That location does not exist. Please try again");
        enterTown();
    }
  }
  
  private void enterWeaponShop()
  {
    console.setImage("weaponshop.jpg");
    
    System.out.println();
    System.out.println("Welcome to the weapons shop. Would you like to purchase some of the finest weapons and armor around?\n(yes/no): ");
    
    String temp = inScanner.nextLine();
    if(temp.equals("yes")){
        System.out.println("Would you like to purchase a sword or armor \nsword: 50 gold \narmor: 50 gold \n(sword/armor): ");
        String choice = inScanner.nextLine();
        if(choice.equals("sword")){
            if(gold >= 50){
                System.out.println("You purchased a sword! (Attack: +10)");
                ourHero.setGold(gold - 50);
                ourHero.setAttack(attack + 10);
            } else{
                System.out.println("You do not have enough gold!");
                enterZone1();
            }
        } else if(choice.equals("armor")){
            if(gold >= 50){
                System.out.println("You purchased armor! (Defence: +10)");
                ourHero.setGold(gold - 50);
                maxHealth = maxHealth + 10;
            } else{
                System.out.println("You do not have enough gold!");
                enterZone1();
            }
        } else{
            System.out.println("Invalid option");
            enterWeaponShop();
        }
    }
  }

  private void gameEnd()
  {
    // ADD CODE HERE
    console.setImage("afterlife.jpg");
    System.out.println();
    System.out.println("You have died! Better luck next time!");
  }
}