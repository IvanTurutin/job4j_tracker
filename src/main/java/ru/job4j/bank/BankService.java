package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает логику банковского приложения, которое хранит список
 * клиентов с привязкой к ним банковских счетов и выполняет переводы
 * между счетами клиентов
 *  * @author IVAN TURUTIN
 *  * @version 1.0
 */
public class BankService {
    /**
     * Хранение списка счетов клиентов осуществляется в коллекции
     * типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового клиента, если его нет в списке
     * @param user клиент, который добавляется в список
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод добавляет в список новый счет пользователя если такого счета у
     * клиента еще нет
     * @param passport паспортные данные клиента, которому надо добавить счет
     * @param account счет клиента, который нужно добавить
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = users.get(user.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод ищет клиента по паспортным данным
     * @param passport паспортные данные клиента, данные на которого надо найти
     * @return возвращает объект пользователя, найденного по паспортным данным
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод ищет счет клиента по реквизитам счета
     * @param passport паспортные данные клиента, счет которого нужно найти
     * @param requisite реквизиты счета клиента
     * @return возвращает объект счета если счет был найден или null если не найден
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        Optional<Account> rsl = Optional.empty();
        if (user.isPresent()) {
            rsl = users.get(user.get())
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
        }
        return rsl;
    }

    /**
     * Метод осуществляет перевод средств между двумя счетами клиентов. Метод проверяет
     * достаточность средств на счету первого клиента и переводит их на счет второго
     * клиента.
     * @param srcPassport паспортные данные первого клиента
     * @param srcRequisite реквизиты счета первого клиента
     * @param destPassport паспортные данные второго клиента
     * @param destRequisite реквизиты счета второго клиента
     * @param amount количество средств, которые нужно перевести
     * @return метод возвращает true если перевод выполнен успешно и false если перевод
     * не выполнен
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Optional<Account> srcAcc = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc.isPresent() && destAcc.isPresent() && srcAcc.get().getBalance() >= amount) {
            srcAcc.get().setBalance(srcAcc.get().getBalance() - amount);
            destAcc.get().setBalance(destAcc.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }

}