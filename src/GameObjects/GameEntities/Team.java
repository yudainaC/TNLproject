package GameObjects.GameEntities;

import Exceptions.HeroAlreadyExistException;
import Exceptions.HeroNonExistException;
import Exceptions.TeamIsFullException;
import Exceptions.TeamNoExistException;

import java.sql.Array;
import java.util.*;

public class Team {
    protected List<Hero> equipe_hero;
    protected List<Monster> equipe_monster;


    /**
     * constructeur
     * permet de créer une équipe de Hero
     * @param nbequipe
     */
    public Team(int nbequipe){
        equipe_hero= new ArrayList<Hero>();
    }

    /**
     * constructeur
     * permet de créer une équipe de monstre( crée a chaque combat donc)
     */
    public Team(){
        equipe_monster= new ArrayList<Monster>();
    }


    /**
     * methode
     * permet d'afficher les membres de l'équipe
     * @return une chaine de caractère de hero
     */
    public String getTeamHero(){
        this.equipe_hero.forEach( (hero) -> {
        System.out.println("   " + hero + "\n");
        });
        return "";
    }

    /**
     * méthode
     * permet d'ajouter un membre a une equipe de hero
     * @param h1 (le hero qu'on veut ajouter)
     * @return vraie si le hero a bien été ajouté, faux si ce n'est pas le cas
     * @throws HeroAlreadyExistException si le hero exist deja
     * @throws TeamIsFullException si l'equipe est pleine
     */
    public boolean addToTeam(Hero h1) throws HeroAlreadyExistException, TeamIsFullException {
        if (this.equipe_hero.contains(h1)) throw new HeroAlreadyExistException();
        if(this.equipe_hero.getLast() == null) throw new TeamIsFullException();
        return this.equipe_hero.add(h1);
    }


    /**
     * méthode
     * permet de retirer un membre a une equipe de hero
     * @param h2
     * @return vraie si le hero a bien été retire, faux si ce n'est pas le cas
     * @throws HeroNonExistException
     * @throws TeamNoExistException
     */
    public boolean removeToTeam(Hero h2) throws HeroNonExistException, TeamNoExistException {
        if (!(this.equipe_hero.contains(h2))) throw new HeroNonExistException();
        return this.equipe_hero.remove(h2);
    }




}
