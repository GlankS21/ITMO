public class ListString{
    private class StringItem {
        private final static byte SIZE = 16; //размер символьного массива в блок
        private final char [] symbols; // массив символов в блоке
        private StringItem next; // следующий блок
        private int size; // количество элементов в блоке
        public StringItem(){
            symbols = new char[SIZE];
            size = 0;
        }
        public StringItem(char[] c) {
            if(c.length == SIZE){
                symbols = c;
                size = 16;
            } else{
                symbols = new char[SIZE];
                copy(0,c.length,0,c,symbols);
                this.size = c.length;
            }
        }
        public StringItem(StringItem s){
            symbols = new char[16];
            copy(0,s.size,0,s.symbols,symbols);
            this.size = s.size;
            this.next = s.next;
        }
    }
    private static class Position{
        StringItem block;
        int position;
        public Position(StringItem block, int position){
            this.block = block;
            this.position = position;
        }
    }
    private StringItem head;
    public ListString(){} // пустая строка
    public ListString(String s) {
        if (s.length() < 16) head = new StringItem(s.toCharArray()); // если s < 16 => сразу возвращаться head
        else {
            head = new StringItem(s.substring(0, 16).toCharArray());
            StringItem temp = head;
            for (int i = 32; i <= s.length(); i += 16) { // Разделить на части
                temp.next = new StringItem(s.substring(i - 16, i).toCharArray());
                temp = temp.next;
            }
            int residuals = s.length() % 16; // остальные
            if (residuals != 0) temp.next = new StringItem(s.substring(s.length()-residuals).toCharArray());
        }
    }
    public ListString(ListString ls){
        if(ls.head == null) return;
        this.head = new StringItem(ls.head);
        StringItem temp = head;
        StringItem add = ls.head.next;
        while(add != null){
            temp.next = new StringItem(add);
            temp = temp.next;
            add = add.next;
        }
    }
    //длина строки
    public int length() {
        int res = 0;
        for(StringItem temp = head; temp != null; temp = temp.next) {
            while(join(temp, temp.next)) {} // Если можно соединять, мы соединяем, а наоборот, цикл закачивается
            res += temp.size;
        }
        return res;
    }
    //вернуть символ в строке в позиции index
    public char charAt(int index) throws StringException{
        if(index < 0) throw new StringException("Позиция не действительна");
        Position pos = find(index);
        if(pos.position == -1) throw new StringException("Позиция не действительна");
        pos.block = (pos.block == null) ? head : pos.block.next;
        return (pos.block == null) ? head.symbols[index] : pos.block.symbols[pos.position];
    }
    //заменить символ в позиции index на c
    public void setcharAt(int index, char c) throws StringException{
        if(index < 0) throw new StringException("Позиция не действительна");
        Position pos = find(index);
        if(pos.position == -1) throw new StringException("Позиция не действительна");
        pos.block = (pos.block == null) ? head : pos.block.next;
        if(pos.block == null) head.symbols[index] = c;
        else pos.block.symbols[pos.position] = c;
    }
    //взятие подстроки, от start до end, не включая end
    public ListString subString(int start, int end) throws StringException{
    // Бросить исключение
        if(start < 0 || end < start) throw new StringException("позиция не действительна");
        Position s = find(start);   // первый блок
        if(s.position == -1) throw new StringException("позиция не действительна");
        Position e = find(end); // последний блок
//        if(e.position == -1) throw new StringException("позиция не действительна");
        // set block
        s.block = (s.block == null) ? head : s.block.next;
        e.block = (e.block == null) ? head : e.block.next;
        if(e.position == -1){
            e.block = end();
            e.position = end().size;
        }
        ListString res = new ListString();
        res.head = new StringItem();
    // Первый вариант: вариант в одном блоке
        if(s.block == e.block){
            res.head.size = e.position - s.position;  // длина первого блока
            copy(s.position,res.head.size,0,s.block.symbols, res.head.symbols); // ее элементы
            //return res;
        }
    // Второй вариант: вариант в разных блоках
        else{
            res.head.size = s.block.size - s.position; // длина первого блока
            copy(s.position,res.head.size,0,s.block.symbols, res.head.symbols); // элементы первого блока
            StringItem temp = res.head;
            StringItem add = s.block.next; // Блок, который мы добавим
            while(add != e.block){   // добавим блоки
                temp.next = new StringItem(add);
                temp = temp.next;
                add = add.next;
            }
            temp.next = new StringItem(); // последний блок
            copy(0,e.position,0,e.block.symbols, temp.next.symbols); // элементы последного блока
            temp.next.size = e.position; // длина последного блока
        }
        return res;
    }
    //добавить в конец символ
    public void append(char c){
        if(head == null) {
            head = new StringItem();
            head.symbols[head.size++] = c;
        }
        else{
            StringItem end = end();
            if(end.size == 16) {
                end.next = new StringItem();
                end.next.symbols[end.next.size++] = c;
            }
            else  end.symbols[end.size++] = c;
        }
    }
    //добавить в конец строку String
    public void append(String s){
        ListString ls = new ListString(s);
        StringItem end = end();
        if(end == null) head = new StringItem(ls.head); // вариант end = head = null
        else end.next = new StringItem(ls.head);
    }
    //добавить в конец строку ListString
    public void append(ListString ls){
        StringItem add = ls.head; // Блок, который мы добавим
        StringItem end = end();
        if(end == null) {   // если end == null => заменить end = ls.head
            end = new StringItem(add);
            add = add.next;
        }
        while(add != null){   // добавим все элементы из ls в конце строки
            end.next = new StringItem(add);
            end = end.next;
            add = add.next;
        }
    }
    //вставить в строку в позицию index строку ListString
    public void insert(int index, ListString ls) throws StringException{
        if(index < 0) throw new StringException("позиция не действительна");
        Position pos = find(index);    // Найти блок, в котором содержит position
        if(pos.position == -1) throw new StringException("позиция не действительна");
        // Копируем ListString ls
        ListString s = new ListString(ls);
        subinsert(pos, s);
    }
   // вставить в строку в позицию index строку String
    public void insert(int index, String s) throws StringException{
        if(index < 0) throw new StringException("позиция не действительна");
        Position pos = find(index);
        if(pos.position == -1) throw new StringException("позиция не действительна");
        subinsert(pos, new ListString(s));
    }
    private void subinsert(Position pos, ListString ls){
        StringItem end = ls.end();
        if(end == null) return; // Вариант Ls == null
        if(pos.position == 0){  //  Если pos.position = 0 => мы поставим новую строку между блоками (не разделить блоки)
            if(pos.block == null){  // вариант index = 0
                end.next = this.head;
                this.head = ls.head;
            } else{
                end.next = pos.block.next;
                pos.block.next = ls.head;
            }
        } else{  // наоборот, разделим блоки
            pos.block = (pos.block == null) ? head:pos.block.next;
            split(pos.block, pos.position);
            end.next = pos.block.next;
            pos.block.next = ls.head;
        }
    }
    public String toString(){
        if(head == null) return "";
        String str = new String(head.symbols,0,head.size);
        StringItem temp = head.next;
        while(temp != null){
            str += new String(temp.symbols,0,temp.size);
            temp = temp.next;
        }
        return str;
    }
    private StringItem end(){
//        if(head == null) return null;
        StringItem temp = head;
        StringItem prev = null;
        while(temp != null) {
            prev = temp;
            temp = temp.next;
        }
        return prev;
    }
    private Position find(int index){
        StringItem pre = null;
        StringItem temp = head;
        while(temp != null && index >= temp.size){
            index -= temp.size;
            pre = temp;
            temp = temp.next;
        }
        if(temp == null) return new Position(null, -1);
        return new Position(pre, index);
    }
    protected void copy(int start, int lenght, int index, char[] from, char[] to){
        int i, end = start + lenght;
        for(i = start; i < end; ++i) to[index++] = from[i];
    }
    private void split(StringItem block, int index){
        StringItem after = new StringItem(); // Вторая часть
        after.size = block.size - index; // длина второй части
        copy(index, after.size,0, block.symbols, after.symbols); // компируем все элеметы из index => block.size и поставим на новый массив
        if(block.next != null) after.next = block.next; // Подключиться к остальным
//        copy(0,index,0, block.symbols, block.symbols); // Первая часть из 0 => index
        block.size = index; // длина первой части
        block.next = new StringItem(after);
    }
    private boolean join(StringItem block1, StringItem block2){
        if(block1 == null || block2 == null || block1.size + block2.size > 16) return false; // Варианты не подходят
        copy(0, block2.size, block1.size, block2.symbols, block1.symbols); // поставим все элементы из блока 2 на блок 1
        block1.size += block2.size; // set size
        block1.next = block2.next; // set next
        return true;
    }
    protected static class StringException extends RuntimeException{
        private final String exc;
        public StringException(String exc){
            this.exc = exc;
        }
        public String toString(){ return exc;}
    }

}
