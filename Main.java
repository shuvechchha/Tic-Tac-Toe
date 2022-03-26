/*
The Main class should handle running the point of sale system for the pizza restaurant.

You should allow the user to input multiple orders each with multiple pizzas. Pizzas can be either traditional or deep dish, and have a variety of toppings. I suggest you have the user enter a string like "pm", where each letter stands for a topping, e.g. "pm" would be pepperoni and mushrooms.

Before you beginning, you add 4 toppings to your Restaurant's toppings ArrayList (using the Restaurant method addTopping). Remember Restaurant doesn't need to be instantiated, as there is only one, e.g. use Restaurant.addTopping("pepperoni", "1.29"). There are 4 toppings available. "pepperoni" and "extra cheese" are 1.29, "green peppers" and "mushrooms" are 0.99. 

I recommend creating multiple static methods in this Main class for specific purposes. One might handle reading in a single pizza. One might handle reading in an order of pizzas (and in that process calling the previous method). Break up the task like that.

After the user is done inputting all of their orders, the program should print out all of the orders (using the Restaurant's printOrders method) and the price of the priciest order (found using the Restaurant's priciestOrder method).

If you are working in a group (max size 3), add an extra feature of your choice for each person in the group beyond the first. An extra feature might be a new type of pizza or the ability to find the average price of a pizza.  
*/

import java.util.*;

class Main {

  public static void main(String[] args) {
    Restaurant.addTopping(new Topping("pepperoni", 1.29));
    Restaurant.addTopping(new Topping("extra cheese", 1.29));
    Restaurant.addTopping(new Topping("green peppers", 0.99));
    Restaurant.addTopping(new Topping("mushrooms", 0.99));
    Restaurant.addTopping(new Topping("olives", 0.65));
    Restaurant.addOrder(pizzaOrder());
    Restaurant.printOrders();
    Restaurant.priciestOrder();
    Restaurant.averagePrice();
  }


  /*This takes care of a single order.*/
  private static Order pizzaOrder(){
    Scanner input = new Scanner(System.in);
    Order order = new Order();
    System.out.println("Number of pizza orders: ");

    int numOfPizzas = input.nextInt();

    for(int i = 0; i<numOfPizzas; i++){
      System.out.println("\nPizza " + (i + 1) + " Order: \n");
      int userinput = 0;
      String pizzaType = ""; 
      while(true){      
        System.out.println("Please enter 1 for Traditional pizza, 2 for Deep Dish or 3 for House Special");
        userinput = input.nextInt();

        if(userinput == 1){
          pizzaType = "Traditional";
          break;
        }else if(userinput == 2){
          pizzaType = "DeepDish";
          break;
        } else if (userinput ==3){
          pizzaType = "HouseSpecial";
          break;
        }else{
          System.out.println("Please enter a valid choice");
        }
      }

      Pizza pizza;
      if(pizzaType.equals("Traditional")){
        pizza = new TraditionalPizza();
      } else if(pizzaType.equals("HouseSpecial")){
        pizza = new HouseSpecial();
      }else{
        pizza = new DeepDishPizza();
      }
      
      System.out.println("\nChoose toppings: Enter 'p' for pepperoni, 'e' for extra cheese, 'g' for green peppers, 'm' for mushroom or 'o' for olives. \n\n Enter a string like pm, where each character stands for a topping, e.g. pm would be pepperoni and mushrooms: ");
      
      String toppingsStr = input.next();

      for(int j = 0; j<toppingsStr.length(); j++){
        char ch = toppingsStr.charAt(j);
        if(ch == 'p'){
          pizza.addTopping(Restaurant.getTopping("pepperoni"));
        }else if(ch == 'e'){
          pizza.addTopping(Restaurant.getTopping("extra cheese"));        
        }else if(ch == 'o'){
          pizza.addTopping(Restaurant.getTopping("olives")); 
        }else if(ch == 'g'){
          pizza.addTopping(Restaurant.getTopping("green peppers"));
        }else if(ch == 'm'){
          pizza.addTopping(Restaurant.getTopping("mushrooms"));
        }
      }
      order.addPizza(pizza);
    }
    input.close();
    return order;
  }

}

