class Rational implements Comparable<Rational> {
    private int a, b; //числитель и знаменатель

    /**
     *  1) Если b == 0 throw new IllegalArgumentException("знаменатель должен быть отличен от 0")
     *  2) найти наименьшее общее кратное чисел a и b n = gcd(Math.abs(a), Math.abs(b));
     *  3) this.a *= a/n;
     *     this.b *= b/n;
     *  4) если (this.b < 0) => this.a *= -1, this.b *= -1;
     */
    public Rational(int a, int b) {
        if (b == 0) throw new IllegalArgumentException("знаменатель должен быть отличен от 0");
        if(b < -1){ // Если знаменатель получит отрицательный знак => Меняем знак
            a *= -1;
            b *= -1;
        }
        int n = gcd(Math.abs(a), b);
        this.a = a/n;
        this.b = b/n;
    }
    /**
     * 1) инициализировать переменную r
     * 2) Выполняем
     *      2.1. r = a % b;
     *      2.2. a = b;
     *      2.3. b = r;
     *    Пока r не равен 0
     * 3) возвращается a;
     */
    private int gcd(int a, int b) {  // наибольший общий делитель
        int r;
        do{
            r = a % b;
            a = b;
            b = r;
        }while (r != 0);
        return a;
    }

    /**
     * возвращается this.a * other.b - other.a * this.b;
     */
    @Override
    public int compareTo(Rational other) {
        return this.a * other.b - other.a * this.b;
    }

    /**
     * инициализировать новую переменную с = a/b;
     * если c != 0 =>  a - b*c
     * если a == 0 => возвращается c;
     * если с == 0 => возвращается  a + "/" + b;
     * другие варианты возвращается с + " целых " +  a + "/" + b;
     */
    @Override
    public String toString() {
        int c = a/b;
        int d = Math.abs(a - b*c);
        if(a == 0 || d == 0) return c + "";       // Вариант только целая часть
        if(c == 0) return a + "/" + b;  // Вариант нет целая часть
        return c + "(" + d + "/" + b + ")";     // Остальные
    }
}