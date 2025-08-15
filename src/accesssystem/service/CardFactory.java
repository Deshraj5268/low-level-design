package accesssystem.service;

import accesssystem.core.AccessCard;
import accesssystem.core.Employee;

import java.util.List;
import java.util.Map;

public class CardFactory {
    private final Map<String, AccessCard> cardMap;

    public CardFactory(Map<String, AccessCard> cardMap) {
        this.cardMap = cardMap;
    }

    public AccessCard createCard(String cardId, String employeeId) {
        AccessCard card = new AccessCard(cardId, employeeId);
        cardMap.put(cardId, card);
        return card;
    }

    // bulk
    public void createCardInBulk(List<String> cardIds, List<Employee> employeeIds) {
        for (int i=0;i<cardIds.size();i++) {
            AccessCard card = new AccessCard(cardIds.get(i), employeeIds.get(i).getId());
            cardMap.put(cardIds.get(i), card);
        }
    }

    public void deactivateCardsByEmployee(String employeeId) {
        for (AccessCard card : cardMap.values()) {
            if (card.getEmployeeId().equals(employeeId)) {
                card.deactivate();
            }
        }
    }
}
