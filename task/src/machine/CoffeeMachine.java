package machine;

import java.util.Scanner;

class CoffeeEngine {
    static int water;
    static int milk;
    static int beans;
    static int cups;
    static int money;

    enum Resources {
        water,
        milk,
        beans,
        cups,
        money
    }

    enum Action {
        buy,
        fill,
        take,
        remaining,
        exit
    }

    enum Type {
        unknown,
        espresso,
        latte,
        cappuccino,
    }

    public Type convertType(String type) {
        switch (type) {
            case "1":
                return Type.espresso;
            case "2":
                return Type.latte;
            case "3":
                return Type.cappuccino;
            default:
                return Type.unknown;
        }
    }
    
    public void operResources(Resources resources, Action action, int n) {
        switch (action) {
            case buy:
                switch (resources) {
                    case water:
                        water -= n;
                        break;
                    case milk:
                        milk -= n;
                        break;
                    case beans:
                        beans -= n;
                        break;
                    case cups:
                        cups -= n;
                        break;
                    case money:
                        money += n;
                        break;
                    default:
                        break;
                }
                break;
            case fill:
                switch (resources) {
                    case water:
                        water += n;
                        break;
                    case milk:
                        milk += n;
                        break;
                    case beans:
                        beans += n;
                        break;
                    case cups:
                        cups += n;
                        break;
                    default:
                        break;
                }
                break;
            case take:
                System.out.printf("I gave you $%d\n", money);
                money = 0;
        }
    }

    public void actEngine(Action action) {
        switch (action) {
            case remaining:
                System.out.println("\nThe coffee machine has:");
                System.out.printf("%d of water", water);
                System.out.printf("\n%d of milk", milk);
                System.out.printf("\n%d of coffee beans", beans);
                System.out.printf("\n%d of disposable cups", cups);
                System.out.printf("\n%d of money\n\n", money);
                break;
            case exit:
                System.exit(0);
        }
    }

    public void initResources(Resources resources, int n) {
        switch (resources) {
            case water:
                water = n;
                break;
            case milk:
                milk = n;
                break;
            case beans:
                beans = n;
                break;
            case cups:
                cups = n;
                break;
            case money:
                money = n;
                break;
            default:
                break;
        }
    }

    public void actionBuy() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String action = scanner.nextLine();
        Type type = convertType(action);
        
        if (!action.equals("back") && checkResources(type)) {
            operResources(Resources.water, Action.buy, baseResources(type, Resources.water));
            operResources(Resources.milk, Action.buy, baseResources(type, Resources.milk));
            operResources(Resources.beans, Action.buy, baseResources(type, Resources.beans));
            operResources(Resources.cups, Action.buy, baseResources(type, Resources.cups));
            operResources(Resources.money, Action.buy, baseResources(type, Resources.money));
            
        } else {
            if (!action.equals("back")) {
                messageResources(type);
            }
        }
    }

    public void actionFill() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water do you want to add:");
        operResources(Resources.water, Action.fill, scanner.nextInt());
        System.out.println("Write how many ml of milk do you want to add:");
        operResources(Resources.milk, Action.fill, scanner.nextInt());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        operResources(Resources.beans, Action.fill, scanner.nextInt());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        operResources(Resources.cups, Action.fill, scanner.nextInt());
    }

    public static boolean checkResources(Type type) {
        boolean check = false;
        if (baseResources(type, Resources.water) < water &&
                baseResources(type, Resources.milk) < milk &&
                baseResources(type, Resources.beans) < beans &&
                baseResources(type, Resources.cups) < cups) {
            check = true;
        }
        return check;
    }

    public static void messageResources(Type type) {
        if (baseResources(type, Resources.water) > water &&
                baseResources(type, Resources.milk) <= milk &&
                baseResources(type, Resources.beans) <= beans &&
                baseResources(type, Resources.cups) <= cups) {
            System.out.println("Sorry, not enough water!");
        } else {
            if (baseResources(type, Resources.water) <= water &&
                    baseResources(type, Resources.milk) > milk &&
                    baseResources(type, Resources.beans) <= beans &&
                    baseResources(type, Resources.cups) <= cups) {
                System.out.println("Sorry, not enough milk!");
            } else {
                if (baseResources(type, Resources.water) <= water &&
                        baseResources(type, Resources.milk) <= milk &&
                        baseResources(type, Resources.beans) > beans &&
                        baseResources(type, Resources.cups) <= cups) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else {
                    if (baseResources(type, Resources.water) <= water &&
                            baseResources(type, Resources.milk) <= milk &&
                            baseResources(type, Resources.beans) <= beans &&
                            baseResources(type, Resources.cups) > cups) {
                        System.out.println("Sorry, not enough cups!");
                    } else {
                        System.out.println("I have enough resources, making you a coffee!");
                    }
                }
            }
        }



    }

    public static int baseResources(Type type, Resources resources) {
        switch (type) {
            case espresso:
                switch (resources) {
                    case water:
                        return 250;
                    case milk:
                        return 0;
                    case beans:
                        return 16;
                    case cups:
                        return 1;
                    case money:
                        return 4;
                    default:
                        break;
                }
                break;
            case latte:
                switch (resources) {
                    case water:
                        return 350;
                    case milk:
                        return 75;
                    case beans:
                        return 20;
                    case cups:
                        return 1;
                    case money:
                        return 7;
                    default:
                        break;
                }
                break;
            case cappuccino:
                switch (resources) {
                    case water:
                        return 200;
                    case milk:
                        return 100;
                    case beans:
                        return 12;
                    case cups:
                        return 1;
                    case money:
                        return 6;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return 0;
    }
    
}

public class CoffeeMachine {

    public static void main(String[] args) {
        CoffeeEngine coffeeEngine = new CoffeeEngine();
        coffeeEngine.initResources(CoffeeEngine.Resources.water, 400);
        coffeeEngine.initResources(CoffeeEngine.Resources.milk, 540);
        coffeeEngine.initResources(CoffeeEngine.Resources.beans, 120);
        coffeeEngine.initResources(CoffeeEngine.Resources.cups, 9);
        coffeeEngine.initResources(CoffeeEngine.Resources.money, 550);

        Scanner scanner = new Scanner(System.in);
        String action;

        do {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            action = scanner.nextLine();
            switch (action) {
                case "buy":
                    coffeeEngine.actionBuy();
                    break;
                case "fill":
                    coffeeEngine.actionFill();
                    break;
                case "take":
                    coffeeEngine.operResources(CoffeeEngine.Resources.money, CoffeeEngine.Action.take, 0);
                    break;
                case "remaining":
                    coffeeEngine.actEngine(CoffeeEngine.Action.remaining);
                    break;
                case "exit":
                    coffeeEngine.actEngine(CoffeeEngine.Action.exit);
                    break;
                default:
                    break;
            }
        } while (!action.equals("X"));
}
}