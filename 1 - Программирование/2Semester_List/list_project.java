/*
public class list<T extends Comparable<T>> {
    private T[] list; // массив элемента
    private int last; // последняя позиция

    public list(T[] list){
        1) this.list = list;
        2) последняя позиция last = -1;
    }
    // возвращает позицию после последнего.
    public int end(){
        1) возвращается last + 1;
    }
    // вставим x в позицию p
    public void insert(T x, int position){
        1) если
            1.1. position < 0
            1.2. (position > last) && (last == list.length)) => return;
        2) Переместить все элементы с position до last на 1 ячейку назад
        3) заменить значение в позиции position на x
        4) увеличить last ++;
    }
    //  возвращает позицию в списке L объекта x
    public int location(T x){
        1) инициализировать переменную i = 0
        2) проверка от начала до конца массива, если найден элемент = x => break
        3) возвращается i;
    }
    // возвращается объект списка L в позиции p
    public T retrieve(int position){
        1) если (position < 0) или (position > last) выбросить исключение
        2) возвращается list[position];
    }
    //удалить элемент списка L в позиции p
    public void delete(int position){
        1) если (position < 0) или (position > last) return;
        2) Переместить все элементы с position до last на 1 ячейку вперед
        3) last--;
    }
    // возвращает следующую за p позицию в списке L
    public int next(int position){
        1) если (position < 0) или (position > last) выбросить исключение
        2) возвращается  position + 1;
    }
    // возвращает предыдущую перед p позицию
    public int previous(int position){
        1) если (position < 0) или (position > last) выбросить исключение
        2) возвращается  position - 1;
    }
    // делает список пустым.
    public void makenull(){
        1) last = -1
    }
    //  возвращает 1-ую позицию
    public int first(){
        1) возвращается  position - 1;
    }
    public void printList(){
        1) отображение на экране массив list (0 - last)
    }

}

 */