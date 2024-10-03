using Lab_2;
using Lab_2.View;

 
public class Program {
    public static void Main(String[] args)
    {
        View v = new View(new MusicCatalog());
        v.start();
    }
}