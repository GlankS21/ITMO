/*
class Rational implements Comparable<Rational> {
    private int a, b; //числитель и знаменатель
    public Rational(int a, int b) {
        1) Если b == 0 throw new IllegalArgumentException("знаменатель должен быть отличен от 0")
        2) найти наименьшее общее кратное чисел a и b n = gcd(Math.abs(a), Math.abs(b));
        3) this.a *= a/n;
           this.b *= b/n;
       4) если (this.b < 0) => this.a *= -1, this.b *= -1;
    }
    // функция чтобы найти наименьшее общее кратное чисел a и b
    private int gcd(int a, int b) {
        1) инициализировать переменную r
        2) Выполняем
            2.1. r = a % b;
            2.2. a = b;
            2.3. b = r;
            Пока r не равен 0
        3) возвращается a;
    }
    @Override
    public int compareTo(Rational other) {
        1) возвращается this.a * other.b - other.a * this.b;
    }
    @Override
    public String toString() {
        1) ининициализировать новую переменную с = a/b;
           ecли c != 0 =>  a - b*c
           ecли a == 0 => возвращается c;
           если с == 0 => возвращается  a + "/" + b;
           другие варианты возвращается с + " целых " +  a + "/" + b;
    }
}
 */