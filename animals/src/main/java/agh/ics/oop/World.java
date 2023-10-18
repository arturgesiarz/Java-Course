package agh.ics.oop;

public class World {
    public static void main(String[] args){
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }
    private static void run(String[] args){
        int n=args.length;
        for(int i=0;i<n;i++){
            switch (args[i]){
                case "f" -> System.out.println("Zwierzak idzie do przodu");
                case "b" -> System.out.println("Zwierzak idzie do tyłu");
                case "r" -> System.out.println("Zwierzak idzie do prawo");
                case "l" -> System.out.println("Zwierzak idzie do lewo");
            }
        }
    }
}
