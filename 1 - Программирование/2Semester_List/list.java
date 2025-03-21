public class list<T extends Comparable<T>> {
    private final T[] list;
    private int last; // последний занятый

    /**
     * 1) this.list = list;
     * 2) последняя позиция last = -1; // последний занятый
     */
    public list(T[] list){
        this.list = list;
        last = -1;
    }
        /**
         * возвращается last + 1;
         */
    // возвращает позицию после последнего.
    public int end(){ return last + 1;}
    /**
     * 1) если
     *      1.1. position < 0
     *      1.2. (position > last) && (last == list.length)) => return;
     * 2) Переместить все элементы с position до last на 1 ячейку назад
     * 3) заменить значение в позиции position на x
     * 4) увеличить last ++;
     */
    // вставим x в позицию p
    public void insert(T x, int position){
        if (position < 0 || (position > end())) return; // Если позиция < 0 или position > last + 1, выбросить исключение
        for (int i = last; i > position; i--) { //  Переместить все элементы с position до last на 1 ячейку назад
            list[i] = list[i - 1];
        }
        list[position] = x; // заменить значение в позиции position на x
        last ++;
    }
    /**
     * 1) инициализировать переменную i = 0
     * 2) проверка от начала до конца массива, если найден элемент = x => break
     * 3) возвращается i;
     */
    //  возвращает позицию в списке L объекта x
    public int location(T x){
        int i;
        for(i = 0; i <= last; i++){
            if(list[i].compareTo(x) == 0) break; // Если найден элемент = х, остановимся
        }
        return i;
    }

    /**
     * 1) если (position < 0) или (position > last) выбросить исключение
     * 2) возвращается list[position];
     */
    // возвращается объект списка L в позиции p
    public T retrieve(int position){
        if(position < 0 || position > last) throw new ListException("Позиция не найдена"); // Если позиция не найдена, выбросить исключение
        return list[position];
    }
    /**
     * 1) если (position < 0) или (position > last) return;
     * 2) Переместить все элементы с position до last на 1 ячейку вперед
     * 3) last--;
     */
    //удалить элемент списка L в позиции p
    public void delete(int position){
        if ((position < 0) || (position > last)) return;  // Если позиция не найдена, выбросить исключение
        for (int i = position; i < last; i++) // Переместить все элементы с position до last на 1 ячейку вперед
            list[i] = list[i + 1];
        last--;
    }
    /**
     * 1) если (position < 0) или (position > last) выбросить исключение
     * 2) возвращается  position + 1;
     */
    // возвращает следующую за p позицию в списке L
    public int next(int position){
        if(position < 0 || position > last) throw new ListException("Позиция не найдена"); // Если позиция не найдена, выбросить исключение
        return position + 1; // Если p – последняя позиция в списке L, то Next(p) = End()
    }
    /**
     * 1) если (position < 0) или (position > last) выбросить исключение
     * 2) возвращается  position - 1;
     */
    // возвращает предыдущую перед p позицию
    public int previous(int position){
        if(position < 1 || position > end()) throw new ListException("Позиция не найдена");
        return position - 1;
    }
    // делает список пустым.
    public void makenull(){
        last = -1;
    }
    // возвращает 1-ую позицию
    public int first(){
        return 0;
    }
    // отображение на экране массив list (0 - last)
    public void printList(){
        System.out.print("List : ");
        for(int i = 0; i <= last; ++i){
            System.out.printf("%10s", list[i]);
        }
        System.out.println();
    }
}
