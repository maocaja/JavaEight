package campaign;

public class Campaign {
    private String code;
    private String name;
    private boolean active;
    private Participant participant;

    public String getCampaignCode() {
        return code;
    }

    public Campaign(String name, boolean active, Participant participant) {
        this.code = "1";
        this.name = name;
        this.active = active;
        this.participant = participant;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Participant getParticipants() {
        return participant;
    }
}
