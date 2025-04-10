package com.mathmaurer.jeu;
import Cards.*;
import Cards.Character;

import javax.swing.JFrame;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) {
		Action[] theActions = new Action[4];
		theActions[0] = Action.attaquer;
		theActions[1] = Action.conjurer;
		theActions[2] = Action.defendre;
		theActions[3] = Action.recuperer;
		
		DamageSpell bobbyKiller = new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3);
		SupportSpell healBobby = new SupportSpell("Booby se soigne", "Un sort crée pour rétablir Bobby", 6, -5);
		Spell[] BobbySpells = new Spell[1];
		BobbySpells[0] = healBobby;
		System.out.println(healBobby);
		Spell[] ConnySpells = new Spell[1];
		ConnySpells[0] = bobbyKiller;
		System.out.println(bobbyKiller);
		
		System.out.println();
		
		Character bobby = new Character("Bobby le premier", "Bobby le premier personnage", 5, 11, 2, theActions, BobbySpells);
		System.out.println(bobby);
		
		System.out.println();
		
		Character conny = new Character("Conny le premier", "Conny est l'antagoniste", 6, 16, 2, theActions, ConnySpells);
		System.out.println(conny);
		
		System.out.println();
		
		
		Jeu firstGame = new Jeu();
		System.out.println(firstGame.fullGame(conny, bobby));
		
	}
}