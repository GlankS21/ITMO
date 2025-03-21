public class Main {
    public static void main(String[] args) {
        ListString temp = new ListString("0123456789!!!!!!!!!!!!!!!!a");
        System.out.println("Длина строки: " + temp.length());
        ListString temp1 = new ListString("");
        temp.insert(0, temp1);
        System.out.println("Строка после insert 16: " + temp);
        temp.setcharAt(16,'+');
        System.out.println("Строка после setcharAt:" + temp);
        System.out.println("Строка после subString 16-32: " + temp.subString(16,32));
        System.out.println("Строка после subString 15-31: " + temp.subString(15,31));
        ListString test = temp.subString(16,31);
        System.out.println("test: " + test);
        test.insert(0,"....");
        System.out.println(test);
//        System.out.println(test.charAt(5));
    }
}