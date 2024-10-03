namespace  Lap_1_2;

/*
 * Характеристикой столбца целочисленной матрицы назовем сумму модулей его отрицательных нечетных элементов.
 * Переставляя столбцы заданной матрицы, расположить их в соответствии с ростом характеристик.
 * Найти сумму элементов в тех столбцах, которые содержат хотя бы один отрицательный элемент
 */

public class Program {
    // Поменяйте местами два столбца массива
    static void swap(int[,] arr, int a, int b){  
        for (var i = 0; i < arr.GetLength(0); ++i) {
            var temp = arr[i, a];
            arr[i, a] = arr[i, b];
            arr[i, b] = temp;
        }
    }
    // Вычислить сумму отрицательных элементов столбцов
    static int[] sumOfNefativeElements(int[,] arr){  
        var sum = new int[arr.GetLength(1)];
        for (var j = 0; j < arr.GetLength(1); ++j) {
            for (var i = 0; i < arr.GetLength(0); ++i) {
                sum[j] += (arr[i, j] < 0) ? Math.Abs(arr[i, j]) : 0;
            }
        }
        return sum;
    }
    // сортировать массив 
    static void sortArray(int[,] arr)  
    {
        // найти сумму 
        var sum = sumOfNefativeElements(arr); 
        // сортировать массив 
        for (var i = 0; i < sum.Length; ++i) {
            for (var j = i; j < sum.Length; ++j) {
                if (sum[i] > sum[j]) {
                    swap(arr, i, j);
                    var temp = sum[i];
                    sum[i] = sum[j];
                    sum[j] = temp;
                }
            }
        }
    }

    static int sumArray(int[,] arr)  
    {
        var sum_arr = sumOfNefativeElements(arr);  
        var sum = 0;
        for (var j = 0; j < arr.GetLength(1); ++j) {
            if (sum_arr[j] != 0) {
                for (var i = 0; i < arr.GetLength(0); ++i) {
                    sum += arr[i, j];
                }
            }
        }
        return sum;
    }
    // print
    static void printArray(int[,] arr) {  
        for (var i = 0; i < arr.GetLength(0); ++i){
            for (var j = 0; j < arr.GetLength(1); ++j){
                Console.Write($"{arr[i,j],5:D}");
            }
            Console.WriteLine();
        }
    }
    static void Main(String[] args)
    {
        // создать массив 
        uint length, width;  
        uint.TryParse(Console.ReadLine(), out length);  
        uint.TryParse(Console.ReadLine(), out width);  
        var arr = new int[length,width];
        Random r = new Random();
        for (var i = 0; i < length; ++i) {
            for (var j = 0; j < width; ++j){
                arr[i, j] = r.Next(-100,100);
            }
        }
        Console.WriteLine("original array");
        printArray(arr);
        // сортировать 
        sortArray(arr);
        Console.WriteLine("array after sorting");
        printArray(arr);
        // найти сумму 
        Console.WriteLine("sum of columns with at least 1 negative element: " + sumArray(arr));

    }
}

