public class Item {
    private String name;        
    private int quantity;     
    
    public Item() {
    	
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    //add the item's quantity 
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    @Override
    public String toString() {
        return String.format("%d %s", quantity, name);
    }
}
