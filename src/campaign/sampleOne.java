package campaign;

import java.util.ArrayList;
import java.util.List;

public class sampleOne {

    public static void main(String args[]) {
        List<String> customerProfiles = new ArrayList<>();
        List<Campaign> campaigns = new ArrayList<>();
        Participant participantOne = new Participant("ElectorOne");
        Participant participantTwo = new Participant("ElectorTwo");
        Participant participantThree = new Participant("JuradoTree");
        campaigns.add(new Campaign("Alcaldia", true, participantOne));
//        campaigns.add(new campaign.Campaign("Alcaldia", true, Arrays.asList(participantOne, participantTwo)));
//        campaigns.add(new campaign.Campaign("Gobernación", true, Arrays.asList(participantOne, participantTwo)));
//        campaigns.add(new campaign.Campaign("Presidencia", false, Arrays.asList(participantOne, participantTwo, participantThree, participantTwo)));

        /*for (campaign.Campaign campaign : campaigns) {
            if (campaign.isActive()) {
                for (campaign.Participant participant : campaign.getParticipants()) {
                    customerProfiles.add(participant.getCustomerProfile());
                }
            }
        }*/
        campaigns.stream()
                .filter(Campaign::isActive)
                .map(campaign -> campaign.getParticipants())
                .filter(participant -> participant.getCustomerProfile() == "1");
        //customerProfiles.forEach(System.out::println);
    }
}
