package com.mathmaurer.jeu;
import Cards.*;
import Cards.Character;
import Cards.Monster.Monster;

import javax.swing.JFrame;

//***** La classe Main g�re le flux principal et ex�cute la m�thode main() qui lance l'application *****//
public class Main {
	
	//**** METHODES **//
	public static void main(String[] args) {


		Action[] theActions = new Action[5];
		theActions[0] = Action.forfait;
		theActions[1] = Action.attaquer;
		theActions[2] = Action.conjurer;
		theActions[3] = Action.defendre;
		theActions[4] = Action.recuperer;
		
		DamageSpell bobbyKiller = new DamageSpell("Tueur de Bobby", "Un sort crée pour blesser Bobby", 10, 3);
		SupportSpell healBobby = new SupportSpell("Booby se soigne", "Un sort crée pour rétablir Bobby", 6, -5);
		Spell[] BobbySpells = new Spell[1];
		BobbySpells[0] = healBobby;
		System.out.println(healBobby);
		Spell[] ConnySpells = new Spell[1];
		ConnySpells[0] = bobbyKiller;
		System.out.println(bobbyKiller);
		Spell[] blobSpell = new Spell[1];

		Monster blob=new Monster("blob","petit monstre visqueux sans réel enveloppe charnel",4,5, 2,theActions,blobSpell, 5);
		System.out.println();
		
		Character bobby = new Character("Bobby le premier", "Bobby le premier personnage", 5, 11, 2, theActions, BobbySpells);
		System.out.println(bobby);
		
		System.out.println();
		
		Character conny = new Character("Conny le premier", "Conny est l'antagoniste", 6, 16, 2, theActions, ConnySpells);
		System.out.println(conny);
		
		System.out.println();
		
		
		Jeu firstGame = new Jeu();
		System.out.println(firstGame.fullGame(conny, bobby));

		System.exit(1);
	}
}