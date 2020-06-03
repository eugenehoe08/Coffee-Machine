package machine;

public class Machine {
    private State state;
    private int water;
    private int milk;
    private int coffee;
    private int cups;
    private int money;

    // Creating a coffee machine
    public Machine(int water, int milk, int coffee, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
        this.money = money;
        setMainState();
    }

    public void actOnState(String input) {
        switch (state) {
            case MAIN:
                setState(input);
                break;
            case BUYING:
                buyCoffee(input);
                setMainState();
                break;
            case FILLING_WATER:
                System.out.println("Write how many ml of water do you want to add:");
                water += Integer.parseInt(input);
                state = State.FILLING_MILK;
                break;
            case FILLING_MILK:
                System.out.println("Write how many ml of milk do you want to add:");
                milk += Integer.parseInt(input);
                state = State.FILLING_COFFEE;
                break;
            case FILLING_COFFEE:
                System.out.println("Write how many grams of coffee beans do you want to add:");
                coffee += Integer.parseInt(input);
                state = State.FILLING_CUPS;
                break;
            case FILLING_CUPS:
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                cups += Integer.parseInt(input);
                setMainState();
                break;
            default:
                break;
        }
    }

    // Setting state of coffee machine
    public void setState(String input) {
        switch (input) {
            case "buy":
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu");
                state = State.BUYING;
                break;
            case "fill":
                state = State.FILLING_WATER;
                break;
            case "take":
                giveMoney();
                break;
            case "remaining":
                printState();
                setMainState();
                break;
            case "exit":
                state = State.OFF;
                break;
            default:
                System.out.println("Invalid action. Please try again.");
                setMainState();
        }
    }

    // Deciding if machine is on
    public boolean isWorking() {
        return state != State.OFF;
    }

    // When machine is turned on
    private void setMainState() {
        state = State.MAIN;
        System.out.println("Write action(buy, fill, take, remaining, exit):");
    }

    // Current state of coffee machine
    private void printState() {
        System.out.printf("The coffee machine has:\n%d of water\n%d of milk\n", water, milk);
        System.out.printf("%d of coffee beans\n%d of disposable cups\n%d of money\n", coffee, cups, money);
    }

    // When buying coffee

    private void buyCoffee(String input) {
        Beverage beverage = null;

        switch (input) {
            case "back":
                state = State.MAIN;
                break;
            case "1":
                beverage = Beverage.ESPRESSO;
                break;
            case "2":
                beverage = Beverage.LATTE;
                break;
            case "3":
                beverage = Beverage.CAPPUCCINO;
                break;
            default:
                System.out.println("Please input a valid option");
                break;
        }
        if (beverage != null) {
            makeCoffee(beverage);
            takeMoney(beverage.getPrice());
        }
    }

    // Making coffee
    private void makeCoffee(Beverage beverage) {
        if (water < beverage.getWater()) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < beverage.getMilk()) {
            System.out.println("Sorry, not enough milk");
        } else if (coffee < beverage.getCoffee()) {
            System.out.println("Sorry, not enough coffee!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= beverage.getWater();
            milk -= beverage.getMilk();
            coffee -= beverage.getCoffee();
            cups--;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    // Settling money
    private void giveMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void takeMoney(int money) {
        this.money += money;
    }
}
