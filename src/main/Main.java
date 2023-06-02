package main;

import model.Company;

import java.util.*;

public class Main {

    Company company = new Company();
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Main m = new Main();
        m.menu();

    }

    public void menu(){
        int option = 0;

        do{
            System.out.println("Welcome to AMAZON delivery HELP system, please select an option: "+ "\n" +
            "1. SHOW THE CHEAPEST WAY TO DELIVER A PRODUCT" + "\n" +
            "2. PROBLEMA CON DFS" + "\n" +
            "0. Exit");
           option = sc.nextInt();

           switch (option){
               case 1:
                   showDistance();
                   break;
               case 2:

                   break;
               case 0:
                   System.out.println("Exiting program...");
                   break;
               default:
                   System.out.println("Please verifiy your selection, you fucking idiot");
                   break;

           }

        }while(option!=0);


    }

    public void showDistance(){
        /*
       String city1,city2;
            System.out.println("Enter the source city: ");
            city1 = sc.next();
            System.out.println("Enter the destination city: ");
            city2 = sc.next();

        Map<String,Object> result=  company.showDistanceC(city1,city2);
        int distance = (int) result.get("distance");
        List<String> path = (List<String>) result.get("path");
        System.out.println("Total cost: " + distance*10 + "$");
        System.out.println("Shortest path: " + path);



         */


       String answer = company.getGraph().bfs("Armenia");
       System.out.println(answer);

    }
}