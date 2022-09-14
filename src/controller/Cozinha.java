package controller;

import java.util.concurrent.Semaphore;

public class Cozinha extends Thread {
	private int ThreadID;
	private double tempo;
	private int prato;
	private Semaphore semaforo;
	
	public Cozinha(int ThreadID, Semaphore semaforo) {
			this.ThreadID = ThreadID;
			this.semaforo = semaforo;
	}
		
	public void run() {
		prato = ThreadID % 2;
		Cozimento(prato);
		Entrega(prato);
	}
	
	public void Cozimento(int prato) {
		long temposleep = (long) 0.1;

		if (prato==1) {
			System.out.println("Preparando pedido "+ThreadID+" - Sopa de Cebola");
			do {
				tempo=Math.random() * 2;
			} while (tempo < 0.5 || tempo > 0.8);
			for (double i=0;i<tempo;i=i+0.1) {
				try {
					sleep(temposleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println((i/tempo*100)+"% do prato "+ThreadID+" está pronto");				
			}
		} else {
			System.out.println("Preparando pedido "+ThreadID+" - Lasanha a Bolonhesa");
			do {
				tempo=Math.random() * 2;
			} while (tempo < 0.6 || tempo > 1.2);
			for (double i=0;i<tempo;i=i+0.1) {
				try {
					sleep(temposleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println((i/tempo*100)+"% do prato "+ThreadID+" está pronto");				
			}
		}
		System.out.println("100% do prato "+ThreadID+" está pronto");		
	}
	
	public void Entrega(int prato) {
		long tempolong = (long) 0.5;
		try {
			sleep(tempolong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			semaforo.acquire();
			System.out.println("Prato "+ThreadID+" entregue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}		
	}
}