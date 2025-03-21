public class Main {
    public static void main(String[] args) {
        list<String> ls = new list<>(new String[10]);
        list<Integer> li = new list<>(new Integer[10]);
        list<Rational> lr = new list<>(new Rational[10]);
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.insert("b",ls.end());
        ls.printList();
        System.out.println("Позиция элемента 'c' в списке " + ls.location("c"));
        delete_dublicate(ls);
        ls.printList();

        li.insert(0,li.end());
        li.insert(1,li.end());
        li.insert(2,li.end());
        li.insert(2,li.end());
        li.insert(3,li.end());
        li.insert(3,li.end());

        li.printList();
        delete_dublicate(li);
        li.printList();

        lr.insert(new Rational(-5,-5),lr.end());
        lr.insert(new Rational(10,5),lr.end());
        lr.insert(new Rational(-10,3),lr.end());
        lr.insert(new Rational(-3,2),lr.end());
        lr.insert(new Rational(-15,10),lr.end());

        lr.printList();
        delete_dublicate(lr);
        lr.printList();

        lr.makenull();
        lr.insert(new Rational(100,30),lr.end());
        lr.insert(new Rational(200,60),lr.end());
        lr.insert(new Rational(-100,-30),lr.end());
        lr.insert(new Rational(-50,-15),lr.end());
        lr.insert(new Rational(-5,-1),lr.end());
        lr.insert(new Rational(-5000,-1000),lr.end());
        lr.insert(new Rational(-100,-20),lr.end());
        lr.insert(new Rational(-50,-10),lr.end());
        lr.insert(new Rational(-5,-1),lr.end());
        lr.insert(new Rational(-5000,-1000),lr.end());

        lr.printList();
        delete_dublicate(lr);
        lr.printList();
    }

    //Удаление дубликатов

    /**
     * 1) проверка от i = list.first() до i != list.end() c шагом list.next(i)
     *            1.1. инициализировать T current = list.retrieve(i);
     *            1.2. проверка от j = list.next(i) до j != list.end() с шагом list.next(j)
     *                1.2.1. инициализировать T next = list.retrieve(j);
     *                1.2.2. Если current совпадает next
     *                    1.2.2.1. list.delete(j);
     *                    1.2.2.2. j = list.previous(j);
     */
    public static <T extends Comparable<T>> void delete_dublicate(list<T> list) {
        for (int i = list.first(); i != list.end(); i = list.next(i)) {
            T current = list.retrieve(i);
            for (int j = list.next(i); j != list.end(); j = list.next(j)) {
                T next = list.retrieve(j);
                if (current.compareTo(next) == 0) { // сравниваем 2 элемента
                    list.delete(j);
                    j = list.previous(j);
                }
            }
        }
    }
}