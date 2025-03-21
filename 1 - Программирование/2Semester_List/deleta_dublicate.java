/*
   public static <T extends Comparable<T>> void delete_dublicate(list<T> list) {
       1) проверка от i = list.first() до i != list.end() c шагом list.next(i)
           1.1. инициализировать T current = list.retrieve(i);
           1.2. проверка от j = list.next(i) до j != list.end() с шагом list.next(j)
               1.2.1. инициализировать T next = list.retrieve(j);
               1.2.2. Если current совпадает next
                   1.2.2.1. list.delete(j);
                   1.2.2.2. j = list.previous(j);
   }
    */