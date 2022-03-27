import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Game game = new Game(Difficulty.EASY, false, 10);

        game.printLayout();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce una coordenada (y,x): ");
            String coords = sc.nextLine();
            String[] splited = coords.split(",");
            game.getGRID_LAYOUT()[Integer.parseInt(splited[0])][Integer.parseInt(splited[1])].setShowing(true);
            game.printLayout();
        }

    }

}
