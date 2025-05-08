package gameCore.GameObjects.GameElements.Skills;

import exceptions.NotASkillsException;

public enum Skills {
    natation,
    escalade,
    herboriste,
    docteur,
    cavernologie,
    orientation,
    DesarmementDePiege,
    peche,
    cuisine,
    meditation,
    artisanat,
    forceSurhumaine;


    public String toString() {
        return this.name();
    }

    public static Skills parseSkills(String bonus) throws NotASkillsException {
        return switch (bonus) {
            case "natation" -> Skills.natation;
            case "escalade" -> Skills.escalade;
            case "herboriste" -> Skills.herboriste;
            case "docteur" -> Skills.docteur;
            case "cavernologie" -> Skills.cavernologie;
            case "orientation" -> Skills.orientation;
            case "desarmementDePiege" -> Skills.DesarmementDePiege;
            case "peche" -> Skills.peche;
            case "cuisine" -> Skills.cuisine;
            case "meditation" -> Skills.meditation;
            case "artisanat" -> Skills.artisanat;
            case "forceSurhumaine" -> Skills.forceSurhumaine;

            default -> throw new NotASkillsException();
        };
    }

}
