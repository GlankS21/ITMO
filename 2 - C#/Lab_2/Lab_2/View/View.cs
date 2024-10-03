using static Lab_2.EMusicFunction;

namespace Lab_2.View;

public class View {
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
                case EMusicFunction.list:
                    _musicCatalog.listMusic();
                    break;
                case EMusicFunction.search:
                    Console.WriteLine("Input the part of the name to find composition in the catalog:");
                    _musicCatalog.seachMusic(Console.ReadLine()); 
                    break;
                case EMusicFunction.add:
                    Music music = new Music();
                    _musicCatalog.addMusic(music);
                    break;
                case EMusicFunction.del:
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
    private EMusicFunction setCommand(string command) {
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
                          + "'quick' to exit");
    }
}