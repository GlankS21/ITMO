namespace Lab_2;

public class Music { 
    public string authorName { get; set; }
    public string compositionName { get; set; }

    public Music() {
        Console.WriteLine("Input author's name:");
        authorName = Console.ReadLine();
        Console.WriteLine("Input the composition's name:");
        compositionName = Console.ReadLine();
    }
    public Music(string authorName, string compositionName) {
        this.authorName = authorName;
        this.compositionName = compositionName;
    }
    public string getMusic() {
        return $"{authorName} - {compositionName}";
    }
}