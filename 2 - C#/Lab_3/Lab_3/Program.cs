
using Lab_3;

using Lab_3.Core;
using Lab_3.Musics;
using Lab_3.View;
using static Lab_3.Musics.EMusicFunctions;

public class Program {
    public static void Main(String[] args)
    {
        using (var db = new MusicCatalogContext())
        {
            db.Database.EnsureCreated();
        }
        var database = new MusicRepository(new MusicCatalogContext());
        var musicDB = new MusicCatalog(database);
        View v = new View(musicDB);
        v.start();
    }
} 