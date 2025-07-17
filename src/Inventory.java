import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> items;

    public Inventory() {
    	items = new ArrayList<>();
    }
    
    public void addItem(Item item) {
        for (Item existingItem : items) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.addQuantity(item.getQuantity());
                return;
            }
        }
        items.add(item);
    }
    
    public void reduceQuantity(String itemName) {
    	for(Item item : items) {
    		if (item.getName().equals(itemName)) {
    			int itemQty = item.getQuantity() - 1; 
    			item.setQuantity(itemQty);
    		}
    	}
    }
    
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item; 
            }
        }
        return null; 
    }
    
    public int getItemQuantity(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item.getQuantity();
            }
        }
        return 0; 
    }
    
    public void setItemQuantity(String itemName, int quantity) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                if (quantity <= 0) {
                    items.remove(item);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
        if (quantity > 0) {
            items.add(new Item(itemName, quantity));
        }
    }
    
////    if won
//    public void addReward(Booster reward) {
//    	Item item = new Item(reward.getReward_Type(), reward.getReward_Quantity());
//        addItem(item); 
//    }
    
    public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
    public String toString() {
        return "Inventory: " + items.toString();
    }
}
