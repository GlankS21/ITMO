using Lab_3.Model;
using Lab_3.Musics;
using static Lab_3.Musics.EMusicFunctions;

namespace Lab_3.View;

public class View
{
     private readonly MusicCatalog _musicCatalog;

    public View(MusicCatalog _musicCatalog) {
        this._musicCatalog = _musicCatalog ?? throw new AggregateException();
        usage();
    }
    public void start() {
        while (true) {
            var command =  setCommand(getCommand());;
            if (command.Equals(quit)) break;
            switch (command) {
                case EMusicFunctions.list:
                    _musicCatalog.listMusic();
                    break;
                case EMusicFunctions.search:
                    Console.WriteLine("Input the part of the name to find composition in the catalog:");
                    _musicCatalog.seachMusic(Console.ReadLine());
                    break;
                case EMusicFunctions.add:
                    Music music = new Music();
                    _musicCatalog.addMusic(new MusicModel() {
                        author = music.authorName,
                        composition = music.compositionName,
                        Id = music.Id
                    });
                    break;
                case EMusicFunctions.del:
                    Console.WriteLine("Input the full name of the track to remove:");
                    _musicCatalog.deleteMusic(Console.ReadLine());
                    break;
            }
        }   
    }

    private string getCommand() {
        Console.WriteLine("Input the command: ");
        return Console.ReadLine();
    }
    private EMusicFunctions setCommand(string command) {
        var cm = command switch {
                "list" => list,
                "search" => search,
                "add" => add,
                "del" => del,
                "quit" => quit,
                _ => throw new InvalidDataException(),
        };
        return cm;
    }
    private void usage() {
        Console.WriteLine("Usage: \n"
                          + "Type one of commands: \n"
                          + "'list' to display all item of catalog \n"
                          + "'search' to go find items in catalog \n"
                          + "'add' to add new item \n"
                          + "'del' to remove some item from list \n"
                          + "'quit' to exit");
    }
}