import java.util.*;

public class Player
{
  String name;
  int health;
  double gold;
  int monstersDefeated;
  int attack;

  public Player(String playerName, int startingHealth, double startingGold)
  {
      name = playerName;
      health = startingHealth;
      gold = startingGold;
  }

  public String getName()
  {
    return name;
  }

  public void changeName(String newName)
  {
    name = newName;
  }

  public int getHealth()
  {
    return health;
  }

  public void setHealth(int newHealth)
  {
    health = newHealth;
  }

  public double getGold()
  {
    return gold;
  }

  public void setGold(int newAmount)
  {
    gold = newAmount;
  }

  public int getMonstersDefeated()
  {
    return monstersDefeated;
  }

  public void defeatMonster()
  {
    monstersDefeated ++;
  }
  public int getAttack()
  {
      return attack;
  }
  public void setAttack(int newAttack)
  {
      attack = newAttack;
  }
}