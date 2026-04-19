package descent;

import descent.item.*;
import java.util.List;
import java.util.ArrayList;

public class Inventory {
	
	private List<Item> items;
	private List<Equipment> equipped = new ArrayList<>();

	public Inventory() {
		this.items = new ArrayList<>();
	}

	public void addItem(Item item) {
		items.add(item);
		Console.println(Console.GREEN, item.getName() + " added to inventory.");
	}

	public void addItem(Item item, int amount) {
		int i = amount;
		while (i > 0) {
			items.add(item);
			i--;
		}
	}

	public void removeItem(Item item) {
		items.remove(item);
		Console.println(Console.WHITE, item.getName() + " removed from inventory.");

	}

	public boolean useItem(int index, Player player) {
		if (index < 0 || index >= items.size()) {
			Console.println(Console.RED, "Invalid item");
			return false;
		}

		Item item = items.get(index);
		
		if (item instanceof Consumable) {
			item.use(player);
			items.remove(index);
		}
		else if (item instanceof Equipment) {
			
			Equipment eq = (Equipment) item;

		    if (isEquipped(eq)) {
		        eq.unequip(player);
		        removeEquipped(eq);
		        Console.println(Console.YELLOW, eq.getName() + " unequipped.");
		    } else {
		        eq.equip(player);
		        equipped.add(eq);
		        Console.println(Console.GREEN, eq.getName() + " equipped.");
		    }
		}

		return true;

	}

	public void displayInventory() {
		if (items.isEmpty()) {
			Console.println(Console.YELLOW, "Your inventory is empty.");
			return;
		}

		System.out.println(Console.BOLD_WHITE + "\n==== INVENTORY ====" + Console.RESET);

		ArrayList<String> countedNames = new ArrayList<>();
		int index = 1;

		for (Item current : items) {

			// skip if already counted
			if (countedNames.contains(current.getName().toLowerCase())) {
				continue;
			}

			// duplicate count
			int count = 0;
			for (Item item : items) {
				if (item.getName().equalsIgnoreCase(current.getName())) {
					count++;
				}
			}

			countedNames.add(current.getName().toLowerCase());

			String rarityColour = getRarityColour(current.getRarity());

			String displayName = current.getName();
			if (count > 1) {
				displayName += " x" + count;
			}

			System.out.printf("%s%-3s%s %-20s %s%-12s%s | %s%s%s%n", Console.BOLD_WHITE, (index++) + ".", Console.RESET,
					displayName, rarityColour, "[" + current.getRarity() + "]", Console.RESET, Console.ITALIC,
					current.getDescription(), Console.RESET);
		}

		System.out.println(Console.WHITE + "===================" + Console.RESET);
	}
	
	private String getRarityColour(Item.Rarity rarity) {
		switch (rarity) {
			case CONSUMABLE:	return Console.WHITE;
			case COMMON:    	return Console.WHITE;
			case UNCOMMON:  	return Console.GREEN;
			case RARE:      	return Console.BLUE;
			case LEGENDARY: 	return Console.BOLD_YELLOW;
			default:        	return Console.RESET;
		}
	}
	
	private boolean isEquipped(Equipment item) {
	    for (Equipment e : equipped) {
	        if (e.getName().equalsIgnoreCase(item.getName())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private void removeEquipped(Equipment item) {
	    for (int i = 0; i < equipped.size(); i++) {
	        if (equipped.get(i).getName().equalsIgnoreCase(item.getName())) {
	            equipped.remove(i);
	            return;
	        }
	    }
	}
	
	
	 public int getNumberOfItems(Item item) {
		 int i = 0;
		 for (Item n: items) {
			 if (n.getName().equalsIgnoreCase(item.getName())) {
				 i++;
			 }
		 }
		 return i;
	 }
	 public List<Item> getItems() { return items; }
	 public boolean isEmpty() { return items.isEmpty(); }
	 public int getSize() { return items.size(); }

}
