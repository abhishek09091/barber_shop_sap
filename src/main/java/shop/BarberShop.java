package shop;

public class BarberShop{
	
	private int consumedChairs;
	private Integer capacity;
	private static BarberShop barbershop = null;
	
	// Making the constuctor private because there is only one barber shop available
	private BarberShop(Integer capacity) {
		this.capacity = capacity;
		this.consumedChairs = 0;
	}
	
	//Double locking so that double instance will not get intialized of the barbershop
	public static BarberShop getInstance() {
		
		if(barbershop == null) {
			
			synchronized (BarberShop.class) {
				
				if(barbershop == null) {
					barbershop = new BarberShop(5);
				}
			}
		}
		
		return barbershop;
	}
	
	// As we are using the counting semaphores we have to synchronize the both the functions so that no more
	// customers not added
	public synchronized int add() throws InterruptedException{
		
		while(consumedChairs == capacity) {
			System.out.println("Capacity reached to max waiting");
			wait();
		}
		notify();
		consumedChairs += 1;
		return consumedChairs;
		
	}
	
	// Method to pick up one person to do the hair cut
	public synchronized int remove() throws InterruptedException {
		
		while(consumedChairs == 0) {
			System.out.println("Barber went on sleep");
			wait();
		}
		consumedChairs -= 1;
		notify();
		return consumedChairs;
	}
	
	// To get the consumedChairs
	public int getConsumedChairs() {
		return consumedChairs;
	}
	
	// Maximum capacity of the chairs
	public int getMaxCapacity() {
		return capacity;
	}
}
