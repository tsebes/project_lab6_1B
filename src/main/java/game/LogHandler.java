package game;

public class LogHandler {

    private static volatile LogHandler instance;
    private static Battle logsBattle;
    private String activeCharacterName;
    private String currentAction;
    private String reaction;
    private String statusEffects;
    private String basicLog;
    private String fullLog;

    private LogHandler(){
        reaction = null;
        statusEffects = null;
    }

    public static LogHandler getInstance() {
        if (instance == null) {
            synchronized (LogHandler.class) {
                if (instance == null) {
                    instance = new LogHandler();
                }
            }
        }
        return instance;
    }

    public void setActiveCharacterName(String activeCharacterName){
        this.activeCharacterName = activeCharacterName;
    }

    public void setCurrentAction(Action action, Skill skill, Item item){
        switch (action){
            case BASICATTACK -> {
                currentAction = "attacked using basic attack";
            }
            case SKILL -> {
                currentAction = "used skill : " + skill.getName();
            }
            case ITEM -> {
                currentAction = "used item : " + item.getName();
            }
            case GUARD -> {
                currentAction = "started guarding";
            }
        }
    }

    public void setReaction(String reactionPart){
        if(reaction==null){
            reaction = reactionPart;
        }else{
            reaction += ",\n" + reactionPart;
        }
    }

    public void setReaction(String reactionPart, boolean continuation){
        if(reaction==null){
            reaction = reactionPart;
        }else{
            reaction += " " + reactionPart;
        }
    }

    public void setStatusEffects(String statusEffectsPart){
        if(statusEffects==null){
            statusEffects = statusEffectsPart;
        }else{
            statusEffects += ", \n" + statusEffectsPart;
        }
    }

    public void makeFullLog(){
        fullLog = activeCharacterName + " " + currentAction;
        basicLog = fullLog;
        if(reaction != null){
            fullLog +=  " - " + reaction;
        }
        if(statusEffects != null){
            fullLog += " " + statusEffects;
        }
        activeCharacterName = null;
        currentAction = null;
        reaction = null;
        statusEffects = null;
    }

    public String getBasicLog(){
        return basicLog;
    }

    public String getFullLog() {
        return fullLog;
    }
}
