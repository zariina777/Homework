package bankCard;

import java.util.Random;

public class Main {
    /*
    Create the Bank Card class:
    - Card details
    -Currency
    -Status

(All with a capital letter are classes)

Make a BankCardData Generator class that will return Card Data (String)
    The card data must be a String of the form:

12345678123456780000999
    The first 16 digits are the number
    Then the date (month/year)
    Then the CVV code

Create a Client class:
    -Name
    -Map
    -Balance
    Write methods to the repository that receive a Map as input
     (+ maybe some other parameter), and at the output they do the following:

- Display all customers who have a certain card status.
    - Group by balance: who has more or less than a certain amount.
    - Average balance by customers.
    - Display only the card numbers of all customers in the format name -> number.
    - Withdraw all customers whose cards have expired.
    - Sort by balance.
    - Group: who has expired cards and who does not.

     */
    public static void main(String[] args) {
        Random random = new Random();
        BankCardDataGenerator generator = new BankCardDataGenerator(random);
        Store store = new Store();

        for (int i = 0; i < 25; i++) {
            Currency currency = Currency.values()[random.nextInt(Currency.values().length)];
            Status status = Status.values()[random.nextInt(Status.values().length)];

            BankCard bankCard = new BankCard(generator.generate(), currency, status);

            store.add(new Client("Client " + i, bankCard, random.nextLong(100000L)));
        }

        store.printClientsWithCardStatus(Status.CLOSED);
        System.out.println();

        store.printGroupingByBalance(50000);
        System.out.println();

        store.printAverageBalance();
        System.out.println();

        store.printClientsWithCardNumbers();
        System.out.println();

        store.printClientsWithExpiredCards();
        System.out.println();

        store.printClientsSortedByBalance();
        System.out.println();

        store.printGroupingByCardExpired();
        System.out.println();
    }


}
