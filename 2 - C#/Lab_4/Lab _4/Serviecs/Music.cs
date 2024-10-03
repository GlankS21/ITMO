namespace Lab_4.Serviecs;

public class Music
{
    public string authorName { get; set; }
    public string compositionName { get; set; }
    public Guid Id;

    public Music() { 
        Console.WriteLine("Input author's name:");
        authorName = Console.ReadLine(); 
        Console.WriteLine("Input the composition's name:");
        compositionName = Console.ReadLine(); 
        Id = Guid.NewGuid();
    }  
    public Music(string authorName, string compositionName) {
        Id = Guid.NewGuid();
        this.authorName = authorName;
        this.compositionName = compositionName;
    }
    public string getMusic() {
        return $"{authorName} - {compositionName}";
    }
}