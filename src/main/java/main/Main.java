package main;

import java.util.Scanner;

import shop.BarberShop;

/*****
 * 
 * A barber shop consists of a waiting room with n chairs, and a barber chair for giving haircuts.
 * 
 * If there are no customers to be served, the barber goes to sleep.
 *
 * If a customer enters the barber shop and all chairs are occupied, 
 * then the customer leaves the shop.
 *
 * If the barber is busy, but chairs are available, 
 * then the customer sits in one of the free chairs. 
 * If the barber is asleep, the customer wakes up the barber.
 *
 * Write a program to coordinate the interaction between the barber and the customers.
 * 
 * 
 * vinay.jagannath@sap.com
 * 
 * ******/

public class Main {

	public static void main(String[] args) throws InterruptedException{
		
		//Initialize the capacity of the barber shop
		int capacity = 5;
		//Creating the object of the barber shop with initial capacity
		final BarberShop barber = BarberShop.getInstance();
		
		//Consumer thread keeps on consuming
		Thread consumer = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						barber.remove();
						System.out.println("Hair cutting done");
						// Giving 5 seconds to barber to do the hair cut
						System.out.println("Chairs remaining after this hair cut: " + barber.getConsumedChairs());
						Thread.sleep(5000);
					}catch(InterruptedException ex) {
						System.out.println("Error happened");
					}
				}
			}
		});
		
		//Producer thread keeps on producing the data
		Thread producer = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					
					try {
						 Scanner sc = new Scanner(System.in);
						 System.out.println("Enter the id only integer value please");
						 Integer id = sc.nextInt();
						 barber.add();
						 System.out.println("Total chairs occupied: " + barber.getConsumedChairs());
						
					}catch(InterruptedException ex) {
						System.out.println("Something bad happened");
					}
				}
			}
		});
		producer.start();
		consumer.start();
		consumer.join();
		producer.join();
	}	

}
