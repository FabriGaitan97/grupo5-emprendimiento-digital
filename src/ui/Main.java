
package ui;

import gestion.Barberia;

public class Main {

    public static void main(String[] args) {
        Barberia barberia = new Barberia();
        Menu menu = new Menu(barberia);

        menu.iniciar();
    }
}
