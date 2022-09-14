package view;
import javax.swing.JOptionPane;
import controller.Cozinha;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);

		for (int i=1;i<6;i++) {
			Thread cozinha = new Cozinha(i, semaforo);
			cozinha.start();
		}
	}
}
